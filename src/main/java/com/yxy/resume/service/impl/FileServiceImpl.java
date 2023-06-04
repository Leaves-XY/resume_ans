package com.yxy.resume.service.impl;

import com.yxy.resume.service.FileService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @ClassName FileServiceImp
 * @Description 处理文件相关服务实现类
 * @Author YeXingyi
 * @Date 2023年6月2日 21点49分
 */

@Service
public class FileServiceImpl implements FileService {
    /**
     * @Description 从 PDF 文件中提取文本
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String extractTextFromPdf(MultipartFile file) {
        PDFTextStripper pdfStripper = null;
        PDDocument pdDocument = null;
        String text = null;
        try {
            pdfStripper = new PDFTextStripper();
            pdDocument = PDDocument.load(file.getInputStream());
            text = pdfStripper.getText(pdDocument);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pdDocument != null) {
                try {
                    pdDocument.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return text;
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
                builder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            // 这里可以根据你的需求对异常进行处理，比如打印异常信息，或者将异常封装后重新抛出
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * @Description 从 DOCX 文件中提取文本
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public String extractTextFromDocxFile(MultipartFile file) {
        StringBuilder text = new StringBuilder();
        InputStream fis = null;
        XWPFDocument document = null;
        try {
            fis = file.getInputStream();
            document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                text.append(para.getText());
            }
        } catch (IOException e) {
            // 异常处理，比如打印异常信息
            e.printStackTrace();
        } finally {
            // 确保 fis 在任何情况下都会被关闭
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 确保 document 在任何情况下都会被关闭
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return text.toString();
    }
}
