package com.yxy.resume.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
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
    /**
     * 跳转到指定页面
     * @param url
     * @return
     */
    @RequestMapping("/{url}")
    public String forwardToStaticPage(@PathVariable("url") String url) {
        return "forward:/pages/" + url + ".html";
    }
}
