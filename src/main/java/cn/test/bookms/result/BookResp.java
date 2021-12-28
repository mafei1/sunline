package cn.test.bookms.result;

import cn.test.bookms.entity.BookInfo;

public class BookResp extends BookInfo{
	
	private String tName;
	private String adminName;
	
	
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	
	
	
}
