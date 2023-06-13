package com.yxy.resume.api;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmss.sdk.ocr.ECloudDefaultClient;
import com.chinamobile.cmss.sdk.ocr.IECloudClient;
import com.chinamobile.cmss.sdk.ocr.http.constant.Region;
import com.chinamobile.cmss.sdk.ocr.http.signature.Credential;
import com.chinamobile.cmss.sdk.ocr.request.IECloudRequest;
import com.chinamobile.cmss.sdk.ocr.request.ocr.OcrRequestFactory;
import com.yxy.resume.until.MobileOcrToText;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/13 23:01
 */
@Component
public class MobileOCR {
    //用户鉴权对象
    @Value("${resume.mobile.AccessKey}")
    private String user_ak;
    @Value("${resume.mobile.SecretKey}")
    private String user_sk;

    public String covert(File file) {

        Credential credential = new Credential(user_ak,   user_sk);
        IECloudClient client = new ECloudDefaultClient(credential, Region.POOL_SZ);
        String text;
        try {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        HashMap<String, Object> OCRParam = new HashMap<>();


        IECloudRequest OCRRequest = OcrRequestFactory.getOcrBase64Request("/api/ocr/v1/generic", encodedString,OCRParam);

            JSONObject response = (JSONObject) client.call(OCRRequest);
            String result = response.toString();
            System.out.println(result);

            text=MobileOcrToText.processOcrResult(result);
            System.out.println(text);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "移动OCR端处理错误";
    }

}
