package com.yxy.resume.service.impl;

import com.yxy.resume.pojo.Account;
import com.yxy.resume.mapper.UserMapper;
import com.yxy.resume.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月10日
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Account> implements UserService {
    @Autowired
    private UserMapper userMapper;

    public Account register(Account user) {
        // 加密密码
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        // 设置注册时间
        user.setCreateTime(LocalDateTime.now());
        // 保存用户
        userMapper.insert(user);
        return user;
    }
}
