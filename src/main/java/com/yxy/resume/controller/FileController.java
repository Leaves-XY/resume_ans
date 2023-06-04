package com.yxy.resume.controller;

import com.yxy.resume.common.R;
import com.yxy.resume.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



/**
 * @ClassName FileController
 * @Description 文件相关控制器
 * @Author YeXingyi
 * @Date 2023年6月2日 21点49分
 */
@Api(tags = "上传文件相关接口")
@Slf4j
@RestController
public class FileController {
    @Autowired
    FileService fileService;



    /**
     * @Description 上传文件
     * @param file
     * @return
     */
    @ApiOperation(value = "上传简历文件(doc、text、pdf)")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "__file")
    @PostMapping("/uploadFile")
    public R<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String contentType = file.getContentType();
        String text = "";
        if (contentType.equals("application/pdf")) {
            text = fileService.extractTextFromPdf(file);
            // 文件是 PDF
        } else if (contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            // 文件是 DOCX
            text= fileService.extractTextFromDocxFile(file);
        } else if (contentType.equals("text/plain")) {
            // 文件是文本
            text = fileService.extractTextFromTextFile(file);
        } else if (contentType.startsWith("image/")) {
            // 文件是图片
            return R.error("暂时不支持图片文件");
        } else {
            // 文件类型未知或不支持
            return R.error("请读入支持的文件类型");
        }
        return R.success(text);
    }

    /**
     * @Description 上传文本
     */
    @ApiOperation(value = "上传简历文本")
    @ApiImplicitParam(name = "text", value = "文本", required = true, dataType = "String")
    @PostMapping("/uploadText")
    public R<String> uploadText(@RequestParam("text") String text) {
        return R.success(text);
    }
}
