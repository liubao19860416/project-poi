package com.saick.base.excel;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * excel导出2007版本文件的封装工具类
 */
public class ExcelExportSXXSSF<T> {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final String DEFAULT_FILESUFFIX=".xlsx";
    private static final String DEFAULT_FILEPREFIX="export_";
    
    static{
    }

    /**
     * 定义工作表对象
     */
    private SXSSFWorkbook workbook;
    /**
     * 定义工作表中的sheet
     */
    private Sheet sheet;
    /**
     * 定义保存在内存中的数量,-1表示关闭自动刷新,
     * 手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
     */
    private int flushRows=100;
    /**
     * 导出文件行数
     */
    private int rowNum=0;
    /**
     * 导出文件列数
     */
    private int colNum;
    /**
     * 导出文件的临时存放路径
     */
    private String exportFileTempPath;
    /**
     * 导出文件的下载路径
     */
    private String exportFileWebPath;
    /**
     * 导出文件全路径
     */
    private String fileAllPath;
    /**
     * 文件名称前缀
     */
    private String filePrefix;
    /**
     * 文件名称后缀
     */
    private String fileSuffix;
    /**
     * 导出文件的文件名(不包括前缀和后缀信息)
     */
    private String exportFileName;
    /**
     * 导出文件列标题
     */
    private List<String> titleList;
    /**
     * 导出文件每列代码，用于反射获取对象属性值
     */
    private List<String> fieldCodeList;

    /**
     * 开始导出方法参数，通过构造方法初始化
     */
    public ExcelExportSXXSSF(String exportFileTempPath, String exportFileName, int flushRows,List<String> titleList,
            List<String> fieldCodeList) {
        this.exportFileTempPath=exportFileTempPath;
        this.exportFileName=DEFAULT_FILEPREFIX+exportFileName+DEFAULT_FILESUFFIX;
        this.flushRows=flushRows;
        this.titleList=titleList;
        this.colNum = titleList.size();
        // 创建workbook
        this.workbook=new SXSSFWorkbook(flushRows);
        // 创建sheet
        this.sheet=workbook.createSheet();
        // 根据列标题得出列数
        this.fieldCodeList=fieldCodeList;
    }

    /**
     * 设置导入文件的标题 开始生成导出excel的标题
     */
    public void writeTitleList() throws Exception {
        // 标题永远是第0行
        rowNum=0;
        Row row = sheet.createRow(0);
        for (int cellNum = 0; cellNum < colNum; cellNum++) {
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(titleList.get(cellNum));
        }
    }

    /**
     *向导出文件写数据，写入List<T>类型的数据
     * 存放Object对象，仅支持单个自定义对象，不支持对象中嵌套自定义对象
     * 这时候，会将该对象的fieldCodeList中的字段全部导出去
     */
    public void writeDatasByObject(List<T> exportDatalist) throws Exception {
        //循环遍历导出数据
        for (int j = 0; j < exportDatalist.size(); j++) {
            rowNum = rowNum + 1;
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < fieldCodeList.size(); cellNum++) {
                T dataObj = exportDatalist.get(j);
                //获取该对象中的对应的属性的值
                Object value = invokeMethod(dataObj, fieldCodeList.get(cellNum),new Object[] {});
                //下面也可以，因为是泛型
                //value=invokeMethod(dataObj, fieldCodeList.get(cellNum));
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(value != null ? value.toString() : "");
            }
        }

    }

    /**
     * 向导出文件写数据，写入List<String>类型的数据，必须保证该字段顺序和fieldCodeList中的个数和顺序是一样的
     * 这个只能导入一行List<String>数据
     */
    public void writeDatasByString(List<String> exportDatalist) throws Exception {
        rowNum = rowNum + 1;
        Row row = sheet.createRow(rowNum);
        int datalistSize = exportDatalist.size();
        for (int cellnum = 0; cellnum < colNum; cellnum++) {
            Cell cell = row.createCell(cellnum);
            if (datalistSize > cellnum) {
                cell.setCellValue(exportDatalist.get(cellnum));
            } else {
                cell.setCellValue("");
            }

        }
    }

    /**
     * 手动刷新方法,如果flushRows为-1，则需要使用此方法手动刷新内存
     */
    public void flush(int flushNum) throws Exception {
        ((SXSSFSheet) sheet).flushRows(flushNum);
    }

    /**
     * 导出文件，文件名添加时间戳，返回下载文件web路径
     */
    public String exportFile() throws Exception {
        FileOutputStream out = new FileOutputStream(exportFileTempPath+ exportFileName);
        workbook.write(out);
        out.flush();
        out.close();
        this.exportFileWebPath=exportFileTempPath+ exportFileName;
        setFileAllPath(exportFileTempPath + exportFileName);
        return exportFileWebPath;
    }

    /**
     * 反射方法，通过get方法获取对象属性,args是该方法的参数列表，而这里我们只需要get方法，它都是没有参数的
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Object invokeMethod(T dataObj, String fieldname, Object[] args) throws Exception {
        String methodName = "get" + fieldname.substring(0, 1).toUpperCase()+ fieldname.substring(1);
        Class dataObjClass = dataObj.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = dataObjClass.getMethod(methodName, argsClass);
        return method.invoke(dataObj, args);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object invokeMethod(T dataObj, String fieldname) throws Exception {
        String methodName = "get" + fieldname.substring(0, 1).toUpperCase()+ fieldname.substring(1);
        Class dataObjClass = dataObj.getClass();
        Method method = dataObjClass.getMethod(methodName, new Class[]{});
        return method.invoke(dataObj, null);
        //return method.invoke(dataObj, new Class[]{});
    }

    /**
     * 对应的set和get方法
     */
    public SXSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(SXSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public int getFlushRows() {
        return flushRows;
    }

    public void setFlushRows(int flushRows) {
        this.flushRows = flushRows;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public String getExportFileTempPath() {
        return exportFileTempPath;
    }

    public void setExportFileTempPath(String exportFileTempPath) {
        this.exportFileTempPath = exportFileTempPath;
    }

    public String getExportFileWebPath() {
        return exportFileWebPath;
    }

    public void setExportFileWebPath(String exportFileWebPath) {
        this.exportFileWebPath = exportFileWebPath;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }

    public String getFileAllPath() {
        return fileAllPath;
    }

    public void setFileAllPath(String fileAllPath) {
        this.fileAllPath = fileAllPath;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public List<String> getFieldCodeList() {
        return fieldCodeList;
    }

    public void setFieldCodeList(List<String> fieldCodeList) {
        this.fieldCodeList = fieldCodeList;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getExportFileName() {
        return exportFileName;
    }

    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }


}
