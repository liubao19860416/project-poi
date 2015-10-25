package com.saick.base.excel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * 将图片保存excel单元格指定位置
 * 
 * @author Liubao
 * @2014年12月16日
 * 
 */
public class ExeclPicture {

    public ExeclPicture() {
    }

    public static void main(String[] args) throws Exception {
        // 创建一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个表格
        HSSFSheet sheet = workbook.createSheet("sheet");
        // 创建一个列
        HSSFRow row = sheet.createRow(0);
        // 创建一个样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置这些样式
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 创建一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 6);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        cellStyle.setFont(font);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 填充单元格
        for (int i = 0; i < 5; i++) {
            // 创建一个单元格
            HSSFCell cell = row.createCell(i);
            switch (i) {
            case 0:
                // 设置普通文本
                cell.setCellValue(new HSSFRichTextString("普通文本"));
                break;
            case 1:
                // 设置为形状
                HSSFClientAnchor clientAnchor = new HSSFClientAnchor(0, 0, 023, 255,
                        (short) 1, 0, (short) 1, 0);
                HSSFSimpleShape simpleShape = patriarch.createSimpleShape(clientAnchor);
                // 这里可以设置形状的样式
                simpleShape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_OVAL);
                break;
            case 2:
                // 设置为布尔量
                cell.setCellValue(true);
                break;
            case 3:
                // 设置为double值
                cell.setCellValue(12.5);
                break;
            case 4:
                // 设置为图片]
                URL url = ExeclPicture.class.getResource("hello.jpg");
                insertImage(workbook, patriarch, getImageData(ImageIO.read(url)), 0,
                        4, 1);
                break;
            }
            // 设置单元格的样式
            cell.setCellStyle(cellStyle);
        }
        FileOutputStream out = new FileOutputStream("我的第一个EXCEL.xls");
        // 输出到文件
        workbook.write(out);
        out.close();
    }

    /**
     *  自定义的方法,插入某个图片到指定索引的位置
     */
    private static void insertImage(HSSFWorkbook hssfWorkbook, HSSFPatriarch hssfPatriarch,
            byte[] data, int row, int column, int index) {
        int x = index * 250;
        int y = 0;
        int x2 = x + 255;
        int y2 = 255;
        HSSFClientAnchor hssfClientAnchor = new HSSFClientAnchor(x, y, x2, y2,
                (short) column, row, (short) column, row);
        hssfClientAnchor.setAnchorType(2);
        hssfPatriarch.createPicture(hssfClientAnchor,
                hssfWorkbook.addPicture(data, HSSFWorkbook.PICTURE_TYPE_JPEG));
    }

    /**
     *  从图片里面得到字节数组
     */
    private static byte[] getImageData(BufferedImage bufferedImage) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
