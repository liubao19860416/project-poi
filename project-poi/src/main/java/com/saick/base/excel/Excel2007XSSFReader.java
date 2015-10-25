package com.saick.base.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel2007版本的excel读取（导入import）
 * 
 * 代码几乎一样，唯一不同的是所使用的对象的区别，Excel03使用到的对象是HSSF开头的，而Excel07使用的是XSSF开头的
 * 
 * @author Liubao
 * @2014年12月8日
 * 
 */
public class Excel2007XSSFReader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final String EXCEL2003REGEX = "^.+\\.(?i)(xls)$";
    private static final String EXCEL2007REGEX = "^.+\\.(?i)(xlsx)$";

    private InputStream is;
    private XSSFWorkbook wb;
    private XSSFSheet[] sheets;
    private XSSFSheet sheet;
    private XSSFRow row;
    private int sheetNum;
    private int rowNum;
    private int colNum;

    public static void main(String[] args) throws Exception {
        String path = "D:\\temp\\订单信息列表.xlsx";
        Excel2007XSSFReader excel = new Excel2007XSSFReader(path);
        excel.setSheet(0);
        System.out.println("总行数：" + excel.getRowNum());
        System.out.println("总列数：" + excel.getColNum());
        for (int i = 0; i < excel.getRowNum(); i++) {
            List<String> values = excel.getRowValues(i);
            for (int j = 0; j < values.size(); j++) {
                System.out.print(values.get(j) + " ");
                if (j == values.size() - 1)
                    System.out.println();
            }
        }
    }

    /**
     * 读取excel文件path的方法入口
     */
    public Excel2007XSSFReader(String path) throws Exception {
        if (!path.trim().toLowerCase().endsWith(".xlsx")) {
            throw new Exception("不是有效的2007Excel文件");
        }
        if (checkFile(path)) {
            try {
                is = new FileInputStream(path);
                wb = new XSSFWorkbook(is);
                // 初始化位置
                initSheet();
            } catch (FileNotFoundException e) {
                throw new Exception("读取的目标文件不存在");
            } catch (IOException e) {
                throw new Exception("打开Excel文件失败");
            }
        } else {
            throw new Exception("无法使用在其他进程或者程序已经打开的文件");
        }

    }

    /**
     * 初始化Excel文件的信息
     */
    public void initSheet() {
        sheetNum = wb.getNumberOfSheets();
        sheets = new XSSFSheet[sheetNum];
        for (int i = 0; i < sheetNum; i++) {
            sheets[i] = wb.getSheetAt(i);
        }
    }

    /**
     * 设置待操作的工作页
     */
    public void setSheet(int index) throws Exception {
        if (sheets == null || sheetNum <= index) {
            throw new Exception("无法获取无效的工作页");
        }
        sheet = sheets[index];
        rowNum = getRowNum();
        colNum = getColNum();
    }

    /**
     * 获取当前的Row行数
     */
    public int getRowNum() throws Exception {
        if (rowNum != 0) {
            return rowNum;
        }
        if (sheet != null) {
            rowNum = getRowNum(sheet);
            return rowNum;
        } else {
            throw new Exception("无法获取无效的工作页的总行数");
        }
    }

    /**
     * 获取指定工作页面的列数
     */
    public int getColNum() throws Exception {
        if (sheet == null) {
            throw new Exception("未指定操作的工作页");
        }
        if (colNum != 0) {
            return colNum;
        }
        XSSFRow row = sheet.getRow(0);
        this.colNum = getCellNum(row);
        return colNum;
    }

    /**
     * 获取sheet总Cell数
     */
    public int getCellNum(XSSFRow row) {
        int first = row.getFirstCellNum();
        int last = row.getLastCellNum();
        int cellNum = last - first;
        return cellNum;
    }

    /**
     * 获取sheet总行数
     */
    public int getRowNum(XSSFSheet sheet) {
        int first = sheet.getFirstRowNum();
        int last = sheet.getLastRowNum();
        int rowCount = last - first + 1;
        return rowCount;
    }

    /**
     * 选中指定行数据
     */
    public void setRow(int index) throws Exception {
        if (sheet != null) {
            if (index > rowNum) {
                throw new Exception("指定获取的行" + index + "超出了最大行数" + rowNum);
            }
            row = sheet.getRow(index);
        } else {
            throw new Exception("未指定操作的工作页");
        }
    }

    /**
     * 检查文件是否有其他程序或者进程在使用
     */
    public boolean checkFile(String path) throws Exception {
        boolean result = false;
        File file = new File(path);
        if (!file.exists()) {
            throw new Exception("指定操作的目标文件不存在");
        } else {
            File nFile = new File(path);
            result = file.renameTo(nFile);
        }
        return result;
    }

    /**
     * 获取一行index的第0列的数据List
     */
    public List<String> getRowValues(int index) throws Exception {
        return getCellValues(index, 0);
    }

    /**
     * 获取行数index的第col列的List数据值
     */
    public List<String> getCellValues(int index, int col) throws Exception {
        if (sheet != null) {
            if (index > rowNum) {
                throw new Exception("指定获取的行" + index + "超出了最大行数" + rowNum);
            }
            // 设置当前行标
            setRow(index);

            if (col == 0) {
                col = colNum;
            }
            List<String> values = new ArrayList<String>(col);

            for (int i = 0; i < col; i++) {
                values.add(getCellToString(i));
            }
            row = null;
            return values;
        } else {
            throw new Exception("未指定操作的工作页");
        }
    }

    /**
     * 获取指定单元格cellIndex的内容
     */
    public String getCellToString(int i) throws Exception {
        if (i > colNum) {
            throw new Exception("请求获取的单元格不存在");
        }

        short index = (short) i;
        XSSFCell cell = row.getCell(index);
        String str = getCellFormatValue(cell);
        return str;
    }

    /**
     * 关闭InputStream流
     */
    public void close() {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is = null;
        }
    }

    /**
     * 获取当前sheet的长度（个数）
     */
    public int getSheetNum() throws Exception {
        if (sheets != null) {
            return sheets.length;
        } else {
            throw new Exception("无效的Excel文件");
        }
    }

    /**
     * 依据内容判断是否为excel2007及以上
     */
    public static boolean isExcel2007(String filePath) {
        boolean result = false;
        try {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(filePath));
            result = POIXMLDocument.hasOOXMLHeader(bis);
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * 依据内容判断是否为excel2003及以下
     */
    public static boolean isExcel2003(String filePath) {
        boolean result = false;
        try {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(filePath));
            result = POIXMLDocument.hasOOXMLHeader(bis);
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * 依据后缀名判断读取的是否为Excel文件
     */
    public static boolean isExcelByFileNameSuffix(String fileName) {
        boolean result = false;
        if (fileName.matches(EXCEL2003REGEX)
                || fileName.matches(EXCEL2007REGEX)) {
            result = true;
        }
        return result;
    }

    // ===========================私有方法列表===========================

    private String getCellFormatValue(XSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case XSSFCell.CELL_TYPE_NUMERIC:
            case XSSFCell.CELL_TYPE_FORMULA:
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);

                } else { // 如果是纯数字；取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                    if (cellvalue.endsWith(".0")) {
                        cellvalue = cellvalue.replace(".0", "");
                    }
                }
                break;
            // 如果当前Cell的Type为STRING，取得当前的Cell字符串
            case XSSFCell.CELL_TYPE_STRING:
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                boolean comment = cell.getBooleanCellValue();
                cellvalue = comment ? "Y" : "N";
                break;
            // 默认的Cell值
            default:
                cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

}