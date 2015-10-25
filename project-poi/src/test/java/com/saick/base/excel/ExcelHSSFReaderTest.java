package com.saick.base.excel;

import java.util.List;

import org.junit.Test;

import com.meidusa.fastjson.JSON;
import com.saick.base.dao.entiy.Coupon;
import com.saick.base.service.api.ExcelImportService;
import com.saick.base.service.impl.BaseServiceTest;
import com.saick.base.service.impl.poi.CouponExcelImportServiceImpl;
/**
 * HxlsReader读取excel信息的测试方法
 * @author Liubao
 * @2014年12月8日
 *
 */
public class ExcelHSSFReaderTest extends BaseServiceTest{
    
    @Test
    public void test01() {
        ExcelHSSFReader<Coupon> xls2csv=null;
        ExcelImportService<Coupon> service = new CouponExcelImportServiceImpl();
        try {
            xls2csv = new ExcelHSSFReader<Coupon>("d:/temp/订单信息列表.xls", 0, service);
            xls2csv.process();
            long importSuccess = xls2csv.getRowsSuccessNum();
            long importFail = xls2csv.getRowsFailureNum();
            long importTotal = xls2csv.getRowsTotalNum();
            System.out.println(importTotal);
            System.out.println(importSuccess);
            System.out.println(importFail);
            List<Coupon> list = xls2csv.getObjectTListFail();
            
            System.out.println("结果:"+JSON.toJSONString(list));

            System.out.println("结束了。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
