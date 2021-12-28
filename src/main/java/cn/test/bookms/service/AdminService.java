package cn.test.bookms.service;

import cn.test.bookms.entity.AdminInfo;

public interface AdminService {
	
	/**
	 * 管理员登录
	 * @param num
	 * @param pwd
	 * @return
	 */
	AdminInfo loginAdmin(String num,String pwd);

	/**
	 * id查询管理员信息
	 * @param aid
	 * @return
	 */
	AdminInfo selectById(Integer aid);
	
	/**
	 * 修改信息
	 * @param admin
	 */
	void  updateAdmin(AdminInfo admin);
}
