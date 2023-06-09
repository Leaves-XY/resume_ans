package com.yxy.resume.controller;


import com.yxy.resume.common.R;
import com.yxy.resume.pojo.Resume;
import com.yxy.resume.service.ResumeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月09日
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @ApiOperation(value = "添加简历")
    @PostMapping()
    public R<String> addResume(@RequestBody Resume resume) {

        resumeService.addResume(resume);
        return R.success("添加成功");
    }

    // 其他接口方法...

}

