package com.saick.base.service.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 导出excel接口（基础类）
 * <T>代表需要导出的实体bean类型
 */
public interface ExcelExportService<T> {
   
    public abstract String exoprtExcel(String exportFileTempPath,String exportFileName)  throws Exception ;
  
    public abstract String exoprtExcel(HttpServletResponse response, HttpServletRequest request, String exportFileTempPath,String exportFileName)  throws Exception ;

    public abstract String exoprtExcel(List<String> titleList,List<String> fieldCodeList,List<T> dataListFail, String exportFileTempPath,String exportFileName) throws Exception;
    
}
