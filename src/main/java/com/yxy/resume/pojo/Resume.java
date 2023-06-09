package com.yxy.resume.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yxy.resume.common.ListTypeHandler;
import com.yxy.resume.common.StringTypeHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月09日
 */
@Data
@Accessors(chain = true)
@TableName(value="resume", autoResultMap = true)
@Api(tags = "简历")
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "简历id")
    private Long id;

    @TableField("name")
    @ApiModelProperty(value = "姓名")
    @JsonProperty("姓名")
    private String name;

    @TableField("gender")
    @ApiModelProperty(value = "性别")
    @JsonProperty("性别")
    private String gender;

    @TableField("brith_place")
    @ApiModelProperty(value = "籍贯")
    @JsonProperty("籍贯")
    private String brithPlace;

    @TableField("birthday")
    @ApiModelProperty(value = "出生年月")
    @JsonProperty("出生年月")
    private LocalDate birthday;

    @TableField("age")
    @ApiModelProperty(value = "年龄")
    @JsonProperty("年龄")
    private String age;

    @TableField("polit_status")
    @ApiModelProperty(value = "政治面貌")
    @JsonProperty("政治面貌")
    private String politStatus;

    @TableField("cellphone")
    @ApiModelProperty(value = "电话")
    @JsonProperty("电话")
    private String cellphone;

    @TableField("academic_career")
    @ApiModelProperty(value = "最高学历")
    private String academicCareer;

    @TableField("address")
    @ApiModelProperty(value = "落户市县")
    @JsonProperty("落户市县")
    private String address;

    @TableField("work_experience")
    @ApiModelProperty(value = "工作时限")
    private Integer workExperience;

    @ApiModelProperty(value = "毕业时间")
    @JsonProperty("毕业时间")
    @TableField(typeHandler = StringTypeHandler.class)
    private List<String> graduateDates;

    @ApiModelProperty(value = "毕业院校")
    @JsonProperty("毕业院校")
    @TableField(typeHandler = StringTypeHandler.class)
    private List<String> graduateSchools;

    @ApiModelProperty(value = "工作单位")
    @JsonProperty("工作单位")
    @TableField(typeHandler = StringTypeHandler.class)
    private List<String> jobCompanies;

    @ApiModelProperty(value = "职务")
    @JsonProperty("职务")
    @TableField(typeHandler = StringTypeHandler.class)
    private List<String> jobFunction;

    @ApiModelProperty(value = "工作时间")
    @JsonProperty("工作时间")
    @TableField(typeHandler = StringTypeHandler.class)
    private List<String> jobTimes;

    @ApiModelProperty(value = "工作内容")
    @JsonProperty("工作内容")
    @TableField(typeHandler = StringTypeHandler.class)
    private List<String> jobContents;

    @ApiModelProperty(value = "学位")
    @JsonProperty("学位")
    @TableField(typeHandler = StringTypeHandler.class)
    private List<String> educationDegrees;

    @ApiModelProperty(value = "项目负责")
    @JsonProperty("项目负责")
    @TableField(typeHandler = StringTypeHandler.class)
    private ArrayList<String> projectExperiences;

    @ApiModelProperty(value = "项目名称")
    @JsonProperty("项目名称")
    @TableField(typeHandler = StringTypeHandler.class)
    private ArrayList<String> projectNames;

    @ApiModelProperty(value = "项目时间")
    @JsonProperty("项目时间")
    @TableField(typeHandler = StringTypeHandler.class)
    private ArrayList<String> projectTimes;

}
