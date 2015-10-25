package com.saick.base.service.impl.poi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saick.base.dao.entiy.Coupon;
import com.saick.base.dao.entiy.CouponExample;
import com.saick.base.dao.entiy.CouponExample.Criteria;
import com.saick.base.dao.mapper.CouponMapper;
import com.saick.base.excel.ExcelExportSXXSSF;
import com.saick.base.service.api.ExcelExportService;

@Service
public class CouponExcelExportServiceImpl implements ExcelExportService<Coupon> {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final int DEFAULT_FLUSHROWS=100;
    
    //这两个信息一般不会变，也可以手动进行修改
    private static List<String> fieldCodeList = new ArrayList<String>();
    private static List<String> titleList = new ArrayList<String>();

    @Autowired
    CouponMapper couponMapper;
    
    /**
     * 当初次导入单信息失败后，会自动调用该方法，自动再执行一次导出操作，提示用户下载 导出失败的信息列表
     */
    @Override
    public String exoprtExcel(List<String> titleList,List<String> fieldCodeList,List<Coupon> exportDataList, 
            String exportFileTempPath,String exportFileName) throws Exception{
        
        //添加校验信息
       /* if(fieldCodeList==null||fieldCodeList.size()<=0){
            return null;
        }*/
        
        // -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
        int flushRows = DEFAULT_FLUSHROWS;
        CouponExcelExportServiceImpl.titleList=titleList;
        CouponExcelExportServiceImpl.fieldCodeList=fieldCodeList;
        
        // 开始导出，执行一些workbook及sheet等对象的初始创建
        ExcelExportSXXSSF<Coupon> excelExportSXXSSF = new ExcelExportSXXSSF<Coupon>(exportFileTempPath,exportFileName, 
                flushRows,titleList, fieldCodeList);

        // 导出表头信息
        excelExportSXXSSF.writeTitleList();
        
        //执行导出表体数据，传递的可以是对象
        excelExportSXXSSF.writeDatasByObject(exportDataList);
        
        //输出文件，返回下载文件的http地址
        String downLoadFilePath = excelExportSXXSSF.exportFile();
        
        return downLoadFilePath;
    }
    
    @Override
    public String exoprtExcel(String exportFileTempPath,String exportFileName) throws Exception {
        // 1.调用service查询对象PageQuery，查询出出需要导出的数据列表
        CouponExample example=new CouponExample();
        Criteria criteria = example.createCriteria();
        criteria.andActivedIsNotNull();
        List<Coupon> exportDataList = couponMapper.selectByExample(example);
        
        // 2.设置导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
        List<String> fieldCodeList = new ArrayList<String>();
        fieldCodeList.add("id");// 通用名
        fieldCodeList.add("code");// 药品流水号
        fieldCodeList.add("name");// 剂型
        fieldCodeList.add("fitToMinKm");// 剂型

        // 3.设置导出数据的title
        // 注意：fieldCodeList和titleList个数必须相同且属性和顺序一一对应，这样导出的数据和内容才一一对应
        List<String> titleList = new ArrayList<String>();
        titleList.add("券号");
        titleList.add("券code");
        titleList.add("券名称");
        titleList.add("最小公里数");

        // -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
        int flushRows = DEFAULT_FLUSHROWS;
        CouponExcelExportServiceImpl.titleList=titleList;
        CouponExcelExportServiceImpl.fieldCodeList=fieldCodeList;
        
        // 开始导出，执行一些workbook及sheet等对象的初始创建
        ExcelExportSXXSSF<Coupon> excelExportSXXSSF = new ExcelExportSXXSSF<Coupon>(exportFileTempPath,exportFileName, 
                flushRows,titleList, fieldCodeList);

        // 导出表头信息
        excelExportSXXSSF.writeTitleList();
        
        //执行导出表体数据，传递的可以是对象
        excelExportSXXSSF.writeDatasByObject(exportDataList);
        
        //输出文件，返回下载文件的http地址
        String downLoadFilePath = excelExportSXXSSF.exportFile();
        
        return downLoadFilePath;
    }

    @Override
    public String exoprtExcel(HttpServletResponse response,HttpServletRequest request,String filePpath, String fileName) throws Exception {
        
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * set和get方法
     */
    public static List<String> getFieldCodeList() {
        return fieldCodeList;
    }

    public static void setFieldCodeList(List<String> fieldCodeList) {
        CouponExcelExportServiceImpl.fieldCodeList = fieldCodeList;
    }

    public static List<String> getTitleList() {
        return titleList;
    }

    public static void setTitleList(List<String> titleList) {
        CouponExcelExportServiceImpl.titleList = titleList;
    }
    

}
