package com.saick.base.excel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saick.base.dao.entiy.Coupon;
import com.saick.base.dao.entiy.CouponExample;
import com.saick.base.dao.entiy.CouponExample.Criteria;
import com.saick.base.dao.mapper.CouponMapper;
import com.saick.base.result.ResourcesUtil;
import com.saick.base.service.impl.BaseServiceTest;
import com.saick.base.utils.DateUtil;
/**
 * ExcelExportSXXSSF导出信息测试类
 * @author Liubao
 * @2014年12月9日
 *
 */
public class ExcelExportSXXSSFTest extends BaseServiceTest{
    
    @Autowired
    CouponMapper couponMapper;

    @Test
    public void test01() throws Exception {
        // 1.调用service查询对象PageQuery，查询出出需要导出的数据列表
        CouponExample example=new CouponExample();
        Criteria criteria = example.createCriteria();
        criteria.andActivedIsNotNull();
        List<Coupon> exportDataList = couponMapper.selectByExample(example);

        // 2.获取文件导出存放的临时文件路径，获取的应该是一个虚拟目录
        String exportFileTempPath = ResourcesUtil.getResourcesValue("exportFileTempPath");

        // 3.导出文件的名称及后缀，前缀等信息，这里使用的是一个新生成的文件名称；
        String filePrefix = "coupon";
        String exportFileName = filePrefix + "_" + DateUtil.getTimeString();

        // 4.设置导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
        List<String> fieldCodeList = new ArrayList<String>();
        fieldCodeList.add("id");// 通用名
        fieldCodeList.add("code");// 药品流水号
        fieldCodeList.add("name");// 剂型
        fieldCodeList.add("fitToMinKm");// 剂型

        // 5.设置导出数据的title
        // 注意：fieldCodeList和titleList个数必须相同且属性和顺序一一对应，这样导出的数据和内容才一一对应
        List<String> titleList = new ArrayList<String>();
        titleList.add("券号");
        titleList.add("券code");
        titleList.add("券名称");

        // -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
        int flushRows = 100;
        
        // 开始导出，执行一些workbook及sheet等对象的初始创建
        ExcelExportSXXSSF<Coupon> excelExportSXXSSF = new ExcelExportSXXSSF<Coupon>(exportFileTempPath,exportFileName, 
                flushRows,titleList, fieldCodeList);

        // 导出表头信息
        excelExportSXXSSF.writeTitleList();
        
        // 执行导出表格数据体信息
        excelExportSXXSSF.writeDatasByObject(exportDataList);
        
        // 输出文件，返回下载文件的http地址d:/temp/upload/coupon_20141209182327516.xlsx
        String downWebPath = excelExportSXXSSF.exportFile();

        System.out.println(downWebPath);
    }
}
