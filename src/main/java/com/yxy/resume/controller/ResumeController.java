package com.yxy.resume.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxy.resume.common.R;
import com.yxy.resume.pojo.Resume;
import com.yxy.resume.service.ResumeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

    @ApiOperation(value = "更新简历")
    @PutMapping("/{id}")
    public R<String> updateResume(@RequestBody Resume resume) {
        resumeService.updateById(resume);
        return R.success("更新成功");
    }

    @ApiOperation(value = "根据ID获取简历")
    @GetMapping("/{id}")
    public R<Resume> getResumeById(@PathVariable Long id) {
        Resume resume = resumeService.getById(id);
        return R.success(resume);
    }

    @ApiOperation(value = "删除简历")
    @DeleteMapping("/{id}")
    public R<String> deleteResume(@PathVariable Long id) {
        resumeService.removeById(id);
        return R.success("删除成功");
    }

    @ApiOperation(value = "获取所有简历")
    @GetMapping("/list")
    public R<List<Resume>> getAllResume() {
        return R.success(resumeService.list());
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public R<IPage> page(int page,int pageSize) {
        Page<Resume> pageInfo = new Page<>(page, pageSize);
        IPage<Resume> result = resumeService.page(pageInfo);
        return R.success(result);
    }


}

