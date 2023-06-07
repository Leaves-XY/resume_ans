package com.yxy.resume.client;

import com.yxy.resume.common.R;
import com.yxy.resume.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/8 6:59
 */

@Api(value = "网页发送给python的数据")
@RestController
public class HtmlToPython {
    @Autowired
    FileService fileService;


    @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "__file")
    @PostMapping("/htmlToPython")
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
            text=fileService.extractTextFromImage(file);
        } else {
            // 文件类型未知或不支持
            return R.error("请读入支持的文件类型");
        }
        PythonClient.sendPython(text);
        return R.success("已发送给python");
    }
}
