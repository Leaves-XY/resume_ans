package com.yxy.resume.until;

import com.yxy.resume.pojo.Resume;
import io.swagger.models.auth.In;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/9 22:39
 */
public class ResumeUtils {

    /**
     * 根据出生日期计算年龄
     * @param birthDateStr
     * @return
     */
    public static Integer calculateAge(String birthDateStr) {
        if (birthDateStr == null || birthDateStr.isEmpty()) {
            return null;
        }

        LocalDate birthDate = null;
        // 尝试使用日期格式 "yyyy年MM月" 进行解析
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月");
            YearMonth yearMonth = YearMonth.parse(birthDateStr, formatter);
            birthDate = yearMonth.atDay(1); // 补充默认的日
        } catch (DateTimeParseException e) {
            // 尝试使用日期格式 "yyyy年MM月dd日" 进行解析
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
                birthDate = LocalDate.parse(birthDateStr, formatter);
            } catch (DateTimeParseException ex) {
                // 解析失败，可能是日期格式不正确
                System.out.println("Invalid date format");
            }
        }

        // 判断是否成功解析出生日期
        if (birthDate != null) {
            // 计算年龄
            LocalDate currentDate = LocalDate.now();
            long years = ChronoUnit.YEARS.between(birthDate, currentDate);
            int age = (int) years;
            return age;
        }

        return null;
    }


    /**
     * 根据学历计算学历级别
     * @param educationDegrees
     * @return
     */
    public static String calculateAcademicCareer(List<String> educationDegrees) {

        if (educationDegrees==null|| educationDegrees.isEmpty()) {
            return null;
        }

        List<String> academicLevels = Arrays.asList("文盲", "小学", "初中", "中专", "高中", "专科", "本科", "研究生", "硕士", "博士");

        List<String> matchedDegrees = new ArrayList<>(); // 匹配到的学位

        // 遍历学位列表，判断是否匹配指定的学历级别，如果匹配则加入结果集合中
        for (String degree : educationDegrees) {
            for (String level : academicLevels) {
                if (degree.contains(level)) {
                    matchedDegrees.add(level);
                    break;
                }
            }
        }

        if (!matchedDegrees.isEmpty()) {
            // 根据学位进行排序
            Collections.sort(matchedDegrees);

            // 获取最高学位
            String maxDegree = matchedDegrees.get(matchedDegrees.size() - 1);

            if (maxDegree.equals("研究生")) {
                maxDegree = "硕士";
            }

            return maxDegree;
        }

        return null;
    }


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy年MM月", Locale.CHINA);
    /**
     * 根据工作时间计算工作经验
     * @param jobTimes,graduateDates
     * @return
     */
    public static Integer calculateWorkExperience(List<String> jobTimes,List<String> graduateDates) {
        String targetDate = null;

        if (jobTimes != null && !jobTimes.isEmpty()) {
            targetDate = findEarliestDate(jobTimes);
        } else if (graduateDates != null && !graduateDates.isEmpty()) {
            targetDate = findLatestDate(graduateDates);
        } else {
            return 0;
        }

        return calculateDifferenceToCurrentDate(targetDate);
    }

    private static String findEarliestDate(List<String> dates) {
        String earliestDate = null;

        for (String date : dates) {
            if (earliestDate == null || date.substring(0, 8).compareTo(earliestDate) < 0) {
                earliestDate = date;
            }
        }

        return earliestDate.length() > 8 ? earliestDate.substring(0, 8) : earliestDate;
    }

    private static String findLatestDate(List<String> dates) {
        String latestDate = null;

        for (String date : dates) {
            if (latestDate == null || date.substring(0, 8).compareTo(latestDate) > 0) {
                latestDate = date;
            }
        }

        return latestDate.length() > 8 ? latestDate.substring(0, 8) : latestDate;
    }

    private static Integer calculateDifferenceToCurrentDate(String date) {
        if (date == null) {
            return 0;
        }

        YearMonth currentDate = YearMonth.now();
        YearMonth targetDate = YearMonth.parse(date, FORMATTER);

        long monthsDifference = ChronoUnit.MONTHS.between(targetDate, currentDate);
        int yearsDifference = (int) (monthsDifference / 12);

        return yearsDifference;
    }
}
