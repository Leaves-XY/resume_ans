package com.yxy.resume.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/9 16:57
 */
@Data
public class ResumeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "简历id")
    private Long id;

    @ApiModelProperty(value = "姓名")
    @JsonProperty("姓名")
    private List<String> name;

    @ApiModelProperty(value = "性别")
    @JsonProperty("性别")
    private List<String> gender;

    @ApiModelProperty(value = "籍贯")
    @JsonProperty("籍贯")
    private List<String> birthPlace;

    @ApiModelProperty(value = "出生年月")
    @JsonProperty("出生年月")
    private List<String> birthday;

    @ApiModelProperty(value = "年龄")
    @JsonProperty("年龄")
    private List<Integer> age;

    @ApiModelProperty(value = "政治面貌")
    @JsonProperty("政治面貌")
    private List<String> politicalStatus;

    @ApiModelProperty(value = "电话")
    @JsonProperty("电话")
    private List<String> cellphone;

    @ApiModelProperty(value = "最高学历")
    @JsonProperty("最高学历")
    private List<String> academicCareer;

    @ApiModelProperty(value = "落户市县")
    @JsonProperty("落户市县")
    private List<String> address;

    @ApiModelProperty(value = "工作时限")
    @JsonProperty("工作时限")
    private List<Integer> workExperience;

    @ApiModelProperty(value = "毕业时间")
    @JsonProperty("毕业时间")
    private List<String> graduateDates;

    @ApiModelProperty(value = "毕业院校")
    @JsonProperty("毕业院校")
    private List<String> graduateSchools;

    @ApiModelProperty(value = "工作单位")
    @JsonProperty("工作单位")
    private List<String> jobCompanies;

    @ApiModelProperty(value = "职务")
    @JsonProperty("职务")
    private List<String> jobFunctions;

    @ApiModelProperty(value = "工作时间")
    @JsonProperty("工作时间")
    private List<String> jobTimes;

    @ApiModelProperty(value = "工作内容")
    @JsonProperty("工作内容")
    private List<String> jobContents;

    @ApiModelProperty(value = "学位")
    @JsonProperty("学位")
    private List<String> educationDegrees;

    @ApiModelProperty(value = "项目负责")
    @JsonProperty("项目负责")
    private List<String> projectExperiences;

    @ApiModelProperty(value = "项目名称")
    @JsonProperty("项目名称")
    private List<String> projectNames;

    @ApiModelProperty(value = "项目时间")
    @JsonProperty("项目时间")
    private List<String> projectTimes;

    // Getters and Setters
}