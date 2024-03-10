package com.github.zhoujiale.commons.util.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * PDF工具类
 */
@Slf4j
public class PdfUtils {

    /**
     * PDF转html图片
     *
     * @param file
     * @return
     */
    public static String pdfToHtml(File file) {
        BufferedImage bufferedImage = pdfToImg(file);
        String result = bufferedImageToBase64(bufferedImage);
        return new StringBuilder("<img src=\"data:image/png;base64,")
                .append(result)
                .append("\">").toString();
    }

    /**
     * pdf文件转图片
     *
     * @param file
     * @return
     * @throws IOException
     */
    private static BufferedImage pdfToImg(File file) {
        try {
            PDDocument pdDocument = Loader.loadPDF(new RandomAccessReadBufferedFile(file));
            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
            int numberOfPages = pdDocument.getNumberOfPages();
            BufferedImage image = null;
            for (int i = 0; i < numberOfPages; i++) {
                image = i == 0 ? pdfRenderer.renderImageWithDPI(i, 72) : combineBufferedImages(image);
            }
            return combineBufferedImages(image);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("PDF转图片失败");
        }
    }

    /**
     * BufferedImage拼接处理添加分割线
     *
     * @param images
     * @return
     */
    private static BufferedImage combineBufferedImages(BufferedImage... images) {
        int height = 0;
        int width = 0;
        for (BufferedImage image : images) {
            height += image.getHeight();
            width += image.getWidth();
        }
        BufferedImage combo = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = combo.createGraphics();
        int x = 0;
        int y = 0;
        for (BufferedImage image : images) {
            //线条粗细
            graphics.setStroke(new BasicStroke(2.0f));
            //线条颜色
            graphics.setColor(new Color(193, 193, 193));
            //线条起点及终点位置
            graphics.drawLine(x, y, width, y);
            graphics.drawImage(image, x, y, null);
            y += image.getHeight();
        }
        return combo;
    }

    private static String bufferedImageToBase64(BufferedImage bufferedImage) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(bytes)
                    .trim()
                    .replaceAll("\n", "")
                    .replaceAll("\r", "");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("图片转base64失败");
        }
    }
}
