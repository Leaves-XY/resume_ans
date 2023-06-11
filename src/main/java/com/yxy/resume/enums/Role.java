package com.yxy.resume.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/10 20:42
 */
public enum Role {
    APPLICANT,
    RECRUITER,
    ADMIN;


    private static final Logger logger = LoggerFactory.getLogger(Role.class);
    /**
     * 大小写不敏感 如果找不到就抛出异常
     * @param role
     * @return
     */
    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return r;
            }
        }
        logger.warn("输入角色有误: " + role + ", 默认应聘者权限");
        return APPLICANT;
    }
}

