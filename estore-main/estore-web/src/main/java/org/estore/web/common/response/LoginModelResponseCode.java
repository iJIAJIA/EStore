package org.estore.web.common.response;

public enum LoginModelResponseCode {
//	验证码模块
	AUTHCODE_SUCCESS("010200",""),
//	AUTHCODE_ERROR("010299","服务器异常,请稍后重试!");
//	用户登录模块
	LOGIN_SUCCESS("010100",""),
	LOGIN_WRONG_NAME_OR_PASSWORD("010101","用户名或密码错误!"),
	LOGIN_WRONG_IDENTITY_CODE("010102","验证码错误!"),
	LOGIN_ERROR("010199","服务器异常,请稍后重试!"),
	
//	用户注册模块
	REGISTER_SUCCESS("020100","注册成功"),
	REGISTER_USERNAME_EXISTED("020101","用户名已存在"),
	REGISTER_EMAIL_EXISTED("020102","邮箱已存在"),
	REGISTER_NULL_INFO("020103","非法注册信息"),
	REGISTER_ERROR("020199","服务器异常,请稍后重试!");
	
	
	private LoginModelResponseCode(String code,String message){
		this.code = code;
		this.message = message;
	}
	private String code;
	
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
