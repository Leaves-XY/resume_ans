package com.yxy.resume.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxy.resume.common.R;
import com.yxy.resume.pojo.Jobs;
import com.yxy.resume.pojo.Resume;
import com.yxy.resume.service.JobsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月10日
 */
@Api(tags = "岗位管理")
@RestController
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    private JobsService jobsService;

    @ApiOperation(value = "获取全部岗位")
    @GetMapping("/list")
    public R<List<Jobs>> getAll() {
        return R.success(jobsService.list());
    }

    @ApiOperation(value = "根据ID获取岗位")
    @GetMapping("/{id}")
    public R<Jobs> getById(@PathVariable Long id) {
        return R.success(jobsService.getById(id));
    }

    @ApiOperation(value = "添加岗位")
    @PostMapping("/")
    public R<String> save(@RequestBody Jobs jobs) {
        jobsService.save(jobs);
        return R.success("添加成功");
    }

    @ApiOperation(value = "更新岗位")
    @PutMapping("/{id}")
    public R<String> update(@PathVariable Long id, @RequestBody Jobs jobs) {
        jobs.setId(id);
        jobsService.updateById(jobs);
        return R.success("更新成功");
    }

    @ApiOperation(value = "删除岗位")
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id) {
        jobsService.removeById(id);
        return R.success("删除成功");
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public R<IPage> page(int page, int pageSize) {
        Page<Jobs> pageInfo = new Page<>(page, pageSize);
        IPage<Jobs> result = jobsService.page(pageInfo);
        return R.success(result);
    }
}

