package org.estore.web.model.user;

/**
 * 用户登录信息Model
 * @author iJIAJIA
 *
 */
public class UserLoginInfo {
	
	private String userName;
	
	private String password;
	
	private String identifyingCode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}

	@Override
	public String toString() {
		return "UserLoginInfo [userName=" + userName + ", password=" + password
				+ ", identifyingCode=" + identifyingCode + "]";
	}
	
	
}
