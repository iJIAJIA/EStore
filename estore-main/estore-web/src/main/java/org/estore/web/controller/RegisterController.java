package org.estore.web.controller;

import org.apache.commons.lang.StringUtils;
import org.estore.common.exception.ProcessException;
import org.estore.dto.UserSimpleInfo;
import org.estore.service.UserService;
import org.estore.web.common.response.LoginModelResponseCode;
import org.estore.web.model.ReturnJsonResult;
import org.estore.web.model.user.UserRegisterInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
	private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
//	private static final String REGISTER_IDENTITYCODE_NAME = "registerIC";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="register.do")
	@ResponseBody
	public ReturnJsonResult doRegister(
			@RequestBody UserRegisterInfo registerInfo){
		logger.info("doRegister invoke");
		ReturnJsonResult returnResult = null;
		returnResult = checkRegisterInfo(registerInfo);
		if(returnResult == null){
//			用户注册信息检测通过,开始用户信息注册
			
			UserSimpleInfo toRegisterUserInfo = new UserSimpleInfo();
			BeanUtils.copyProperties(registerInfo, toRegisterUserInfo);
			try {
				UserSimpleInfo registedUserInfo = userService.registerCustomer(toRegisterUserInfo);
				logger.debug("Registed User Info: {}", registedUserInfo);
				returnResult = generateJsonResult(LoginModelResponseCode.REGISTER_SUCCESS);
			} catch (ProcessException e) {
				logger.error("Exception happened while register user info",e);
				returnResult = generateJsonResult(LoginModelResponseCode.REGISTER_ERROR);
			}
		}
		return returnResult;
	}
	
	/**
	 * 检查用户注册信息
	 * @param registerInfo
	 * @return 如果用户注册信息存在非法信息,则返回ReturnJsonResult,否则返回null
	 */
	private ReturnJsonResult checkRegisterInfo(UserRegisterInfo registerInfo){
		ReturnJsonResult result = null;
//		TODO 检测用户注册信息
		if(registerInfo == null || StringUtils.isBlank(registerInfo.getUserName()) ||
				StringUtils.isBlank(registerInfo.getPassword()) || 
				StringUtils.isBlank(registerInfo.getEmail())){
			result = generateJsonResult(LoginModelResponseCode.REGISTER_NULL_INFO);
			return result;
		}
		try {
			UserSimpleInfo userInfo = userService.findUserInfoByUserName(registerInfo.getUserName());
			if(userInfo != null){
				result = generateJsonResult(LoginModelResponseCode.REGISTER_USERNAME_EXISTED);
				return result;
			}
			userInfo = userService.findUserInfoByEmail(registerInfo.getEmail());
			if(userInfo != null){
				result = generateJsonResult(LoginModelResponseCode.REGISTER_EMAIL_EXISTED);
				return result;
			}
		} catch (ProcessException e) {
			logger.error("Exception Happned while while check User register info",e.getMessage());
			result = generateJsonResult(LoginModelResponseCode.REGISTER_ERROR);
		}
		return result;
	}
	
	/**
	 * 设置响应编码和信息
	 * @param respCode
	 * @return
	 */
	private ReturnJsonResult generateJsonResult(LoginModelResponseCode respCode){
		if(respCode == null){
			return null;
		}
		ReturnJsonResult result = new ReturnJsonResult();
		result.setReturnCode(respCode.getCode());
		result.setReturnMsg(respCode.getMessage());
		return result;
	}
}
