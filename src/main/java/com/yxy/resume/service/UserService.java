package com.yxy.resume.service;

import com.yxy.resume.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月10日
 */
public interface UserService extends IService<User> {
    /**
     * 注册
     * @param user
     * @return
     */
    public User register(User user);
}
