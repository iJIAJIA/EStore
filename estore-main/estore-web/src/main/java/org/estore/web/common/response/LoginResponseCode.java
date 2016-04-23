package org.estore.web.common.response;

public enum LoginResponseCode {
//	验证码模块
	AUTHCODE_SUCCESS("010200",""),
//	AUTHCODE_ERROR("010299","服务器异常,请稍后重试!");
//	用户登录模块
	LOGIN_SUCCESS("010100",""),
	LOGIN_WRONG_NAME_OR_PASSWORD("010101","用户名或密码错误!"),
	LOGIN_WRONG_IDENTITY_CODE("010102","验证码错误!"),
	LOGIN_ERROR("010199","服务器异常,请稍后重试!");
	
	
	private LoginResponseCode(String code,String message){
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
