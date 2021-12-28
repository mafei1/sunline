package cn.test.bookms.result;

import cn.test.bookms.config.ConfigUtil;

/**
 * 统一消息格式
 * @author kai
 * @param <T>
 * @Description TODO
 * @Email kain.wong@foxmail.com
 * @Bolg  https://blog.csdn.net/qq_28631165
 */
public class Result<T> {

	private Integer code;
	private String msg;
	private T data;
	
	public Result() {
		this.code = ConfigUtil.SUCCESS_CODE;
		this.msg = ConfigUtil.SUCCESS_MSG;
	}
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
