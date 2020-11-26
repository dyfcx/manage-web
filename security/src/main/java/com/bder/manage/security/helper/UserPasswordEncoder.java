package com.bder.manage.security.helper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Felix YF Dong
 * @date 2020/11/25
 */
public class UserPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return PasswordHelper.encryptPassword(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return PasswordHelper.checkPassword(rawPassword.toString(),
                encodedPassword.toString());
    }
}
