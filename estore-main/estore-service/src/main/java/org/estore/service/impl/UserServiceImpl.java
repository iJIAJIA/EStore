package org.estore.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.estore.common.exception.ProcessException;
import org.estore.dao.UserDao;
import org.estore.dto.UserSimpleInfo;
import org.estore.dto.UserSimpleInfo.ActivateType;
import org.estore.dto.UserSimpleInfo.UserRole;
import org.estore.dto.UserSimpleInfo.UserState;
import org.estore.entity.User;
import org.estore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceImpl implements UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserSimpleInfo findLoginUser(String userName, String password)
		throws ProcessException {
		Map<String,Object> queryParams = new HashMap<String, Object>();
		queryParams.put("userName", userName);
		queryParams.put("password", password);
		return findUserInfo(queryParams);
	}


	@Override
	public UserSimpleInfo findUserInfo(Map<String, Object> params)
			throws ProcessException {
		User user = userDao.findUser(params);
		UserSimpleInfo userSimpleInfo = null;
		if(user != null){
			userSimpleInfo = new UserSimpleInfo();
			BeanUtils.copyProperties(user, userSimpleInfo, "state","role");
			userSimpleInfo.setState(UserState.getUserState(user.getState()));
			userSimpleInfo.setRole(UserRole.getUserRole(user.getRole()));
			userSimpleInfo.setActivateType(ActivateType.getActiveType(user.getActivateWay()));			
		}
		return userSimpleInfo;
	}


	@Override
	public UserSimpleInfo registerUser(UserSimpleInfo userInfo)
			throws ProcessException {
		if(userInfo == null){
			throw new ProcessException("用户信息不能为空");
		}
		User user = new User();
		BeanUtils.copyProperties(userInfo, user,"state","role");
		user.setActivateWay(
				userInfo.getActivateType() == null? null: userInfo.getActivateType().getTypeCode());
		user.setRole(
				userInfo.getRole() == null? null: userInfo.getRole().getRoleCode());
		user.setState(
				userInfo.getState() == null? null: userInfo.getState().getStateCode());
		int insertRows = userDao.insertUser(user);
		if(insertRows == 1){
			userInfo.setId(user.getId());
			return userInfo;
		}
		return null;
	}


	@Override
	public boolean activateUser(UserSimpleInfo userInfo)
			throws ProcessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
