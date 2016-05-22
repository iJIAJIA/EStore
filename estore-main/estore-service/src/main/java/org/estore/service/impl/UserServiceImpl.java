package org.estore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.estore.common.exception.ProcessException;
import org.estore.common.secure.ActivatecodeUtils;
import org.estore.common.secure.MD5Utils;
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
		List<UserSimpleInfo> infoList = findUserInfo(queryParams);
		UserSimpleInfo info = null;
		if(infoList != null && infoList.size() > 0){
			info = infoList.get(0);
		}
		return info;
	}


	@Override
	public List<UserSimpleInfo> findUserInfo(Map<String, Object> params)
			throws ProcessException {
		List<User> userList = userDao.findUser(params);
		List<UserSimpleInfo> userSimpleInfoList = null;
		if(userList != null && userList.size() > 0){
			userSimpleInfoList = new ArrayList<UserSimpleInfo>();
			for(User user: userList){
				UserSimpleInfo userSimpleInfo = new UserSimpleInfo();
				BeanUtils.copyProperties(userList, userSimpleInfoList, "state","role");
				userSimpleInfo.setState(UserState.getUserState(user.getState()));
				userSimpleInfo.setRole(UserRole.getUserRole(user.getRole()));
				userSimpleInfo.setActivateType(ActivateType.getActiveType(user.getActivateWay()));		
				userSimpleInfoList.add(userSimpleInfo);
			}
		}
		return userSimpleInfoList;
	}


	@Override
	public UserSimpleInfo registerCustomer(UserSimpleInfo userInfo)
			throws ProcessException {
		if(userInfo == null){
			throw new ProcessException("用户信息不能为空");
		}
		User user = new User();
		user.setUserName(userInfo.getUserName());
		user.setPassword(MD5Utils.md5(userInfo.getPassword()));
		user.setEmail(userInfo.getEmail());
		user.setActivateWay(ActivateType.BY_EMAIL.getTypeCode());
		user.setRole(UserRole.CUSTOMER.getRoleCode());
		user.setState(UserState.NON_ACTIVATED.getStateCode());
		user.setActiveCode(ActivatecodeUtils.generateActivatecode());
		user.setCreateTime(new Date());
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


	@Override
	public UserSimpleInfo findUserInfoByEmail(String email)
			throws ProcessException {
		Map<String,Object> queryParams = new HashMap<String, Object>();
		queryParams.put("email", email);
		List<UserSimpleInfo> infoList = findUserInfo(queryParams);
		UserSimpleInfo info = null;
		if(infoList != null && infoList.size() > 0){
			info = infoList.get(0);
		}
		return info;
	}


	@Override
	public UserSimpleInfo findUserInfoByUserName(String userName)
			throws ProcessException {
		Map<String,Object> queryParams = new HashMap<String, Object>();
		queryParams.put("userName", userName);
		List<UserSimpleInfo> infoList = findUserInfo(queryParams);
		UserSimpleInfo info = null;
		if(infoList != null && infoList.size() > 0){
			info = infoList.get(0);
		}
		return info;
	}
	
	
}
