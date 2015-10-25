package com.saick.base.excel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 将图片保存到2003格式的excel中
 * 
 * @author Liubao
 * @2014年12月14日
 * 
 */
public class PicToHSSFExcelTest {

    public static void main(String[] args) {
        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;
        BufferedImage bufferImg1 = null;
        try {
            // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            ByteArrayOutputStream byteArrayOut1 = new ByteArrayOutputStream();
            bufferImg = ImageIO.read(new File("d:/temp/002.jpg"));
            bufferImg1 = ImageIO.read(new File("d:/temp/003.jpg"));
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            ImageIO.write(bufferImg1, "jpg", byteArrayOut1);

            // 创建一个工作薄
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet1 = wb.createSheet("new sheet");
            // HSSFRow row = sheet1.createRow(2);
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 512, 255,
                    (short) 1, 1, (short) 10, 20);
            HSSFClientAnchor anchor1 = new HSSFClientAnchor(0, 0, 512, 255,
                    (short) 2, 30, (short) 10, 60);
            anchor1.setAnchorType(2);
            // 插入图片
            patriarch
                    .createPicture(anchor, wb.addPicture(
                            byteArrayOut.toByteArray(),
                            HSSFWorkbook.PICTURE_TYPE_JPEG));
            patriarch.createPicture(anchor1,
                    wb.addPicture(byteArrayOut1.toByteArray(),
                            HSSFWorkbook.PICTURE_TYPE_JPEG));

            fileOut = new FileOutputStream("d:/temp/picture.xls");
            // 写入excel文件
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("io erorr : " + io.getMessage());
        } finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
                fileOut = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}