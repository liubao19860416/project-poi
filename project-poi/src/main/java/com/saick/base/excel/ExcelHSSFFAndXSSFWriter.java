package com.saick.base.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成excel2003和2007格式文件
 * 
 */
public class ExcelHSSFFAndXSSFWriter {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    // 标题字体
    private Font titleFont = null;
    //private HSSFFont titleFont = null;
    // private XSSFFont titleFont = null; //2007格式

    // 标题样式
    private CellStyle titleStyle = null;
    //private HSSFCellStyle titleStyle = null;
    // private XSSFCellStyle titleStyle = null;//2007格式

    // 行信息内容样式
    private CellStyle contentStyle = null;
    //private HSSFCellStyle contentStyle = null;
    // private XSSFCellStyle contentStyle = null;//2007格式

    /** 测试 */
    public static void main(String[] args) {
        ExcelHSSFFAndXSSFWriter we = new ExcelHSSFFAndXSSFWriter();
        String[] titleStrs = { "部门", "城市", "日期", "金额" };

        List<String[]> contentList = new ArrayList<String[]>();
        String[] contents1 = { "财务部", "北京", "2013-07-25", "1000.25" };
        String[] contents2 = { "销售部", "深圳", "2013-08-01", "0.099" };
        String[] contents3 = { "产品部", "天津", "2013-11-17", "18888888" };
        String[] contents4 = { "市场部", "上海", "2013-12-10", "5658978987.135454的" };
        contentList.add(contents1);
        contentList.add(contents2);
        contentList.add(contents3);
        contentList.add(contents4);
        String filename = "WriteExcel";
        try {
            we.writeExcel2003(titleStrs, contentList, filename);
            we.writeExcel2007(titleStrs, contentList, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写excel2003文件
     */
    public void writeExcel2003(String[] titleStrs, List<String[]> contentList,
            String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("D:\\temp\\订单信息列表2.xls");
        /*
         * severlet响应生成excel文件 HttpServletResponse response
         * 
         * // 文件标题 String head = new String(filename.getBytes("GB2312"),
         * "ISO-8859-1"); response.reset();
         * response.setContentType("application/vnd.ms-excel");
         * response.addHeader("Content-Disposition", "attachment; filename="+
         * head + ".xls");
         * 
         * HSSFWorkbook wb = new HSSFWorkbook(); 。。。。。
         * 
         * java.io.OutputStream os = response.getOutputStream(); wb.write(os);
         * os.close();
         */

        HSSFWorkbook wb = new HSSFWorkbook();// 创建新HSSFWorkbook对象
        // XSSFWorkbook wb = new XSSFWorkbook();//2007格式

        // 执行样式初始化
        setHSSFExcelStyle(wb);

        HSSFSheet sheet = wb.createSheet(filename);// 创建新的sheet对象
        // XSSFSheet sheet = wb.createSheet(filename);//2007格式

        HSSFRow titleRow = sheet.createRow((short) 0);// 创建第一行
        // XSSFRow titleRow = sheet.createRow((short) 0);//2007格式

        // titleRow.setHeight((short)300);//设置行高,设置太小可能被隐藏看不到
        titleRow.setHeightInPoints(20);// 20像素
        int titleCount = titleStrs.length;// 列数
        // 写标题
        for (int k = 0; k < titleCount; k++) {
            HSSFCell cell = titleRow.createCell((short) k); // 新建一个单元格
            // XSSFCell cell = titleRow.createCell((short) k); //2007格式

            // cell.setEncoding(HSSFCell.ENCODING_UTF_16); // 中文字符集转换
            cell.setCellStyle(titleStyle);// 设置标题样式
            // cell.setCellValue(new HSSFRichTextString(titleStrs[k])); //
            // 为单元格赋值
            // cell.setCellValue(wb.getCreationHelper().createRichTextString(""));
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(titleStrs[k]);
            sheet.setColumnWidth((short) k, (short) 5000);// 设置列宽
        }

        int contentCount = contentList.size();// 总的记录数
        // 写内容
        for (int i = 0; i < contentCount; i++) {
            String[] contents = contentList.get(i);
            HSSFRow row = sheet.createRow((short) (i + 1)); // 新建一行
            // XSSFRow row = sheet.createRow((short)(i + 1)); // //2007格式

            for (int j = 0; j < titleCount; j++) {
                HSSFCell cell = row.createCell((short) j); // 新建一个单元格
                // XSSFCell cell = row.createCell((short) j); // //2007格式

                cell.setCellStyle(contentStyle);// 设置内容样式
                if (contents[j] == null || contents[j].equals("null")) {
                    contents[j] = "";
                }
                // 格式化日期
                if (j == 2) {
                    HSSFCellStyle style = wb.createCellStyle();
                    // XSSFCellStyle style = wb.createCellStyle();//2007格式
                    style.setDataFormat(wb.getCreationHelper()
                            .createDataFormat()
                            .getFormat("yyyy-mm-dd hh:mm:ss"));
                    // cell.setCellValue(new Date());
                    // cell.setCellValue(Calendar.getInstance());
                    cell.setCellValue(contents[j]);
                    cell.setCellStyle(style);
                } else {
                    cell.setCellValue(new HSSFRichTextString(contents[j]));
                }
            }
        }
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
    /**
     * 写excel2003文件
     */
    public void writeExcel2007(String[] titleStrs, List<String[]> contentList,String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("D:\\temp\\订单信息列表2.xlsx");
        /*
         * severlet响应生成excel文件 HttpServletResponse response
         * 
         * // 文件标题 String head = new String(filename.getBytes("GB2312"),
         * "ISO-8859-1"); response.reset();
         * response.setContentType("application/vnd.ms-excel");
         * response.addHeader("Content-Disposition", "attachment; filename="+
         * head + ".xls");
         * 
         * HSSFWorkbook wb = new HSSFWorkbook(); 。。。。。
         * 
         * java.io.OutputStream os = response.getOutputStream(); wb.write(os);
         * os.close();
         */
        
         XSSFWorkbook wb = new XSSFWorkbook();//2007格式
        
         setXSSFExcelStyle(wb);// 执行样式初始化
        
         XSSFSheet sheet = wb.createSheet(filename);//2007格式
        
         XSSFRow titleRow = sheet.createRow((short) 0);//2007格式
        
         titleRow.setHeight((short)300);//设置行高,设置太小可能被隐藏看不到
        titleRow.setHeightInPoints(20);// 20像素
        int titleCount = titleStrs.length;// 列数
        // 写标题
        for (int k = 0; k < titleCount; k++) {
//            HSSFCell cell = titleRow.createCell((short) k); // 新建一个单元格
             XSSFCell cell = titleRow.createCell((short) k); //2007格式
            
             //cell.setEncoding(HSSFCell.ENCODING_UTF_16); // 中文字符集转换
            cell.setCellStyle(titleStyle);// 设置标题样式
             //cell.setCellValue(new HSSFRichTextString(titleStrs[k])); //
            // 为单元格赋值
             cell.setCellValue(wb.getCreationHelper().createRichTextString(""));
//            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue(titleStrs[k]);
            sheet.setColumnWidth((short) k, (short) 5000);// 设置列宽
        }
        
        int contentCount = contentList.size();// 总的记录数
        // 写内容
        for (int i = 0; i < contentCount; i++) {
            String[] contents = contentList.get(i);
//            HSSFRow row = sheet.createRow((short) (i + 1)); // 新建一行
             XSSFRow row = sheet.createRow((short)(i + 1)); // //2007格式
            
            for (int j = 0; j < titleCount; j++) {
//                HSSFCell cell = row.createCell((short) j); // 新建一个单元格
                 XSSFCell cell = row.createCell((short) j); // //2007格式
                
                cell.setCellStyle(contentStyle);// 设置内容样式
                if (contents[j] == null || contents[j].equals("null")) {
                    contents[j] = "";
                }
                // 格式化日期
                if (j == 2) {
//                    HSSFCellStyle style = wb.createCellStyle();
                     XSSFCellStyle style = wb.createCellStyle();//2007格式
                    style.setDataFormat(wb.getCreationHelper()
                            .createDataFormat()
                            .getFormat("yyyy-mm-dd hh:mm:ss"));
                    // cell.setCellValue(new Date());
                    // cell.setCellValue(Calendar.getInstance());
                    cell.setCellValue(contents[j]);
                    cell.setCellStyle(style);
                } else {
                    //cell.setCellValue(new HSSFRichTextString(contents[j]));
                }
            }
        }
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    /**
     * 样式初始化设置
     */
    private void setXSSFExcelStyle(XSSFWorkbook workBook) {
        // 设置列标题字体，样式
        titleFont = workBook.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        // 标题列样式
        titleStyle = workBook.createCellStyle();
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置边框
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setFont(titleFont);
        
        // 内容列样式
        contentStyle = workBook.createCellStyle();
        contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        
    }
    
    /**
     * 样式初始化设置
     */
    private void setHSSFExcelStyle(HSSFWorkbook workBook) {
        // 设置列标题字体，样式
        titleFont = workBook.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        // 标题列样式
        titleStyle = workBook.createCellStyle();
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置边框
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setFont(titleFont);
        
        // 内容列样式
        contentStyle = workBook.createCellStyle();
        contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        
    }
    

}
