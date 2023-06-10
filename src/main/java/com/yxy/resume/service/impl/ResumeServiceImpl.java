package com.yxy.resume.service.impl;

import com.yxy.resume.mapper.*;
import com.yxy.resume.pojo.*;
import com.yxy.resume.service.ResumeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yxy.resume.until.ResumeUtils;

import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月09日
 */
@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {
    /**
     * 添加简历
     * @param resume
     * @return
     */
    public boolean addResume(Resume resume) {

        resume.setAge(ResumeUtils.calculateAge(resume.getBirthday()));

        resume.setAcademicCareer(ResumeUtils.calculateAcademicCareer(resume.getEducationDegrees()));

        resume.setWorkExperience(ResumeUtils.calculateWorkExperience(resume.getJobTimes(), resume.getGraduateDates()));

        return this.save(resume);
    }



}
