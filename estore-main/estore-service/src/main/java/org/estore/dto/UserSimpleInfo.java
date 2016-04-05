package org.estore.dto;

import java.util.Date;

public class UserSimpleInfo {
	private Long id;
	
	private String userName;
	
	private String password;
	
	private String nickName;
	
	private String email;
	
	private String mobile;
	
	private UserRole role;
	
	private Date createTime;
	
	private Date updateTime;
	
	private UserState state;
	
	private ActivateType activateType;
	
	private String activeCode;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}
	
	public ActivateType getActivateType() {
		return activateType;
	}

	public void setActivateType(ActivateType activateType) {
		this.activateType = activateType;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public enum UserRole{
		CUSTOMER(1),MERCHANT(2),ADMINSTRATOR(0);
		private int roleCode;

		public int getRoleCode() {
			return this.roleCode;
		}
		
		private UserRole(int roleCode) {
			this.roleCode = roleCode;
		}
	}
	
	public enum UserState{
		NON_ACTIVATED(0),ACTIVATED(1);
		private int stateCode;
		
		private UserState(int stateCode) {
			this.stateCode = stateCode;
		}

		public int getStateCode() {
			return stateCode;
		}
		
	}
	
	public enum ActivateType{
		BY_EMAIL(0);
		private int typeCode;
		
		private ActivateType(int typeCode) {
			this.typeCode = typeCode;
		}

		public int getTypeCode() {
			return typeCode;
		}
		
		
	}
}
