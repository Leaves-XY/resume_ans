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
@Api(value = "工作经历")
public class Job {
    @ApiModelProperty(value = "工作时间")
    private String jobTime;
    @ApiModelProperty(value = "工作单位")
    private String jobCompany;
    @ApiModelProperty(value = "工作内容")
    private String jobContent;
    @ApiModelProperty(value = "职务")
    private String function;
}
