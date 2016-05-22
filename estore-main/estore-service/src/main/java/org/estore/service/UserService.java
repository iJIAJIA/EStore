package org.estore.service;

import java.util.List;
import java.util.Map;

import org.estore.common.exception.ProcessException;
import org.estore.dto.UserSimpleInfo;

public interface UserService {

	/**
	 * 根据条件获取用户基本信息
	 * @param params
	 * @return 
	 */
	public List<UserSimpleInfo> findUserInfo(Map<String,Object> params) throws ProcessException;
	
	/**
	 * 注册用户
	 * @param userInfo
	 * @return
	 */
	public UserSimpleInfo registerCustomer(UserSimpleInfo userInfo) throws ProcessException;
	
	/**
	 * 根据用户邮箱查找用户
	 * @param email
	 * @return
	 * @throws ProcessException
	 */
	public UserSimpleInfo findUserInfoByEmail(String email) throws ProcessException;
	
	/**
	 * 根据用户姓名查找用户
	 * @param userName
	 * @return
	 * @throws ProcessException
	 */
	public UserSimpleInfo findUserInfoByUserName(String userName) throws ProcessException;
	
	
	/**
	 * 激活用户
	 * @param userInfo
	 * @return
	 */
	public boolean activateUser(UserSimpleInfo userInfo) throws ProcessException;
	
	
	/**
	 * 根据用户名和密码获取用户信息
	 * @param userName
	 * @param password
	 * @return null or UserSimpleInfo
	 */
	public UserSimpleInfo findLoginUser(String userName,String password) throws ProcessException;
}
