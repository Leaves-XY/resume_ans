package com.yxy.resume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.resume.pojo.User;
import com.yxy.resume.mapper.UserMapper;
import com.yxy.resume.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月10日
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User register(User user) {
        // 加密密码
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        // 保存用户
        userMapper.insert(user);
        return user;
    }
}
