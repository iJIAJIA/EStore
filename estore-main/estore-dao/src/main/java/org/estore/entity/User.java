package org.estore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="USER")
public class User {
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="USERNAME")
	private String userName;
	
	private String password;
	
	private String email;
	
	private int role;
	
	private Date createTime;
	
	private Date updateTime;
	
	private int state;
	
	private int activateWay;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getActivateWay() {
		return activateWay;
	}

	public void setActivateWay(int activateWay) {
		this.activateWay = activateWay;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", email=" + email + ", role=" + role
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", state=" + state + ", activateWay=" + activateWay
				+ ", activeCode=" + activeCode + "]";
	}
	
	
}
