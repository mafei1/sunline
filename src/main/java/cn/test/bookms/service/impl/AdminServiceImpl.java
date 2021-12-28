package cn.test.bookms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.test.bookms.config.ConfigUtil;
import cn.test.bookms.entity.AdminInfo;
import cn.test.bookms.mapper.AdminInfoMapper;
import cn.test.bookms.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminInfoMapper adminMapper;
	
	public AdminInfo loginAdmin(String num, String pwd) {
		if(StringUtils.isEmpty(num)||StringUtils.isEmpty(pwd)) {
			return null;
		}
		AdminInfo admin = adminMapper.adminLogin(num, pwd);
		if(admin!=null&&admin.getAdminNum().equals(num)) {
			admin.setHeadImg(ConfigUtil.BOOKIMAGE_URI +admin.getHeadImg());
			admin.setAdminPwd("********");
			return adminMapper.adminLogin(num, pwd);
		}
		return null;
	}

	
	public AdminInfo selectById(Integer aid) {
		AdminInfo admin = adminMapper.selectByPrimaryKey(aid);
		admin.setHeadImg(ConfigUtil.BOOKIMAGE_URI +admin.getHeadImg());
		admin.setAdminPwd("********");//隐藏密码
		return admin;
	}

	
	public void updateAdmin(AdminInfo admin) {
		adminMapper.updateByPrimaryKeySelective(admin);
	}

}
