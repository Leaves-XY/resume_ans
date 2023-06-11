package com.yxy.resume.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import com.yxy.resume.handler.StringTypeHandler;
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
 * @since 2023年06月10日
 */
@Data
@Accessors(chain = true)
@TableName(value = "jobs", autoResultMap = true)
@Api(tags = "岗位信息")
public class Jobs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "岗位id")
    private Long id;

    @TableField("position_name")
    @ApiModelProperty(value = "岗位名称")
    private String positionName;

    @TableField("location")
    @ApiModelProperty(value = "工作地点")
    private String location;

    @TableField("salary_range")
    @ApiModelProperty(value = "薪资范围")
    private String salaryRange;

    @TableField("education_requirement")
    @ApiModelProperty(value = "学历要求")
    private String educationRequirement;

    @TableField(value = "job_tags", typeHandler = StringTypeHandler.class)
    @ApiModelProperty(value = "岗位标签")
    private List<String> jobTags;

    @TableField(value = "industry")
    @ApiModelProperty(value = "所属行业")
    private String industry;

    @TableField("job_description")
    @ApiModelProperty(value = "岗位描述")
    private String jobDescription;

    @TableField("job_requirement")
    @ApiModelProperty(value = "岗位要求")
    private String jobRequirement;

    @TableField("contact_person")
    @ApiModelProperty(value = "联系人")
    private String contactPerson;

    @TableField("company_name")
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @TableField("company_address")
    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @TableField("company_description")
    @ApiModelProperty(value = "公司描述")
    private String companyDescription;

    @TableField("other_info")
    @ApiModelProperty(value = "其他信息")
    private String otherInfo;


}
