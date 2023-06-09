package com.yxy.resume.service.impl;

import com.yxy.resume.mapper.*;
import com.yxy.resume.pojo.*;
import com.yxy.resume.service.ResumeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YeXingyi
 * @since 2023年06月09日
 */
@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {
    /**
     * 添加简历
     * @param resume
     * @return
     */
    public boolean addResume(Resume resume) {
        // 定义支持的日期格式列表
        List<String> dateFormats = Arrays.asList("yyyy.MM", "yyyy-MM", "yyyy/MM", "MM/yyyy", "MM.yyyy", "MM-yyyy", "MM/yyyy", "MM.yyyy", "MM-yyyy", "yyyy.MM.dd", "yyyy-MM-dd", "yyyy/MM/dd", "dd.MM.yyyy", "dd-MM-yyyy", "dd/MM/yyyy");

        String birthDateStr = resume.getBirthday(); // 获取出生年月字符串

        if(birthDateStr == null || birthDateStr.isEmpty()) {
            return this.save(resume);
        }

        LocalDate birthDate = null;
    // 尝试使用不同的日期格式进行解析
        for (String format : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                birthDate = LocalDate.parse(birthDateStr, formatter);
                break; // 解析成功，退出循环
            } catch (DateTimeParseException e) {
                continue; // 解析失败，继续尝试下一个日期格式
            }
        }

    // 如果解析失败，尝试使用 YearMonth 解析年月，并手动补充默认的日（如 1 号）
        if (birthDate == null) {
            for (String format : dateFormats) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                    YearMonth yearMonth = YearMonth.parse(birthDateStr, formatter);
                    birthDate = yearMonth.atDay(1); // 补充默认的日
                    break; // 解析成功，退出循环
                } catch (DateTimeParseException e) {
                    continue; // 解析失败，继续尝试下一个日期格式
                }
            }
        }

    // 判断是否成功解析出生日期
        if (birthDate != null) {
            // 计算年龄
            LocalDate currentDate = LocalDate.now();
            long years = ChronoUnit.YEARS.between(birthDate, currentDate);
            int age = (int) years;

            // 设置年龄属性
            resume.setAge(age);
        }

        return this.save(resume);
    }

}
