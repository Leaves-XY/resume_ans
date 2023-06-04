package com.yxy.resume.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/4 14:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPosition {
    private Long id;

    private String title;

    private String description;

    private String requirements;

    private String location;

    private String type; // 兼职 实习 校招 社招

    private String salaryRange;

//    private List<String> industries; //行业

    private String industries;

    private String experienceRequired;

    private String educationRequired;
}
