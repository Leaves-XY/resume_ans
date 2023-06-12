package com.yxy.resume.until;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/7 23:56
 */
public class PdfUtils {
    public static int getPageCount(File pdfFile) {
        int numberOfPages = 0;
        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            numberOfPages = document.getNumberOfPages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfPages;
    }

    public static boolean isPdf(File file) {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        return extension.toLowerCase().equals("pdf");
    }
}
