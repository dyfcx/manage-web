package com.bder.manage.security.service.impl;

import com.bder.manage.security.entity.SysUser;
import com.bder.manage.security.repository.SysUserRepository;
import com.bder.manage.security.vo.CustomUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix YF Dong
 * @date 2020/11/25
 */
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load UserDetails by username: " + username);
        SysUser sysUser = sysUserRepository.findSysUserByUsername(username);
        if (sysUser == null)
        {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        // TODO permission
//        for (String permission : sysUser.getPermission().split(" ")) {
//            if (permission.isEmpty())
//            {
//                continue;
//            }
//            authorities.add(new SimpleGrantedAuthority(permission));
//        }

        // TODO is Verified
//        return new CustomUser(username, sysUser.getPassword(), true, true, true, sysUser.isVerified(), authorities);
        return new CustomUser(username, sysUser.getPassword(), true, true, true, true, authorities);
    }
}
