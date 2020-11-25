package com.bder.manage.security.provider;

import com.bder.manage.security.helper.NullAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Felix YF Dong
 * @date 2020/11/25
 */
public class NullAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (NullAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
