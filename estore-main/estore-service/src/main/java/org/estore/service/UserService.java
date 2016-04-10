package org.estore.service;

import java.util.Map;

import org.estore.dto.UserSimpleInfo;

public interface UserService {

	/**
	 * 根据条件获取用户基本信息
	 * @param params
	 * @return true if user exited false not a user
	 */
	public UserSimpleInfo findUserInfo(Map<String,Object> params);
	
	/**
	 * 注册用户
	 * @param userInfo
	 * @return
	 */
	public UserSimpleInfo registerUser(UserSimpleInfo userInfo);
	
	/**
	 * 激活用户
	 * @param userInfo
	 * @return
	 */
	public boolean activateUser(UserSimpleInfo userInfo);
	
	
	/**
	 * 根据用户名和密码获取用户信息
	 * @param userName
	 * @param password
	 * @return null or UserSimpleInfo
	 */
	public UserSimpleInfo findLoginUser(String userName,String password);
}
