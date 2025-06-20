package com.yxy.resume.controller;

import com.yxy.resume.client.PythonClient;
import com.yxy.resume.common.R;
import com.yxy.resume.pojo.Resume;
import com.yxy.resume.service.FileService;
import com.yxy.resume.service.ResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    ResumeService resumeService;

    @Value("${resume.toPython.url}")
    private String pythonUrl;

    /**
     * @Description 上传文件
     * @param file
     * @return
     */
    @ApiOperation(value = "上传简历文件(doc、text、pdf、png、jpg)")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "__file")
    @PostMapping("/uploadFile")
    public R<Resume> uploadFile(@RequestParam("file") MultipartFile file) {
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
            text=fileService.extractTextFromImage(file);
        } else {
            // 文件类型未知或不支持
            return R.error("请读入支持的文件类型");
        }
        Resume resume=PythonClient.sendPython(text,pythonUrl,resumeService);

        return R.success(resume);
    }

    /**
     * @Description 上传文本
     */
    @ApiOperation(value = "上传简历文本")
    @ApiImplicitParam(name = "text", value = "文本", required = true, dataType = "String")
    @PostMapping("/uploadText")
    public R<Resume> uploadText(@RequestParam("text") String text) {
        text = fileService.plainText(text);
        log.info("{}",text);
        Resume resume=PythonClient.sendPython(text,pythonUrl,resumeService);
        return R.success(resume);
    }
}
