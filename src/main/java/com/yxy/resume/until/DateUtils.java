package com.yxy.resume.until;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/9 22:57
 */
public class DateUtils {

    public static String replaceDateFormats(String input) {
        // 定义日期格式的正则表达式
        String pattern = "(\\d{4}\\.\\d{2}\\.\\d{2}|\\d{4}-\\d{2}-\\d{2}|\\d{4}/\\d{2}/\\d{2}|\\d{2}/\\d{2}/\\d{4}|\\d{4}\\.\\d{2}|\\d{4}-\\d{2}|\\d{4}/\\d{2})";

        // 编译正则表达式
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);

        // 逐个匹配并替换日期格式
        StringBuffer output = new StringBuffer();
        while (matcher.find()) {
            String matchedDate = matcher.group(1); // 获取匹配到的日期
            String replacement = formatDate(matchedDate); // 格式化日期
            matcher.appendReplacement(output, replacement);
        }
        matcher.appendTail(output);

        return output.toString();
    }

    public static String formatDate(String date) {
        // 解析日期格式并进行格式化
        String[] dayFormats = {"yyyy.MM.dd", "yyyy-MM-dd", "yyyy/MM/dd", "MM/dd/yyyy"};
        String[] monthFormats = {"yyyy.MM", "yyyy-MM", "yyyy/MM", "MM/yyyy"};
        String dayReplacement = "yyyy年MM月dd日";
        String monthReplacement = "yyyy年MM月";

        for (String format : dayFormats) {
            if (date.matches(format)) {
                return date.replaceAll(format, dayReplacement);
            }
        }

        for (String format : monthFormats) {
            if (date.matches(format)) {
                return date.replaceAll(format, monthReplacement);
            }
        }

        return date; // 如果无法匹配任何日期格式，返回原始日期
    }


//    public static final List<String> DATE_FORMATS = Arrays.asList(
//            "yyyy.MM", "yyyy-MM", "yyyy/MM", "MM/yyyy", "MM.yyyy", "MM-yyyy", "MM/yyyy", "MM.yyyy", "MM-yyyy",
//            "yyyy.MM.dd", "yyyy-MM-dd", "yyyy/MM/dd", "dd.MM.yyyy", "dd-MM-yyyy", "dd/MM/yyyy"
//    );
//
//    /**
//     * 转换类里面所有日期属性的格式
//     * @param object
//     * @param targetFormat
//     */
//    public static void convertDateProperties(Object object, String targetFormat) {
//        Class<?> clazz = object.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//
//        for (Field field : fields) {
//            if (field.getType().equals(String.class) && isDateProperty(field.getName(), clazz)) {
//                field.setAccessible(true);
//                try {
//                    String dateString = (String) field.get(object);
//                    if (dateString != null) {
//                        LocalDate date = parseDate(dateString);
//                        String formattedDate = formatDate(date, targetFormat);
//                        field.set(object, formattedDate);
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            } else if (field.getType().equals(List.class) && isDateCollectionProperty(field.getName(), clazz)) {
//                field.setAccessible(true);
//                try {
//                    List<String> dateList = (List<String>) field.get(object);
//                    if (dateList != null) {
//                        List<String> formattedDateList = new ArrayList<>();
//                        for (String dateString : dateList) {
//                            LocalDate date = parseDate(dateString);
//                            String formattedDate = formatDate(date, targetFormat);
//                            formattedDateList.add(formattedDate);
//                        }
//                        field.set(object, formattedDateList);
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private static boolean isDateProperty(String propertyName, Class<?> clazz) {
//        // 在此方法中判断指定类的属性是否为日期类型的逻辑，返回true或false
//        // 根据需要自行实现判断逻辑
//        return true; // 这里示例简单地返回true，表示所有属性都是日期属性
//    }
//
//    private static boolean isDateCollectionProperty(String propertyName, Class<?> clazz) {
//        // 在此方法中判断指定类的集合属性是否为日期类型的逻辑，返回true或false
//        // 根据需要自行实现判断逻辑
//        return true; // 这里示例简单地返回true，表示所有集合属性都是日期属性
//    }
//
//    private static LocalDate parseDate(String dateString) {
//        // 在此方法中实现日期字符串的解析逻辑，返回解析后的LocalDate对象
//        for (String format : DATE_FORMATS) {
//            try {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
//                return LocalDate.parse(dateString, formatter);
//            } catch (DateTimeParseException e) {
//                // 解析失败，尝试下一个日期格式
//            }
//        }
//        // 如果所有格式都无法解析，则返回null或抛出异常，根据实际情况自行处理
//        return null;
//    }
//
//    private static String formatDate(LocalDate date, String targetFormat) {
//        if (date == null) {
//            return null; // 或者返回一个默认的日期字符串，根据实际需求决定
//        }
//        // 在此方法中实现LocalDate对象的格式化逻辑，返回格式化后的日期字符串
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(targetFormat);
//        return date.format(formatter);
//    }
}