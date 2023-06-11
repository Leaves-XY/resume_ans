package com.yxy.resume.controller;


import com.yxy.resume.common.R;
import com.yxy.resume.pojo.Account;
import com.yxy.resume.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月10日
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public R<String> register(@RequestBody Account account) {
        userService.register(account);
        return R.success("注册成功");
    }
}

