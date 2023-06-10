package com.yxy.resume.service.impl;

import com.yxy.resume.pojo.Jobs;
import com.yxy.resume.mapper.JobsMapper;
import com.yxy.resume.service.JobsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月10日
 */
@Service
public class JobsServiceImpl extends ServiceImpl<JobsMapper, Jobs> implements JobsService {

}
