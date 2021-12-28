package cn.test.bookms.entity;

import java.io.Serializable;

public class AdminInfo implements Serializable {
    private Integer aid;

    private String adminNum;

    private String adminName;

    private String adminPwd;

    private String email;

    private String sex;

    private Integer age;

    private String idcard;

    private String headImg;

    private Integer isSuper;

    private String note;

    private Integer isWork;

    private static final long serialVersionUID = 1L;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum == null ? null : adminNum.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd == null ? null : adminPwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public Integer getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Integer isSuper) {
        this.isSuper = isSuper;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getIsWork() {
        return isWork;
    }

    public void setIsWork(Integer isWork) {
        this.isWork = isWork;
    }

	@Override
	public String toString() {
		return "AdminInfo [aid=" + aid + ", adminNum=" + adminNum + ", adminName=" + adminName + ", adminPwd="
				+ adminPwd + ", email=" + email + ", sex=" + sex + ", age=" + age + ", idcard=" + idcard + ", headImg="
				+ headImg + ", isSuper=" + isSuper + ", note=" + note + ", isWork=" + isWork + "]";
	}
    
    
}