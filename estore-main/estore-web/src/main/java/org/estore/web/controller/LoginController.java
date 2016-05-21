package org.estore.web.controller;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.estore.common.exception.ProcessException;
import org.estore.common.secure.IdentityCodeUtils;
import org.estore.dto.UserSimpleInfo;
import org.estore.service.UserService;
import org.estore.web.common.response.LoginResponseCode;
import org.estore.web.model.IdentityCode;
import org.estore.web.model.ReturnJsonResult;
import org.estore.web.model.user.UserLoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private static final String LOGIN_IDENTITYCODE_NAME = "loginIC";
	
	private static final Long IC_USERFUL_TIME = 1000*60*15L; //验证码有效期,15分钟
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	@ResponseBody
	public ReturnJsonResult doLogin(@RequestBody UserLoginInfo loginInfo,
			HttpSession session){
		logger.info("doLogin invoke...");
		logger.info("userLoginInfo: {}",loginInfo);
//		1,检查用户验证码
		IdentityCode iCode = (IdentityCode) session.getAttribute(LOGIN_IDENTITYCODE_NAME);
		logger.info("Before removed IdentityCode: {}",iCode);
		session.removeAttribute(LOGIN_IDENTITYCODE_NAME);
		logger.info("After removed IdentityCode: {}",iCode);
		ReturnJsonResult result = null;
		if(!checkIdentityCode(loginInfo.getIdentifyingCode(),iCode)){
//			TODO 发送验证码错误响应码
			result = generateJsonResult(LoginResponseCode.LOGIN_WRONG_IDENTITY_CODE);
		}
//		2,获取登录用户信息
		UserSimpleInfo userSimpleInfo = null;
		try {
			userSimpleInfo = userService.findLoginUser(loginInfo.getUserName(), loginInfo.getPassword());
		} catch (ProcessException e) {
			logger.error("验证用户登录信息异常",e.getMessage());
			result = generateJsonResult(LoginResponseCode.LOGIN_ERROR);
			return result;
		}
		logger.debug("userSimpleInfo: {}",userSimpleInfo);
//		3.返回信息
		if(userSimpleInfo == null){
			logger.info("User's Login Info is null");
			result = generateJsonResult(LoginResponseCode.LOGIN_WRONG_NAME_OR_PASSWORD);
		}else{
			result = generateJsonResult(LoginResponseCode.LOGIN_SUCCESS);
			result.setReturnObject(userSimpleInfo);
		}
		logger.debug("ReturnJsonResult: {}",result);
		return result;
	}
	
	@RequestMapping(value="login.index")
	public String loginIndex(HttpSession session){
		logger.info("loginIndex invoke...");
		return "user/loginIndex";
	}
	
	/**
	 * 获取验证码接口
	 * @param session
	 * @return
	 */
	@RequestMapping(value="getAuthCode.do")
	@ResponseBody
	public ReturnJsonResult getIdentityCode(HttpSession session){
		logger.info("getIdentityCode invoke..");
		
//		生成验证码
		String codeStr = IdentityCodeUtils.generateIdentityCode();
		logger.info("Generated IdentityCode: " + codeStr);
		BufferedImage bImage = IdentityCodeUtils.generateIndentityImage(codeStr);
		
//		将验证码存储于session中
		IdentityCode iCode = new IdentityCode();
		iCode.setCode(codeStr);
		iCode.setDeadTime(new Date().getTime() + IC_USERFUL_TIME);
		session.setAttribute(LOGIN_IDENTITYCODE_NAME, iCode);
		
//		生成响应信息
		ReturnJsonResult jsonResult = generateJsonResult(LoginResponseCode.AUTHCODE_SUCCESS);
		jsonResult.setReturnObject(bImage);
		
		return jsonResult;
	}
	/**
	 * 检查用户验证码
	 * @param code
	 * @return
	 */
	private boolean checkIdentityCode(String code,IdentityCode iCode){
//		logger.debug("IdentityCode: {} || DeadTime: {}",iCode.getCode(),iCode.getDeadTime());
		
		return true;
	}
	
	/**
	 * 设置响应编码和信息
	 * @param respCode
	 * @return
	 */
	private ReturnJsonResult generateJsonResult(LoginResponseCode respCode){
		if(respCode == null){
			return null;
		}
		ReturnJsonResult result = new ReturnJsonResult();
		result.setReturnCode(respCode.getCode());
		result.setReturnMsg(respCode.getMessage());
		return result;
	}
}
