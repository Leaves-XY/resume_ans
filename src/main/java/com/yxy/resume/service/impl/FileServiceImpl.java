package com.yxy.resume.service.impl;

import com.yxy.resume.Tencent.GeneralBasicOCR;
import com.yxy.resume.service.FileService;
import com.yxy.resume.until.FileCovert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * @ClassName FileServiceImp
 * @Description 处理文件相关服务实现类
 * @Author YeXingyi
 * @Date 2023年6月2日 21点49分
 */

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    GeneralBasicOCR generalBasicOCR ;

    /**
     * @Description 从 PDF 文件中提取文本
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String extractTextFromPdf(MultipartFile file) {
        try {
            File file1=FileCovert.convertMultipartFileToFile(file);
            return generalBasicOCR.covert(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Description 从文本文件中提取文本
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String extractTextFromTextFile(MultipartFile file) {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream is = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            // 这里可以根据你的需求对异常进行处理，比如打印异常信息，或者将异常封装后重新抛出
            e.printStackTrace();
        }
        return builder.toString().replaceAll("\\s", "").replaceAll("\\n", "");
    }

    /**
     * @Description 从 DOCX 文件中提取文本
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public String extractTextFromDocxFile(MultipartFile file) {
        try {
            File file1=FileCovert.convertMultipartFileToFile(file);
            File pdfFile=FileCovert.docxConvertPdf(file1);
            return generalBasicOCR.covert(pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @Description 从图片中提取文本
     * @param file
     * @return
     */
    public String extractTextFromImage(MultipartFile file) {
        try {
            File file1=FileCovert.convertMultipartFileToFile(file);
            return generalBasicOCR.covert(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String plainText(String text) {
        return text.replaceAll("\\s", "").replaceAll("\\n", "");
    }
}
