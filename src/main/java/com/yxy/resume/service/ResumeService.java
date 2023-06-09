package com.yxy.resume.service;

import com.yxy.resume.pojo.Resume;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月09日
 */
public interface ResumeService extends IService<Resume> {
    /**
     * 添加简历
     * @param resume
     * @return
     */
    public boolean addResume(Resume resume);



}
