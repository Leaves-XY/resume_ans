package com.yxy.resume.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileService
 * @Description 处理文件相关服务接口
 * @Author YeXingyi
 * @Date 2023年6月2日 21点49分
 */
public interface FileService{
    public String extractTextFromPdf(MultipartFile file);

    public String extractTextFromTextFile(MultipartFile file) ;

    public String extractTextFromDocxFile(MultipartFile file);

    public String extractTextFromImage(MultipartFile file);

    public String plainText(String text);

}
