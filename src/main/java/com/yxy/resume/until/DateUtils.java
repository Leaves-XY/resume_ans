package com.yxy.resume.until;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/9 22:57
 */
public class DateUtils {

    /**
     * 替换文本中的日期精确到月
     * @param text
     * @return
     */
    public static String convertDatesAccurateToTheMonth(String text) {
        // 定义你希望寻找的日期格式
        String[] datePatterns = {"yyyy.MM", "yyyy-MM", "yyyy/MM"};
        String[] mmYyyyPatterns = {"MM/yyyy", "MM.yyyy", "MM-yyyy"};

        // 遍历每种日期格式
        for(String pattern : datePatterns){
            text = convertWithPatternAccurateToTheMonth(text, pattern, "\\d{4}[./-]\\d{2}", "yyyy年MM月");
        }

        // 单独处理 MM/yyyy，MM.yyyy 和 MM-yyyy 格式
        for(String pattern : mmYyyyPatterns){
            text = convertWithPatternAccurateToTheMonth(text, pattern, "\\d{2}[./-]\\d{4}", "yyyy年MM月");
        }

        return text;
    }

    private static String convertWithPatternAccurateToTheMonth(String text, String datePattern, String regex, String targetPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        // 如果源格式与目标格式相同，需设置lenient为false
        sdf.setLenient(false);
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(text);

        while (m.find()) {
            String dateStr = m.group(0);
            try {
                Date date = sdf.parse(dateStr);
                SimpleDateFormat newFormat = new SimpleDateFormat(targetPattern);
                String newDateStr = newFormat.format(date);
                // 使用新的日期格式替换旧的日期格式
                text = text.replace(dateStr, newDateStr);
            } catch (ParseException e) {
                // 如果日期格式不匹配，我们会忽略这个异常，因为我们试图匹配多种日期格式。
            }
        }
        return text;
    }

    /**
     * 替换文本日期精确到天
     * @param text
     * @return
     */
    public static String convertDatesAccurateToTheDay(String text) {
        DatePattern[] patterns = new DatePattern[] {
                new DatePattern("yyyy.MM.dd", "\\d{4}\\.\\d{2}\\.\\d{2}", "yyyy年MM月dd日"),
                new DatePattern("yyyy-MM-dd", "\\d{4}-\\d{2}-\\d{2}", "yyyy年MM月dd日"),
                new DatePattern("yyyy/MM/dd", "\\d{4}/\\d{2}/\\d{2}", "yyyy年MM月dd日"),
                new DatePattern("dd.MM.yyyy", "\\d{2}\\.\\d{2}\\.\\d{4}", "dd日MM月yyyy年"),
                new DatePattern("dd-MM-yyyy", "\\d{2}-\\d{2}-\\d{4}", "dd日MM月yyyy年"),
                new DatePattern("dd/MM/yyyy", "\\d{2}/\\d{2}/\\d{4}", "dd日MM月yyyy年"),
                new DatePattern("yyyy.MM", "\\d{4}\\.\\d{1,2}", "yyyy年MM月"),
                new DatePattern("yyyy-MM", "\\d{4}-\\d{1,2}", "yyyy年MM月"),
                new DatePattern("yyyy/MM", "\\d{4}/\\d{1,2}", "yyyy年MM月"),
                new DatePattern("MM.yyyy", "\\d{1,2}\\.\\d{4}", "MM月yyyy年"),
                new DatePattern("MM-yyyy", "\\d{1,2}-\\d{4}", "MM月yyyy年"),
                new DatePattern("MM/yyyy", "\\d{1,2}/\\d{4}", "MM月yyyy年"),
        };

        Pattern rangePattern = Pattern.compile("(\\d{4}[./-]\\d{1,2})[./-](\\d{4}[./-]\\d{1,2})");
        Matcher rangeMatcher = rangePattern.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (rangeMatcher.find()) {
            String start = rangeMatcher.group(1);
            String end = rangeMatcher.group(2);
            String separator = text.substring(rangeMatcher.start() + start.length(), rangeMatcher.start() + start.length() + 1);
            for (DatePattern pattern : patterns) {
                start = convertWithPatternAccurateToTheDay(start, pattern);
                end = convertWithPatternAccurateToTheDay(end, pattern);
            }
            rangeMatcher.appendReplacement(sb, start + separator + end);
        }
        rangeMatcher.appendTail(sb);
        text = sb.toString();

        for (DatePattern pattern : patterns) {
            text = convertWithPatternAccurateToTheDay(text, pattern);
        }

        return text;
    }

    private static String convertWithPatternAccurateToTheDay(String text, DatePattern datePattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern.pattern);
        sdf.setLenient(false);
        Pattern r = Pattern.compile(datePattern.regex);
        Matcher m = r.matcher(text);

        StringBuilder result = new StringBuilder();
        int lastEnd = 0;

        while (m.find()) {
            String dateStr = m.group(0);
            try {
                Date date = sdf.parse(dateStr);
                SimpleDateFormat newFormat = new SimpleDateFormat(datePattern.targetPattern);
                String newDateStr = newFormat.format(date);
                result.append(text, lastEnd, m.start()).append(newDateStr);
                lastEnd = m.end();
            } catch (ParseException e) {
                // do nothing
            }
        }
        result.append(text.substring(lastEnd));
        return result.toString();
    }

    private static class DatePattern {
        String pattern;
        String regex;
        String targetPattern;

        DatePattern(String pattern, String regex, String targetPattern) {
            this.pattern = pattern;
            this.regex = regex;
            this.targetPattern = targetPattern;
        }
    }



}