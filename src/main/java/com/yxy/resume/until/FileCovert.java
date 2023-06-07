package com.yxy.resume.until;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.ToPdfParameterList;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;



/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/7 13:46
 */
public class FileCovert {
    /**
     * docx转pdf
     * @param source
     * @param pdfDestination
     */
    public static void docxConvertPdf(String source, String pdfDestination) {
        try {
            // 创建doc对象
            Document doc = new Document();
            // 加载docx文件
            doc.loadFromFile(source);
            // 创建ToPdfParameterList对象
            ToPdfParameterList ppl = new ToPdfParameterList();
            // 在PDF文档中嵌入所有字体
            ppl.isEmbeddedAllFonts(true);
            // 删除超链接并保留字符格式
            ppl.setDisableLink(true);
            // 设置输出图像质量为原始图像的40%，默认设置为80%。
            doc.setJPEGQuality(40);
            // 转为pdf
            doc.saveToFile(pdfDestination, ppl);
//            System.out.println("docx文件转为pdf成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * docx转pdf
     * @param file
     */
    public static File docxConvertPdf(File file) {
        try {
            // 创建doc对象
            Document doc = new Document();

            // 将File转为InputStream
            InputStream inputStream = new FileInputStream(file);
            doc.loadFromStream(inputStream, FileFormat.Docx_2013);

            // 创建ToPdfParameterList对象
            ToPdfParameterList ppl = new ToPdfParameterList();
            // 在PDF文档中嵌入所有字体
            ppl.isEmbeddedAllFonts(true);
            // 删除超链接并保留字符格式
            ppl.setDisableLink(true);
            // 设置输出图像质量为原始图像的40%，默认设置为80%。
            doc.setJPEGQuality(40);

            // 将转换后的PDF保存到临时文件中
            File tempFile = File.createTempFile("converted", ".pdf");
            doc.saveToFile(tempFile.getAbsolutePath(), ppl);

            return tempFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * pdf转png
     * @param source
     * @param PNGDestination
     * @throws IOException
     */
    public static void pdfConvertPNG(String source, String PNGDestination) throws IOException {
        PDDocument doc = Loader.loadPDF(new File(source));
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        for(int i=0;i<pageCount;i++){
            BufferedImage image = renderer.renderImageWithDPI(i, 200);
            ImageIO.write(image, "PNG", new File(PNGDestination + String.format(("Image-%d.png"), i)));
        }
    }

    /**
     * word转png
     * @param sourceWordFilePath
     * @param targetPngFolderPath
     * @throws IOException
     */
    public static void wordConvertPng(String sourceWordFilePath, String targetPngFolderPath) throws IOException {
        // Convert the Word file to a PDF file
        String tempPdfFilePath = "temp.pdf";  // you may want to use a unique file name here
        docxConvertPdf(sourceWordFilePath, tempPdfFilePath);

        // Convert the PDF file to PNG images
        pdfConvertPNG(tempPdfFilePath, targetPngFolderPath);

        // Delete the temporary PDF file
        new File(tempPdfFilePath).delete();
    }

    /**
     * MultipartFile转File
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        // 创建临时文件
        File tempFile = File.createTempFile("temp-", "-" + multipartFile.getOriginalFilename());

        // 将MultipartFile的内容复制到临时文件中
        try (InputStream inputStream = multipartFile.getInputStream();
             FileOutputStream fos = new FileOutputStream(tempFile)) {
            FileCopyUtils.copy(inputStream, fos);
        }

        return tempFile;
    }




}
