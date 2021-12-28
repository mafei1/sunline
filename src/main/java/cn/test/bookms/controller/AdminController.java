package cn.test.bookms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.test.bookms.config.ConfigUtil;
import cn.test.bookms.entity.AdminInfo;
import cn.test.bookms.result.Result;
import cn.test.bookms.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public Result<AdminInfo> adminLogin(String adminNum,String adminPwd,HttpSession session){
		Result<AdminInfo> result = new Result<AdminInfo>();
		try {
			AdminInfo admin = adminService.loginAdmin(adminNum, adminPwd);
			if(admin==null) {
				result.setCode(ConfigUtil.FAILED_CODE);
				result.setMsg("用户名或密码错误，请重试");
			}
			if(admin!=null&&admin.getIsWork()==1) {
				result.setCode(ConfigUtil.FAILED_CODE);
				result.setMsg("该员工已离职，禁止登录");
			}
			session.setAttribute("admin", admin);  //管理员信息存入sesssion中
			result.setData(admin);
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return  result;
	}
	
	/**
	 * 
	 * @Description: 注销
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/logout")
	public Result<String> logout(HttpSession session){
		Result<String> result = new Result<String>();
		try {
			AdminInfo admin = (AdminInfo) session.getAttribute("admin");
			if(admin!=null) {
				session.removeAttribute("admin");
			}
			result.setData("注销成功");
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 通过id获取管理员信息
	 * @Description: 
	 * @param aid
	 * @return
	 */
	@RequestMapping(value="/one")
	public Result<AdminInfo> selectById(Integer aid){
		Result<AdminInfo> result = new Result<AdminInfo>();
		try {
			AdminInfo admin = adminService.selectById(aid);
			if(admin==null) {
				result.setCode(ConfigUtil.FAILED_CODE);
				result.setMsg("未找到该用户，请重新登录");
				return result;
			}
			result.setData(admin);
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @Description: 管理员头像修改
	 * @return   图书封面文件名
	 */
	@RequestMapping(value = "/headImg")
	public Result<String> selectImgName(MultipartFile file) {
		Result<String> result = new Result<String>();
		try {
			String newFileName = ConfigUtil.fileOperate(file, ConfigUtil.IMG_LOCAL_PATH);
			result.setData(newFileName);
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @Description: 修改信息
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/update")
	public Result<String> updateAdminInfo(AdminInfo admin){
		//如果密码没有变动，则不修改密码
		if("".equals(admin.getAdminPwd())) {
			admin.setAdminPwd(null);
		}
		System.out.println("修改信息："+admin);
		Result<String> result = new Result<String>();
		try {
			admin.setHeadImg(admin.getHeadImg().substring(admin.getHeadImg().lastIndexOf("/")+1));
			System.out.println(admin);
			adminService.updateAdmin(admin);
			result.setData("修改成功");
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
}
