package com.bder.manage.security.handle;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bder.manage.security.helper.JwtAuthenticationToken;
import com.bder.manage.security.service.SysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author Felix YF Dong
 * @date 2020/12/8
 */
public class JwtRefreshSuccessHandler implements AuthenticationSuccessHandler{

	private static final int TOKEN_REFRESH_INTERVAL = 300;  //刷新间隔5分钟

	private final SysUserService sysUserService;

	public JwtRefreshSuccessHandler(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		DecodedJWT jwt = ((JwtAuthenticationToken)authentication).getToken();
		boolean shouldRefresh = shouldTokenRefresh(jwt.getIssuedAt());
		if(shouldRefresh) {
            String newToken = sysUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
            response.setHeader("Authorization", newToken);
        }
	}

	protected boolean shouldTokenRefresh(Date issueAt){
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(TOKEN_REFRESH_INTERVAL).isAfter(issueTime);
    }

}
