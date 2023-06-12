package com.yxy.resume.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxy.resume.dto.ResumeDto;
import com.yxy.resume.dto.dtoMapper.ResumeDtoMapper;
import com.yxy.resume.pojo.Resume;
import com.yxy.resume.service.ResumeService;
import com.yxy.resume.until.OptimizeText;
import io.swagger.annotations.Api;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/8 6:58
 */
@Api(value = "发送给python的数据")
public class PythonClient {


    /**
     * 发送给python的数据
     * @param text 文本
     * @param url python接口的url
     * @return
     */
    static public Resume sendPython(String text,String url,ResumeService resumeService) {

        RestTemplate restTemplate = new RestTemplate();

        // 获取消息转换器列表
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();

        // 遍历消息转换器列表
        for (HttpMessageConverter<?> converter : messageConverters) {
            // 如果找到了StringHttpMessageConverter
            if (converter instanceof StringHttpMessageConverter) {
                // 将它的默认字符集设置为UTF-8
                ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
            }
        }

        // 设置headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAcceptCharset(Collections.singletonList(Charset.forName("UTF-8")));

        text= OptimizeText.removeNonEncodableGBKCharacters(text);

        // 封装请求，包括headers和body
        HttpEntity<String> request = new HttpEntity<>(text, headers);

        // 发送POST请求
        ResponseEntity<String> response = restTemplate. postForEntity(url, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                ResumeDto resumeDto = objectMapper.readValue(response.getBody(), ResumeDto.class);
                Resume resume=ResumeDtoMapper.mapResumeDtoToResume(resumeDto);
                resume.setResumeText(text);
                resumeService.saveByAnalysis(resume);
                System.out.println(resume);
                return resume;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}