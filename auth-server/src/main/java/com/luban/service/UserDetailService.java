package com.luban.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用户的账户信息
 */
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;//加密工具类

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return User.builder().username(name).password(passwordEncoder.encode("123456"))
                .authorities("ROLE_ADMIN").build();
    }
}
