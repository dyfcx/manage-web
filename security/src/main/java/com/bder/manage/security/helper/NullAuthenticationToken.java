package com.bder.manage.security.helper;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @author Felix YF Dong
 * @date 2020/11/25
 */
public class NullAuthenticationToken extends AbstractAuthenticationToken {

    /**
     *
     */
    private static final long serialVersionUID = 4544894451246623482L;

    private final Object principal;

    public NullAuthenticationToken(User principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
