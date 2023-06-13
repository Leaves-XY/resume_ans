package com.yxy.resume.until;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/14 0:25
 */
public class MobileOcrToText {
    public static String processOcrResult(String ocrResultJson) {
        JSONObject ocrResult = JSON.parseObject(ocrResultJson);
        JSONObject content = ocrResult.getJSONObject("body").getJSONObject("content");
        JSONArray wordsInfo = content.getJSONArray("prism_wordsInfo");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < wordsInfo.size(); i++) {
            JSONObject wordInfo = wordsInfo.getJSONObject(i);
            String word = wordInfo.getString("word");
            result.append(word).append("\n");
        }

        return result.toString();
    }
}
