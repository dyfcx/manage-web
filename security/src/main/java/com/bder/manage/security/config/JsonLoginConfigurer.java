package com.bder.manage.security.config;

import com.bder.manage.security.filter.JwtAuthenticationTokenFilter;
import com.bder.manage.security.handle.HttpStatusLoginFailureHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

/**
 * @author Felix YF Dong
 * @date 2020/12/8
 */
public class JsonLoginConfigurer<T extends JsonLoginConfigurer<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B>  {

	private final JwtAuthenticationTokenFilter authFilter;

	public JsonLoginConfigurer() {
		this.authFilter = new JwtAuthenticationTokenFilter();
	}

	@Override
	public void configure(B http) throws Exception {
		authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		authFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());
		authFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());

		JwtAuthenticationTokenFilter filter = postProcess(authFilter);
		http.addFilterAfter(filter, LogoutFilter.class);
	}

	public JsonLoginConfigurer<T,B> loginSuccessHandler(AuthenticationSuccessHandler authSuccessHandler){
		authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
		return this;
	}

}
