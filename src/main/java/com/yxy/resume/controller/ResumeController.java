package com.yxy.resume.controller;

import com.yxy.resume.common.R;
import com.yxy.resume.pojo.Resume;
import com.yxy.resume.service.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/4 16:38
 */
@Slf4j
@RestController
@Api(tags = "简历管理")
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @ApiOperation(value = "添加简历")
    @ApiImplicitParam(name = "resume", value = "简历信息", dataType = "Resume")
    @PostMapping()
    public R<String> addResume(@RequestBody Resume resume) {
        resumeService.save(resume);
        log.info("添加简历: {}", resume);
        return R.success("添加简历成功");
    }

    @ApiOperation(value = "删除简历")
    @ApiImplicitParam(name = "id", value = "简历id", dataType = "Integer")
    @DeleteMapping()
    public R<String> deleteResume(@RequestParam("id") Integer id) {
        resumeService.removeById(id);
        log.info("删除简历: {}", id);
        return R.success("删除简历成功");
    }

    @ApiOperation(value = "更新简历")
    @ApiImplicitParam(name = "resume", value = "简历信息", dataType = "Resume")
    @PutMapping()
    public R<String> updateResume(@RequestBody Resume resume) {
        resumeService.updateById(resume);
        log.info("更新简历: {}", resume);
        return R.success("更新简历成功");
    }

    @ApiOperation(value = "根据id获取简历信息")
    @ApiImplicitParam(name = "id", value = "简历id", dataType = "Integer")
    @GetMapping()
    public R<Resume> getResume(@RequestParam("id") Integer id) {
        log.info("获取简历: {}", id);
        return R.success(resumeService.getById(id));
    }

    @ApiOperation(value = "获取所有简历")
    @GetMapping("/all")
    public R<List<Resume>> getAllResumes() {
        log.info("获取所有简历");
        return R.success(resumeService.list());
    }
}