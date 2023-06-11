package com.yxy.resume.service;

import com.yxy.resume.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月10日
 */
public interface UserService extends IService<Account> {
    /**
     * 注册
     * @param account
     * @return
     */
    public Account register(Account account);
}
