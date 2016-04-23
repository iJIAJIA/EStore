package org.estore.dao;

import java.util.Map;

import org.estore.entity.User;

public interface UserDao {
	
	/**
	 * 获取用户信息
	 * @param params
	 * @return
	 */
	public User findLoginUser(Map<String,Object> params);
	
	/**
	 * 保存用户信息
	 * @param user
	 * @return 用户Id
	 */
	public int saveUser(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
}
