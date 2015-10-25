package com.saick.base.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 将图片保存到2003格式的excel中2
 * 
 * @author Liubao
 * @2014年12月14日
 * 
 */
public class PicToHSSFExcelTest2 {

    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream("D:/temp/picbook.xls");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("picture");
        HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
        HSSFClientAnchor anchor1 = new HSSFClientAnchor(0, 0, 0, 0, (short) 0,
                0, (short) 8, 20);
        HSSFClientAnchor anchor2 = new HSSFClientAnchor(0, 0, 0, 0, (short) 0,
                24, (short) 8, 44);
        patriarch.createPicture(anchor1, wb.addPicture(
                loadImage("d:/temp/002.jpg"), HSSFWorkbook.PICTURE_TYPE_JPEG));
        patriarch.createPicture(anchor2, wb.addPicture(
                loadImage("d:/temp/003.jpg"),
                HSSFWorkbook.PICTURE_TYPE_JPEG));
        wb.write(out);
        out.close();
    }

    /**
     * 加载图片
     */
    public static byte[] loadImage(String filePath) {
        try {
            FileInputStream input = new FileInputStream(new File(filePath));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
           /* for (;;) {
                int noBytesRead = input.read(buf);
                if (noBytesRead == -1) {
                    return output.toByteArray();
                }
                output.write(buf, 0, noBytesRead);
            }*/
            int len =-1;
            while(( len = input.read(buf))!=-1){
                output.write(buf, 0, len);
            }
            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
