package com.saick.base.excel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saick.base.service.api.ExcelImportService;
/**
 * 支持2003版本xls格式的excel文件读取导入工具类，和HxlsAbstract配合使用
 * @author Liubao
 * @param <T>
 * @2014年12月8日
 *
 */
public class ExcelHSSFReader<T> extends HxlsAbstract {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    
    // 处理数据总数
    private int rowsTotalNum = 0;
    // 处理数据成功数量
    private int rowsSuccessNum = 0;
    // 处理数据失败数量
    private int rowsFailureNum = 0;

    /**
     *  excel表格每列标题
     */
    private List<String> titleList=new ArrayList<String>();
    /**
     *  excel表格对应的实体Bean的属性字段信息
     */
    private List<String> fieldCodeList=new ArrayList<String>();
    /**
     *  失败数据列表，列表信息格式为List<T>，T为定义的泛型对象信息，但是T只能是单一自定义对象，不能够嵌套对象
     */
    private List<T> objectTListFail=new ArrayList<T>();
    /**
     *  失败数据列表,列表信息格式为List<List<String>>
     */
    private List<List<String>> dataListFail=new ArrayList<List<String>>();
    // 失败原因列表
    private List<String> detailListFail=new ArrayList<String>();

    // 要处理数据所在的sheet索引,sheet索引从0开始
    private int sheetIndex=0;

    //导出服务的基础service接口，自定义的类都会实现该接口
    private ExcelImportService<T> excelImportService;
    
    /**
     * 导入文件的名称的物理路径，要读取数据所在sheet序号
     * 处理读取每一行数据的接口
     * 注入了导出excel服务的service类
     * 执行顺序：1
     */
    public ExcelHSSFReader(String filename, int sheetIndex,ExcelImportService<T> excelImportService ) throws Exception{
        super(filename);
        this.sheetIndex = sheetIndex;
        this.titleList = new ArrayList<String>();
        this.excelImportService = excelImportService;
        
        this.fieldCodeList = excelImportService.getFieldCodeList();
    }

    /**
     * 对读取到一行数据进行解析,rowlist为当前读取到的那行数据
     * sheetIndex和curRow都是从0开始的
     * 执行顺序：6
     */
    @Override
    public void optRows(int sheetIndex, int curRow, List<String> rowlist) throws Exception {
        // 将rowlist的长度补齐和标题一致
        int k = titleList.size() - rowlist.size();
        for (int i = 0; i < k; i++) {
            rowlist.add(null);
        }
        if (sheetIndex == this.sheetIndex) {
            if (curRow == 0) {
                // 记录标题
                titleList.addAll(rowlist);
            } else {
                //标题行没有记录在总条数内。
                rowsTotalNum++;
                
                /**
                 * 调用对应的导出服务接口，执行导出任务
                 * 返回的结果是导入数据的结果，有成功，有失败
                 * 进行数据读取，一行行的进行数据操作和记录
                 */
                String result =excelImportService.readOneRow(sheetIndex,curRow, rowlist);
                
                if (result != null && !SUCCESS.equals(result)) {
                    // 失败统计数加1
                    rowsFailureNum++;
                    // 失败数据列表
                    dataListFail.add(new ArrayList<String>(rowlist));
                    // 失败数据对应的失败原因列表
                    detailListFail.add(result);
                    /**
                     * 同时将该失败信息数据保存到对象List<T>集合中
                     */
                    excelImportService.transferDataListFail2ObjectTList(objectTListFail,rowlist);
                } else {
                    rowsSuccessNum++;
                }
            }

        }

    }
    
    /**
     * 可以手动设置当前工具类对应的导出服务类，好像泛型限定了，这样set，使用时可能有问题！！！
     */
    public ExcelImportService<T> getExcelImportService() {
        return excelImportService;
    }

    public void setExcelImportService(ExcelImportService<T> excelImportService) {
        this.excelImportService = excelImportService;
    }

    public int getRowsTotalNum() {
        return rowsTotalNum;
    }

    public void setRowsTotalNum(int rowsTotalNum) {
        this.rowsTotalNum = rowsTotalNum;
    }

    public int getRowsSuccessNum() {
        return rowsSuccessNum;
    }

    public void setRowsSuccessNum(int rowsSuccessNum) {
        this.rowsSuccessNum = rowsSuccessNum;
    }

    public int getRowsFailureNum() {
        return rowsFailureNum;
    }

    public void setRowsFailureNum(int rowsFailureNum) {
        this.rowsFailureNum = rowsFailureNum;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public List<List<String>> getDataListFail() {
        return dataListFail;
    }

    public void setDataListFail(List<List<String>> dataListFail) {
        this.dataListFail = dataListFail;
    }

    public List<String> getDetailListFail() {
        return detailListFail;
    }

    public void setDetailListFail(List<String> detailListFail) {
        this.detailListFail = detailListFail;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public List<String> getFieldCodeList() {
        return fieldCodeList;
    }

    public void setFieldCodeList(List<String> fieldCodeList) {
        this.fieldCodeList = fieldCodeList;
    }

    public List<T> getObjectTListFail() {
        return objectTListFail;
    }

    public void setObjectTListFail(List<T> objectTListFail) {
        this.objectTListFail = objectTListFail;
    }

    
}
