package com.yxy.resume.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yxy.resume.pojo.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月10日
 */
@Mapper
public interface UserMapper extends BaseMapper<Account> {
    default Account findByUsername(String username){
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Account::getUsername, username);
        return selectOne(wrapper);
    }
}
