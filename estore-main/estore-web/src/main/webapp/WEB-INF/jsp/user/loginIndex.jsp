<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all">
<title>登录页面</title>
</head>
<body>
	<div class="w">
		<div id="logo">
			<a href="">
				<img src="./img/logo.jpg" height="60" width="170"></img>
			</a>
			<b id="welcome"></b>
		</div>
	</div>
	<div id="content">
		<div class="login-wrap">
			<div class="w">
				<div class="login-form">
					<div class="login-box">
						<!-- 登录盒子顶部 -->
						<div class="mt">
							<div class="extra-r">
                                <div class="regist-link"><a href="" target="_blank">立即注册</a></div>
                            </div>
						</div>
						<!-- 登录信息显示部位 -->
						<div class="msg-wrap">
							<div class="msg-warn"><b></b>公共场所不建议自动登录，以防账号丢失</div>
						</div>
						<!-- 登录盒子内容 -->
						<div class="mc">
							<!-- 登录信息 -->
							<div class="form">
								<form id="formlogin" method="post">
									<div class="item item-fore1">
										<label class="login-label name-label" for="loginname"></label>
										<input id="loginname" class="itxt" name="loginname" tabindex="1" autocomplete="off" placeholder="邮箱/用户名" type="text">
										<span class="clear-btn" style="display:inline;"></span>									
									</div>
									<div class="item item-fore2">
										<label class="login-label pwd-label" for="nloginpwd"></label>
										<input id="nloginpwd" name="nloginpwd" class="itxt itxt-error" tabindex="2"  placeholder="密码" type="password">
										<span class="clear-btn" style="display:inline;"></span>
									</div>
									<div class="item item-fore3" class="safe">
										<div class="safe">
											<span>
												<input id="autoLogin" class="jdcheckbox" tabindex="3"  type="checkbox"/>
												<label for="autoLogin">自动登录</label>
											</span>
											<span class="forget-pw-safe">
												<a href="" target="_blank">忘记密码?</a>
											</span>
										</div>
									</div>
									<div class="item item-fore5">
										<div class="login-btn">
											<a id="loginsubmit" class="btn-img btn-entry"
											href="javascript:;" tabindex="6">登录</a>
										</div>	
									</div>
									<div class="item-blank">
									
									</div>
								</form>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<div class="login-banner" style="background-color: #da1720;height:475px">
				<div class="w">
					<div id="banner-bg" class="i-inner"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>