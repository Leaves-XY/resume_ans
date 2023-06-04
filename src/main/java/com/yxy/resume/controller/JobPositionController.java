package com.yxy.resume.controller;

import com.yxy.resume.common.R;
import com.yxy.resume.pojo.JobPosition;
import com.yxy.resume.service.JobPositionService;
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
 * @date 2023/6/4 14:40
 */
@Api(tags = "岗位管理")
@Slf4j
@RestController
@RequestMapping("/job")
public class JobPositionController {

    @Autowired
    private JobPositionService jobPositionService;

    @ApiOperation(value = "添加岗位")
    @ApiImplicitParam(name = "jobPosition", value = "岗位信息", dataType = "JobPosition")
    @PostMapping()
    public R<String> addJob(@RequestBody JobPosition jobPosition) {
        jobPositionService.save(jobPosition);
        log.info("添加岗位: {}", jobPosition);
        return R.success("添加岗位成功");
    }

    @ApiOperation(value = "删除岗位")
    @ApiImplicitParam(name = "id", value = "岗位id", dataType = "Integer")
    @DeleteMapping()
    public R<String> deleteJob(@RequestParam("id") Integer id) {
        jobPositionService.removeById(id);
        log.info("删除岗位: {}", id);
        return R.success("删除岗位成功");
    }

    @ApiOperation(value = "更新岗位")
    @ApiImplicitParam(name = "jobPosition", value = "岗位信息", dataType = "JobPosition")
    @PutMapping()
    public R<String> updateJob(@RequestBody JobPosition jobPosition) {
        jobPositionService.updateById(jobPosition);
        log.info("更新岗位: {}", jobPosition);
        return R.success("更新岗位成功");
    }

    @ApiOperation(value = "根据id获取岗位信息")
    @ApiImplicitParam(name = "id", value = "岗位id", dataType = "Integer")
    @GetMapping()
    public R<JobPosition> getJob(@RequestParam("id") Integer id) {
        log.info("获取岗位: {}", id);
        return R.success(jobPositionService.getById(id));
    }

    @ApiOperation(value = "获取所有岗位信息")
    @GetMapping("/all")
    public R<List<JobPosition>> getAllJobs() {
        log.info("获取所有岗位");
        return R.success(jobPositionService.list());
    }
}