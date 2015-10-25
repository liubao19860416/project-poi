package com.saick.base.excel.jxl;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接来下将以下JXL的操作，需要下载jxl.jar包， 不过目前好像已经没有团队在维护该jar包了，而且该工具包不支持EXCEL07的操作
 * 
 * @author Liubao
 * @2014年12月8日
 * 
 */
public class Excel2003JXLReader {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private Workbook wb;
    private Sheet[] sheets;
    private Sheet sheet;
    private int rowCount;
    private int colCount;

    public static void main(String[] args) throws Exception {
        String path = "D:\\temp\\订单信息列表.xls";
        Excel2003JXLReader excel = new Excel2003JXLReader(path);
        excel.setSheet(0);
        System.out.println("总行数：" + excel.getRowNum());
        System.out.println("总列数：" + excel.getColNum());
        for (int i = 0; i < excel.getRowNum(); i++) {
            List<String> values = excel.getCellValues(i);
            for (int j = 0; j < values.size(); j++) {
                System.out.print(values.get(j) + " ");
            }
            System.out.println();
        }
    }

    public Excel2003JXLReader(InputStream inputStream) {
        try {
            wb = Workbook.getWorkbook(inputStream);
            sheets = wb.getSheets();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Workbook getWorkbook() {
        return wb;
    }

    public Excel2003JXLReader(String filePath) {
        File file = new File(filePath);
        init(file);
    }

    public Excel2003JXLReader(File file) {
        init(file);
    }

    private void init(File file) {
        try {
            wb = Workbook.getWorkbook(file);
            sheets = wb.getSheets();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSheet(int index) {
        if (index >= sheets.length){
            throw new IndexOutOfBoundsException("不存在该页数");
        }
        sheet = sheets[index];
        rowCount = sheet.getRows();
        colCount = sheet.getColumns();
    }

    public int getSheetNum() {
        if (sheets == null || sheets.length == 0){
            throw new NullPointerException("该文件没有工作页面");
        }
        return sheets.length;
    }

    public int getRowNum() {
        if (sheet == null){
            throw new NullPointerException("未设定使用工作页面");
        }
        if (rowCount <= 0){
            rowCount = sheet.getRows();
        }
        return rowCount;
    }

    public int getColNum() {
        if (sheet == null){
            throw new NullPointerException("未设定使用工作页面");
        }
        if (colCount <= 0){
            colCount = sheet.getColumns();
        }
        return colCount;
    }

    public String[] getRowValues(int index) {
        List<String> values = getCellValues(index);
        int length = values.size();
        Arrays.asList(values);
        return (String[]) values.toArray(new String[length]);
    }

    public List<String> getCellValues(int index) {
        String[] v = getCellValues(index, 0);
        List<String> values = Arrays.asList(v);
        return values;
    }

    public String[] getCellValues(int index, int count) {
        if (sheet == null){
            throw new NullPointerException("未设定使用工作页面");
        }
        if (index > getRowNum()){
            throw new IndexOutOfBoundsException("不存在操作行");
        }
        Cell[] cells = sheet.getRow(index);
        if (count == 0){
            count = cells.length;
        }
        String[] values = new String[count];
        for (int i = 0; i < count; i++) {
            values[i] = cells[i].getContents();
        }
        return values;
    }

    public void close() {
        wb.close();
        wb = null;
    }
}
