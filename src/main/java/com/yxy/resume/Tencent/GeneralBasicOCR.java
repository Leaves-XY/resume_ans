package com.yxy.resume.Tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.yxy.resume.until.GeneralBasicToText;
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
public class GeneralBasicOCR {
    @Value("${resume.tencent.SecretId}")
    private String SecretId;

    @Value("${resume.tencent.SecretKey}")
    private String SecretKey;


    public String covert(File file) {
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
            GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();

            byte[] fileContent = Files.readAllBytes(file.toPath());
            String encodedString = Base64.getEncoder().encodeToString(fileContent);

            req.setIsPdf(true);
            req.setImageBase64(encodedString);


            // 返回的resp是一个GeneralBasicOCRResponse的实例，与请求对象对应
            GeneralBasicOCRResponse resp = client.GeneralBasicOCR(req);
            String json = GeneralBasicOCRResponse.toJsonString(resp);

            // 输出json格式的字符串回包
            System.out.println(json);
            System.out.println("-----------------------------------------------------");

//            JsonToText.generalBasicToText(json);
            context= GeneralBasicToText.generalBasicToText(json);

        } catch (TencentCloudSDKException | IOException e) {
            e.printStackTrace();
        }
        return context;

    }
}
