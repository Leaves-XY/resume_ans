package com.yxy.resume.tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.yxy.resume.until.GeneralBasicToText;
import com.yxy.resume.until.PdfPageCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/7 16:06
 */
@Component
@Slf4j
public class GeneralBasicOCR {
    @Value("${resume.tencent.SecretId}")
    private String SecretId;

    @Value("${resume.tencent.SecretKey}")
    private String SecretKey;


    public String covert(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            // Load PDF document


            // Get number of pages
            int numPages = PdfPageCounter.getPageCount(file);

            for (int i = 0; i < numPages; i++) {
                Credential cred = new Credential(SecretId, SecretKey);
                HttpProfile httpProfile = new HttpProfile();
                httpProfile.setEndpoint("ocr.tencentcloudapi.com");
                ClientProfile clientProfile = new ClientProfile();
                clientProfile.setHttpProfile(httpProfile);
                OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);

                GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
                byte[] fileContent = Files.readAllBytes(file.toPath());
                String encodedString = Base64.getEncoder().encodeToString(fileContent);
                req.setIsPdf(true);
                req.setImageBase64(encodedString);
                req.setPdfPageNumber((long)i+1);  // Set the page number

                GeneralBasicOCRResponse resp = client.GeneralBasicOCR(req);
                String json = GeneralBasicOCRResponse.toJsonString(resp);
                System.out.println(json);
                System.out.println("-----------------------------------------------------");

                String context = GeneralBasicToText.generalBasicToText(json);
                sb.append(context);
            }
        } catch (TencentCloudSDKException | IOException e) {
            log.info("腾讯云OCR识别失败: {}", e.getMessage());
        }
        return sb.toString();
    }
}
