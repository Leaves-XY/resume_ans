package com.yxy.resume.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/4 14:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "简历")
public class Resume {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "籍贯")
    private String BrithPlace;
    @ApiModelProperty(value = "出生年月")
    private Date birthday;
    @ApiModelProperty(value = "年龄")
    private String age;
    @ApiModelProperty(value = "政治面貌")
    private String politStatus;
    @ApiModelProperty(value = "电话")
    private String cellphone;
    @ApiModelProperty(value = "学历")
    private String academicCareer;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "居住地")
    private String address;

    @ApiModelProperty(value = "教育经历")
    private ArrayList<Education> education;

    @ApiModelProperty(value = "工作经历")
    private ArrayList<Job> job;

    @ApiModelProperty(value = "项目经历")
    private ArrayList<Project> project;
}
