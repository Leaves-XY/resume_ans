package com.yxy.resume;

import com.spire.doc.Document;
import com.spire.doc.ToPdfParameterList;
import com.spire.doc.documents.ImageType;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/7 12:11
 */
public class convert {

    public static void main(String[] args) {
//        for (int i = 1; i <= 100; i++) {
//            System.out.println(System.currentTimeMillis());
//            docxConvertPdf("C:\\Users\\Leaves_XY\\Desktop\\word\\" + i + ".docx", "C:\\Users\\Leaves_XY\\Desktop\\word\\" + i + ".pdf");
//        }

        for(int i = 1; i <= 100; i++){
            try {
                PdfToImg("C:\\Users\\Leaves_XY\\Desktop\\word\\" + i + ".pdf", "C:\\Users\\Leaves_XY\\Desktop\\word\\" + i +"-");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

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

    public static void ConvertWordToJPG(String source, String JPGDestination){
        Document doc=new Document();
        doc.loadFromFile(source);
        BufferedImage[] images = doc.saveToImages(ImageType.Bitmap);
        for (int i = 0; i < images.length; i++) {

            BufferedImage image = images[i];

            //Re-write the image with a different color space

            BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

            newImg.getGraphics().drawImage(image, 0, 0, null);

            //Write to a JPG file
            System.out.println(JPGDestination + String.format(("Image-%d.jpg"), i));
            File file = new File(JPGDestination + String.format(("Image-%d.jpg"), i));

            try {
                ImageIO.write(newImg, "JPEG", file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void PdfToImg(String source, String JPGDestination) throws IOException{
        PDDocument doc = Loader.loadPDF(new File(source));
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        for(int i=0;i<pageCount;i++){
            BufferedImage image = renderer.renderImageWithDPI(i, 296);

            ImageIO.write(image, "PNG", new File(JPGDestination + String.format(("Image-%d.png"), i)));
        }
    }





}






