package com.bder.manage.security.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bder.manage.security.service.SysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;


/**
 * @author Felix YF Dong
 * @date 2020/12/8
 */
public class TokenClearLogoutHandler implements LogoutHandler {

	private final SysUserService sysUserService;

	public TokenClearLogoutHandler(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		clearToken(authentication);
	}

	protected void clearToken(Authentication authentication) {
		if(authentication == null) {
			return;
		}
		UserDetails user = (UserDetails)authentication.getPrincipal();
		if(user!=null && user.getUsername()!=null) {
		    sysUserService.deleteUserLoginInfo(user.getUsername());
		}
	}

}
