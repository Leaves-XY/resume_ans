package com.yxy.resume.api;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.*;
import com.yxy.resume.until.RecognizeTableToText;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component
@Data
public class RecognizeTableOCR
{
    @Value("${resume.tencent.SecretId}")
    private static String SecretId;

    @Value("${resume.tencent.SecretKey}")
    private String SecretKey;


    public String covert(MultipartFile file) {
        String context = "";
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential(SecretId, SecretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            RecognizeTableOCRRequest req = new RecognizeTableOCRRequest();

            byte[] fileContent = file.getBytes();
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            req.setIsPdf(true);
            req.setImageBase64(encodedString);


            // 返回的resp是一个RecognizeTableOCRResponse的实例，与请求对象对应
            RecognizeTableOCRResponse resp = client.RecognizeTableOCR(req);
            // 输出json格式的字符串回包
            String json = RecognizeTableOCRResponse.toJsonString(resp);
            System.out.println(json);
            RecognizeTableToText.recognizeTableToText(json);

        } catch (TencentCloudSDKException | IOException e) {
            e.printStackTrace();
        }
        return context;
    }
}