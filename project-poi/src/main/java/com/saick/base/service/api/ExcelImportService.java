package com.saick.base.service.api;

import java.util.List;

/**
 * 文件导入接口（基础类）
 * @author Liubao
 * @2014年12月8日
 *
 */
public interface ExcelImportService<T> {

    /**
     * 接口返回的结果是导入数据的结果，有成功，有失败 
     * 一行行的进行数据操作和记录
     * 必须实现的导出方法
     */
    public abstract String readOneRow(int sheetIndex, int curRow, List<String> rowlist);
    
    /**
     * 获取失败信息，封装成对象List<T>格式
     */
    public abstract String transferDataListFail2ObjectTList(List<T> objectTListFail, List<String> rowlist);
    
    /**
     * 获取对象List<T>中的T对象的需要导出/入的字段信息
     */
    public abstract List<String> getFieldCodeList();
}
