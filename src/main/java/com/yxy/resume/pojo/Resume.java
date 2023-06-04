package com.yxy.resume.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/4 14:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    private Long id;

    private String name;

    private String gender;

    private String age;

    private String polit_status;

    private String cellphone;

    private String education;

    private String email;

    private String address;

    private String job;

    private String school;

    private String eduDate;

    private String major;

    private String courseInfo;

    private String skillInfo;

    private String workExperience;

    private String selfEvaluation;

    private String projects;

//    private String photo;
}
