package com.yxy.resume.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/10 22:57
 */
@Api(tags = "视图管理")
@Controller
public class ViewController {
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }


    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin.html";
    }
}
