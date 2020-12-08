package com.bder.manage.security.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bder.manage.security.service.SysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Felix YF Dong
 * @date 2020/12/8
 */
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler{

	private final SysUserService sysUserService;

	public JsonLoginSuccessHandler(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String token = sysUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
		response.setHeader("Authorization", token);
	}

}
