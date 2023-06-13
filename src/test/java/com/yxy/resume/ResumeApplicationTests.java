package com.yxy.resume;

import com.spire.xls.core.converter.spreadsheet.revision.Users;
import com.yxy.resume.api.MobileOCR;
import com.yxy.resume.common.R;
import com.yxy.resume.until.PdfUtils;
import io.swagger.annotations.ApiModelProperty;
import nonapi.io.github.classgraph.utils.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
class ResumeApplicationTests {
    @Autowired
    MobileOCR mobileOCR ;
    @Test
    void contextLoads() {

        File file = new File("C:\\Users\\Leaves_XY\\Desktop\\word\\7-Image-0.png");
        mobileOCR.covert(file);
    }

    @Test
    void test() throws IOException {
        File file = new File("C:\\Users\\Leaves_XY\\Desktop\\word\\56.pdf");
        System.out.println(PdfUtils.getTextFromPdf(file));

    }

}
