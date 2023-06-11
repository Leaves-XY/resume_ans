package com.yxy.resume.service.impl;

import com.yxy.resume.enums.Role;
import com.yxy.resume.mapper.UserMapper;
import com.yxy.resume.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/11 10:56
 */
@Service
public class MyAccountDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userMapper.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Role role = Role.fromString(account.getRole());
        return new User(account.getUsername(), account.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name())));

    }
}