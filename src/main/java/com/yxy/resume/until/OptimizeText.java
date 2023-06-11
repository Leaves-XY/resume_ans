package com.yxy.resume.until;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/10 15:45
 */
public class OptimizeText {
    public static String optimize(String text){
        text=DateUtils.convertDatesAccurateToTheDay(text);
        text=text.replaceAll("\\r", "").replaceAll("\\n", "").replaceAll("\\t", "").replaceAll(" ", "");
        text=labelPhone(text);
        text=labelEmail(text);
        return text;
    }

    /**
     * 标记电话号码
     * @param text
     * @return
     */
    public static String labelPhone(String text){
        String regex = "(?<!\\d)(?:(?:1[3456789]\\d{9})|(?:861[3456789]\\d{9}))(?!\\d)";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(text);
        while (m.find()) {
            String phone = m.group(0);
            text = text.replaceAll(phone, "<电话>" + phone + "</电话>");
        }
        return text;
    }

    /**
     * 标记邮箱
     * @param text
     * @return
     */
    public static String labelEmail(String text){
        String regex = "(?<!\\w)([a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+)(?!\\w)";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(text);
        while (m.find()) {
            String email = m.group(0);
            text = text.replaceAll(email, "<邮箱>" + email + "</邮箱>");
        }
        return text;
    }

    /**
     * 移除非GBK编码的字符
     * @param input
     * @return
     */
    public static String removeNonEncodableGBKCharacters(String input) {
        Charset gbk = Charset.forName("GBK");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String currentCharacter = String.valueOf(input.charAt(i));
            if (gbk.newEncoder().canEncode(currentCharacter)) {
                output.append(currentCharacter);
            }
        }
        return output.toString();
    }
}
