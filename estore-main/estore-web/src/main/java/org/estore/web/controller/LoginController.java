package org.estore.web.controller;

import org.estore.dto.UserSimpleInfo;
import org.estore.service.UserService;
import org.estore.web.model.ReturnJsonResult;
import org.estore.web.model.user.UserLoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJsonResult doLogin(@RequestBody UserLoginInfo loginInfo){
		logger.info("doLogin invoke...");
		logger.debug("userLoginInfo: {}",loginInfo);
//		1,检查用户验证码
		checkIdentityCode(loginInfo.getIdentifyingCode());
//		2,获取登录用户信息
		UserSimpleInfo userSimpleInfo = userService.findLoginUser(loginInfo.getUserName(), 
				loginInfo.getPassword());
		logger.debug("userSimpleInfo: {}",userSimpleInfo);
		ReturnJsonResult result = generateResult4LoginUsr(userSimpleInfo);
		return result;
	}
	@RequestMapping(value="login.do",method=RequestMethod.GET)
	@ResponseBody
	public ReturnJsonResult doLogin(@RequestParam("userName")String userName,
			@RequestParam("password")String password){
		logger.info("userName: {}",userName);
		logger.info("password: {}",password);
		ReturnJsonResult result = new ReturnJsonResult();
		result.setReturnCode("0000");
		result.setReturnMsg("登录成功!");
		return result;
	}
	
	/**
	 * 检查用户验证码
	 * @param code
	 * @return
	 */
	private boolean checkIdentityCode(String code){
		return true;
	}
	
	/**
	 * 根据用户信息生成返回对象
	 * @param userInfo
	 * @return
	 */
	private ReturnJsonResult generateResult4LoginUsr(UserSimpleInfo userInfo){
		ReturnJsonResult result = new ReturnJsonResult();
		result.setReturnCode("0000");
		result.setReturnMsg("登录成功!");		
		
		return result;
	}
}
