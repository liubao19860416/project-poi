package com.saick.base.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * excel样式设计汇总
 * 
 * @author Liubao
 * @2014年12月12日
 * 
 */
public class ExcelStyle {

    // 在单元格中换行
    public static void method02() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet s = wb.createSheet();
        HSSFRow r = null;
        HSSFCell c = null;
        HSSFCellStyle cs = wb.createCellStyle();
        HSSFFont f = wb.createFont();
        HSSFFont f2 = wb.createFont();
        cs = wb.createCellStyle();
        cs.setFont(f2);
        // 开启Word Wrap
        cs.setWrapText(true);
        r = s.createRow((short) 2);
        r.setHeight((short) 0x349);
        c = r.createCell((short) 2);
        c.setCellType(HSSFCell.CELL_TYPE_STRING);
        c.setCellValue("Use n with word wrap on to create a new line");
        c.setCellStyle(cs);
        s.setColumnWidth((short) 2, (short) ((50 * 8) / ((double) 10 / 20)));
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 数据格式化
    public static void method03() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("format sheet");
        HSSFCellStyle style;
        HSSFDataFormat format = wb.createDataFormat();
        HSSFRow row;
        HSSFCell cell;
        short rowNum = 0;
        short colNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(colNum);
        cell.setCellValue(11111.25);
        style = wb.createCellStyle();
        style.setDataFormat(format.getFormat("0.0"));
        cell.setCellStyle(style);
        row = sheet.createRow(rowNum++);
        cell = row.createCell(colNum);
        cell.setCellValue(11111.25);
        style = wb.createCellStyle();
        style.setDataFormat(format.getFormat("#,##0.0000"));
        cell.setCellStyle(style);
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 使得一个Sheet适合一页
    public static void method04() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("format sheet");
        HSSFPrintSetup ps = sheet.getPrintSetup();
        sheet.setAutobreaks(true);
        ps.setFitHeight((short) 1);
        ps.setFitWidth((short) 1);
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 设置打印区域
    public static void method05() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet1");
        wb.setPrintArea(0, "$A$1:$C$2");
        // 为第一个Sheet页设置打印区域
        // 也可以这样
        // wb.setPrintArea(0, 0, 1, 0, 0) ，详细参考java doc
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 设置页脚的页数
    public static void method06() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("format sheet");
        HSSFFooter footer = sheet.getFooter();
        footer.setRight("Page " + HSSFFooter.page() + " of "
                + HSSFFooter.numPages());
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 使用简便的函数
    // 这些函数保存在contrib并且提供了一些使用特征功能，例如设置合并单元格的边框，不用创建新样式改变样式属性。
    public static void method07() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("new sheet");
        // 合并单元格
        HSSFRow row = sheet1.createRow((short) 1);
        HSSFRow row2 = sheet1.createRow((short) 2);
        HSSFCell cell = row.createCell((short) 1);
        cell.setCellValue("This is a test of merging");
        Region region = new Region(1, (short) 1, 4, (short) 4);
        sheet1.addMergedRegion(region);
        // 设置边框和颜色.
        final short borderMediumDashed = HSSFCellStyle.BORDER_MEDIUM_DASHED;
        HSSFRegionUtil.setBorderBottom(borderMediumDashed, region, sheet1, wb);
        HSSFRegionUtil.setBorderTop(borderMediumDashed, region, sheet1, wb);
        HSSFRegionUtil.setBorderLeft(borderMediumDashed, region, sheet1, wb);
        HSSFRegionUtil.setBorderRight(borderMediumDashed, region, sheet1, wb);
        HSSFRegionUtil.setBottomBorderColor(HSSFColor.AQUA.index, region,
                sheet1, wb);
        HSSFRegionUtil.setTopBorderColor(HSSFColor.AQUA.index, region, sheet1,
                wb);
        HSSFRegionUtil.setLeftBorderColor(HSSFColor.AQUA.index, region, sheet1,
                wb);
        HSSFRegionUtil.setRightBorderColor(HSSFColor.AQUA.index, region,
                sheet1, wb);
        // 展示HSSFCellUtil类的用法
        HSSFCellStyle style = wb.createCellStyle();
        style.setIndention((short) 4);
        HSSFCellUtil.createCell(row, 8, "This is the value of the cell", style);
        HSSFCell cell2 = HSSFCellUtil.createCell(row2, 8,
                "This is the value of the cell");
        HSSFCellUtil.setAlignment(cell2, wb, HSSFCellStyle.ALIGN_CENTER);
        // 写入文件
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 在Sheet页中上下移动行
    public static void method08() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("row sheet");
        // 创建不同的行列
        // 将6-11行移动到0-5行
        sheet.shiftRows(5, 10, -5);
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 设置一个Sheet页为被选中的
    public static void method09() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("row sheet");
        sheet.setSelected(true);
        // 创建不同的行列。。。
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 设置放大属性
    public static void method10() throws Exception {
        // The zoom is expressed as a fraction. For example to express a zoom of
        // 75% use 3 for the numerator and 4 for the denominator.
        // 3Zoom被明确为一个分数，例如下面的75%使用3作为分子，4作为分母。
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("new sheet");
        sheet1.setZoom(3, 4); // 75%放大
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 反复的行和列（设置打印标题）
    public static void method11() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("new sheet");
        HSSFSheet sheet2 = wb.createSheet("second sheet");
        // 第一个Sheet设置从0到2的列
        wb.setRepeatingRowsAndColumns(0, 0, 2, -1, -1);
        // 第二个Sheet设置行和列
        wb.setRepeatingRowsAndColumns(1, 4, 5, 1, 2);
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    // 页眉和页脚
    // 这是个页眉的例子，但是页脚也同样适用/
    public static void method12() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");
        HSSFHeader header = sheet.getHeader();
        header.setCenter("Center Header");
        header.setLeft("Left Header");
        header.setRight(HSSFHeader.font("Stencil-Normal", "Italic")
                + HSSFHeader.fontSize((short) 16)
                + "Right w/ Stencil-Normal Italic font and size 16");
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

}