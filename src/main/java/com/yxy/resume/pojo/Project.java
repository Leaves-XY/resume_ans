package com.yxy.resume.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/8 6:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "项目经历")
public class Project {
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "项目负责")
    private String projectExperience;
    @ApiModelProperty(value = "项目时间")
    private String projectTime;
}
