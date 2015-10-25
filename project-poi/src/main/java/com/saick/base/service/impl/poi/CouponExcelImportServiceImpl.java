package com.saick.base.service.impl.poi;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saick.base.dao.entiy.Coupon;
import com.saick.base.dao.mapper.CouponMapper;
import com.saick.base.excel.ExcelHSSFReader;
import com.saick.base.service.api.ExcelImportService;

/**
 * 具体的一个Coupon对应的导出excel信息实现类
 * @author Liubao
 * @2014年12月8日
 *
 */
@Service
public class CouponExcelImportServiceImpl implements ExcelImportService<Coupon>{
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //记录当前需要导出导入的该类的字段信息
    private List<String> fieldCodeList=new ArrayList<String>();
    
    static{
        //fieldCodeList.add("");
        //fieldCodeList.add("");
        //fieldCodeList.add("");
        //fieldCodeList.add("");
        //fieldCodeList.add("");
        //fieldCodeList.add("");
        //fieldCodeList.add("");
    }
    
    @Autowired
    CouponMapper couponMapper;
    
    /**
     * 接口返回的结果是导入数据的结果，有成功，有失败 
     * 一行行的进行数据操作和记录
     * 必须实现的导出方法
     */
    @Override
    public String readOneRow(int sheetIndex, int curRow, List<String> rowlist) {
        //根据导入的excel解析这一行的数据
        String mc = rowlist.get(0);//通用名
        String jx =rowlist.get(1);//剂型
        String gg = rowlist.get(2);//规格
        String zhxs  = rowlist.get(3);//转换系数
        String zbjg= rowlist.get(4);//中标价格
        String scqymc = rowlist.get(5);//生产企业名称
        String spmc = rowlist.get(6);//商品名称
        String jyzt = rowlist.get(7);//交易状态(1：正常，2：暂停)
        
        //校验交易状态
        /*if(!jyzt.equals("1")&&!jyzt.equals("2") ){
            return "交易状态输入数据不对，请参数导入说明";
        }*/
        
        //构造po
        //调用mapper插入数据库
        //插入之前校验唯一性
        //ypxxMapper.insert(ypxx);
        
        
        return "success";
    }
    
    /**
     * 获取失败信息，封装成对象List<Coupon>格式
     */
    @Override
    public String transferDataListFail2ObjectTList(List<Coupon> objectTListFail, List<String> rowlist) {
        //根据导入的excel解析这一行的数据
        String mc = rowlist.get(0);//通用名
        String jx =rowlist.get(1);//剂型
        String gg = rowlist.get(2);//规格
        String zhxs  = rowlist.get(3);//转换系数
        String zbjg= rowlist.get(4);//中标价格
        String scqymc = rowlist.get(5);//生产企业名称
        String spmc = rowlist.get(6);//商品名称
        String jyzt = rowlist.get(7);//交易状态(1：正常，2：暂停)
        
        Coupon coupon =new Coupon();
        //方式1：对应的set到coupon对象中
        
        
        
        //方式2：通过反射机制设置
        this.fieldCodeList=getFieldCodeList();
        for (String fieldCode : fieldCodeList) {
            
        }
        
        
        //插入之前校验唯一性,调用mapper插入数据库
        int result = couponMapper.insert(coupon);
        
        //返回结果
        if(result==1){
            return ExcelHSSFReader.SUCCESS;
        }else{
            return ExcelHSSFReader.FAILURE;
        }
    }

    /**
     * 这里的字段信息是和上面的对应的
     */
    @Override
    public List<String> getFieldCodeList() {
        if(fieldCodeList.isEmpty()){
            //fieldCodeList.add("");
            //fieldCodeList.add("");
            //fieldCodeList.add("");
            //fieldCodeList.add("");
            //fieldCodeList.add("");
            //fieldCodeList.add("");
            //fieldCodeList.add("");
        }
        return fieldCodeList;
    }

    public void setFieldCodeList(List<String> fieldCodeList) {
        this.fieldCodeList = fieldCodeList;
    }
    
}
