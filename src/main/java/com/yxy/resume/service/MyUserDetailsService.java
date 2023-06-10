package com.yxy.resume.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.resume.mapper.UserMapper;
import com.yxy.resume.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/10 22:28
 */

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            throw new UsernameNotFoundException("用户 " + username + " 不在数据库中");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), convertRolesToAuthorities(user.getRole()));
    }

    private Collection<SimpleGrantedAuthority> convertRolesToAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
