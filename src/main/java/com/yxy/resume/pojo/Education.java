package com.yxy.resume.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/8 6:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "教育经历")
public class Education {
    @ApiModelProperty(value = "毕业院校")
    private String graduateSchool;
    @ApiModelProperty(value = "毕业时间")
    private String graduateDate;
    @ApiModelProperty(value = "专业")
    private String major;
}
