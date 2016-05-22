package org.estore.dao;

import java.util.List;
import java.util.Map;

import org.estore.entity.User;

public interface UserDao {
	
	/**
	 * 获取用户信息
	 * @param params
	 * @return
	 */
	public List<User> findUser(Map<String,Object> params);
	
	/**
	 * 保存用户信息,用户Id在插入成功后会赋值到给定user的id字段上
	 * @param user
	 * @return 操作行数
	 */
	public int insertUser(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
}
