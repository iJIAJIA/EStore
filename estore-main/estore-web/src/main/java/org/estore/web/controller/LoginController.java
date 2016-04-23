package org.estore.web.controller;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.estore.common.secure.IdentityCodeUtils;
import org.estore.dto.UserSimpleInfo;
import org.estore.service.UserService;
import org.estore.web.common.response.LoginResponseCode;
import org.estore.web.model.IdentityCode;
import org.estore.web.model.ReturnJsonResult;
import org.estore.web.model.user.UserLoginInfo;
import org.estore.web.model.user.UserRegisterInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private static final String LOGIN_IDENTITYCODE_NAME = "loginIC";
	
	private static final Long IC_USERFUL_TIME = 1000*60*15L; //验证码有效期,15分钟
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
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
		if(!checkIdentityCode(loginInfo.getIdentifyingCode(),iCode)){
//			TODO 发送验证码错误响应码
		}
//		2,获取登录用户信息
//		UserSimpleInfo userSimpleInfo = userService.findLoginUser(loginInfo.getUserName(), 
//				loginInfo.getPassword());
//		logger.debug("userSimpleInfo: {}",userSimpleInfo);
//		3.返回信息
//		ReturnJsonResult result = generateReturnCode4LoginUsr(userSimpleInfo);
//		return result;
		return new ReturnJsonResult();
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
		ReturnJsonResult jsonResult = new ReturnJsonResult();
		setResponseCode(jsonResult, LoginResponseCode.AUTHCODE_SUCCESS);
		jsonResult.setReturnObject(bImage);
		
		return jsonResult;
	}
	/**
	 * 检查用户验证码
	 * @param code
	 * @return
	 */
	private boolean checkIdentityCode(String code,IdentityCode iCode){
		logger.debug("IdentityCode: {} || DeadTime: {}",iCode.getCode(),iCode.getDeadTime());
		
		return true;
	}
	
	/**
	 * 根据用户信息生成返回返回码
	 * @param userInfo
	 * @return
	 */
	private ReturnJsonResult generateReturnCode4LoginUsr(UserSimpleInfo userInfo){
		ReturnJsonResult result = new ReturnJsonResult();
		result.setReturnCode("0000");
		result.setReturnMsg("登录成功!");		
		
		return result;
	}
	
	/**
	 * 将响应码和响应信息写入ReturnJsonResult中
	 * @param jsonResult
	 * @param respCode
	 */
	private void setResponseCode(ReturnJsonResult jsonResult,LoginResponseCode respCode){
		jsonResult.setReturnCode(respCode.getCode());
		jsonResult.setReturnMsg(respCode.getMessage());
	}
}
