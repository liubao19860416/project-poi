package com.saick.base.excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meidusa.fastjson.JSON;
import com.saick.base.dao.entiy.UserCoupon;
import com.saick.base.dao.entiy.UserCouponDOP;
import com.saick.base.dao.entiy.VehicleSuit;
/**
 * 无需显示判断版本信息，直接读取excel进行解析
 */
public class Excel2007And2003Reader {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public static void main(String[] args) {
//        test01();
        //test02();
//        test03();
//        test04();
        test05();
    }
    
    /**
     * 导出文件方法012-大众保养套餐信息导出
     */
    private static void test02() {
        Excel2007And2003Reader ReadExcel2 = new Excel2007And2003Reader();
        try {
            // ReadExcel2.readExcel("D:\\temp\\订单信息列表.xlsx");
            List<List<String>> readExcel = ReadExcel2
                    .readExcel("D:\\temp\\上海大众途观系列 1.8TSI／2.0TSI 车型保养.xlsx");
//                    .readExcel("D:\\temp\\订单信息列表.xls");
            
            //构造json格式数据
            List<VehicleSuit> couponList=new ArrayList<VehicleSuit>();
            
            //删除标题行
            readExcel.remove(0);
            readExcel.remove(0);
            
            int i=0;
            for (List<String> list : readExcel) {
                ++i;
                if(i>=41){
                    break;
                }
                System.out.println(i);
                VehicleSuit vehicleSuit=new VehicleSuit();
                float parseFloat = Float.parseFloat(list.get(0));
                vehicleSuit.setSortIndex((int)parseFloat+"");
                vehicleSuit.setType(list.get(1));
                vehicleSuit.setName(list.get(2));
                String fitToSuit=transToFitToSuit2(list.get(3),list.get(4),
                        list.get(5),list.get(6),list.get(7));
                vehicleSuit.setFitToSuit(fitToSuit);
                couponList.add(vehicleSuit);
            }
            
            //TODO 特殊项目补充
            
            System.out.println("===========================");
            System.out.println(JSON.toJSONString(couponList.size()));
            System.out.println(JSON.toJSONString(couponList));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 转换适用的保养类型
     * @param string5 
     * @param string4 
     * @param string3 
     * @param string2 
     */
    @SuppressWarnings("unused")
    @Deprecated
    private static String transToFitToSuit(String str1, String str2, String str3, String str4, String str5) {
        String fitToSuit="";
        if(StringUtils.isNotBlank(str1)&&"√".equals(str1)){
            fitToSuit+=",a";
        }
        if(StringUtils.isNotBlank(str2)&&"√".equals(str2)){
            fitToSuit+=",b";
        }
        if(StringUtils.isNotBlank(str3)&&"√".equals(str3)){
            fitToSuit+=",c";
        }
        if(StringUtils.isNotBlank(str4)&&"√".equals(str4)){
            fitToSuit+=",d";
        }
        if(StringUtils.isNotBlank(str5)&&"√".equals(str5)){
            fitToSuit+=",e";
        }
        
        fitToSuit+=",1,2,";
        
        return fitToSuit;
    }
    
    private static String transToFitToSuit2(String str1, String str2, String str3, String str4, String str5) {
        String fitToSuit="";
        if(StringUtils.isNotBlank(str1)){
            fitToSuit+=",a";
        }
        if(StringUtils.isNotBlank(str2)){
            fitToSuit+=",b";
        }
        if(StringUtils.isNotBlank(str3)){
            fitToSuit+=",c";
        }
        if(StringUtils.isNotBlank(str4)){
            fitToSuit+=",d";
        }
        if(StringUtils.isNotBlank(str5)){
            fitToSuit+=",e";
        }
        
        fitToSuit+=",1,2,";
        
        return fitToSuit;
    }
    
    /**
     * 更新保养券的使用时间sql
     */
    @SuppressWarnings("unused")
    private static void test05() {
        Excel2007And2003Reader ReadExcel2 = new Excel2007And2003Reader();
        try {
            List<List<String>> readExcel = ReadExcel2
                    .readExcel("D:\\temp\\更新用户保养券的使用时间-20150618.xls");
            //构造json格式数据
            List<Map<String,String>> couponList=new ArrayList<Map<String,String>>();
            //删除标题行 5623条
            readExcel.remove(0);
            int i=0;
            for (List<String> list : readExcel) {
                System.out.println(i++);
                Map<String,String> map=new HashMap<String,String>();
                map.put("verifyCode", list.get(0));
                map.put("useDatetime", list.get(1));
                couponList.add(map);
            }
            
            Assert.assertEquals(5758, couponList.size());
            
            System.out.println("==========="+couponList.size()+"================");
            System.out.println(JSON.toJSONString(couponList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出文件方法-保养券使用时间更新json格式数据
     */
    @SuppressWarnings("unused")
    private static void test04() {
        Excel2007And2003Reader ReadExcel2 = new Excel2007And2003Reader();
        try {
            List<List<String>> readExcel = ReadExcel2
                    .readExcel("D:\\temp\\已使用-保养券列表.xlsx");
            //构造json格式数据
            List<Map<String,String>> couponList=new ArrayList<Map<String,String>>();
            //删除标题行 5623条
            readExcel.remove(0);
            for (List<String> list : readExcel) {
                Map<String,String> map=new HashMap<String,String>();
                map.put("verifyCode", list.get(0));
                map.put("useDatetime", list.get(1));
                couponList.add(map);
            }
            
            Assert.assertEquals(5623, couponList.size());
            
            System.out.println("==========="+couponList.size()+"================");
            System.out.println(JSON.toJSONString(couponList));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 导出文件方法01-保养券信息导出
     */
    @SuppressWarnings("unused")
    private static void test01() {
        Excel2007And2003Reader ReadExcel2 = new Excel2007And2003Reader();
        try {
            // ReadExcel2.readExcel("D:\\temp\\订单信息列表.xlsx");
            List<List<String>> readExcel = ReadExcel2
                    .readExcel("D:\\temp\\客户调研保养券第二批.xlsx");
//                    .readExcel("D:\\temp\\temp.xlsx");
//                    .readExcel("D:\\temp\\订单信息列表.xls");
            for (List<String> list : readExcel) {
                //System.out.println(Arrays.asList(list));
                /*for (String string : list) {
                    System.out.println("=====>>>" + string);
                }*/
            }
            
            //构造json格式数据
            List<UserCoupon> couponList=new ArrayList<UserCoupon>();
            
            //删除标题行
            readExcel.remove(0);
            
            for (List<String> list : readExcel) {
                UserCoupon userCoupon=new UserCoupon();
                userCoupon.setUserCode(list.get(0));
                userCoupon.setUserMobilePhone(list.get(1));
                userCoupon.setVerifyCode(list.get(4));
                userCoupon.setUserVehiclePlateNumber(list.get(2));
                couponList.add(userCoupon);
            }
            
            System.out.println("===========================");
            System.out.println(JSON.toJSONString(couponList));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    /**
     * 导出文件方法03-保养券-DOP信息导出供给update语句使用
     */
    private static void test03() {
        Excel2007And2003Reader ReadExcel2 = new Excel2007And2003Reader();
        try {
            List<List<String>> readExcel = ReadExcel2
                    .readExcel("D:\\temp\\temp.xlsx");
//                    .readExcel("D:\\temp\\订单信息列表.xls");
            
            //构造json格式数据
            List<UserCouponDOP> couponList=new ArrayList<UserCouponDOP>();
            
            //删除标题行
            readExcel.remove(0);
            
            for (List<String> list : readExcel) {
                UserCouponDOP userCoupon=new UserCouponDOP();
                userCoupon.setCustomer_id(list.get(0));
                userCoupon.setCoupon_id(list.get(1));
                userCoupon.setUser_name(list.get(2));
                userCoupon.setStore_name(list.get(3));
                userCoupon.setMobile(list.get(4));
                userCoupon.setVerify_code(list.get(5));
                userCoupon.setPlate_number(list.get(6));
                couponList.add(userCoupon);
            }
            
            System.out.println("===========================");
            System.out.println(JSON.toJSONString(couponList));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }




    /**
     * 无需显示判断版本信息，直接读取excel进行解析,List<Map<String, Object>>
     */
    public List<List<String>> readExcel(String excelPath)  throws Exception {
        
        //List<Map<String, Object>> resultListMap=new LinkedList<Map<String, Object>>();
        List<List<String>> resultList=new LinkedList<List<String>>();
        
        //创建Workbook对象
        Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(excelPath)));
        //创建Sheet对象，第on个获取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //获取第一行开始行号
        int startRowNum = sheet.getFirstRowNum();
        //获取最后一行行号
        int endRowNum = sheet.getLastRowNum();
        for (int rowNum = startRowNum; rowNum <= endRowNum; rowNum++) {
            
            //封装查询结果
            //Map<String, Object> map=new HashMap<String, Object>();
            List<String> list=new ArrayList<String>();
            
            Row row = sheet.getRow(rowNum);
            if (row == null){
                continue;
            }
            //获取第一列开始列号
            int startCellNum = row.getFirstCellNum();
            //获取最后一列列号 TODO,当最后一个单元格为空的时候，就会出问题！！！脚标超界
            int endCellNum = row.getLastCellNum();
//            int endCellNum = 8;
            for (int cellNum = startCellNum; cellNum < endCellNum; cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell == null){
                    //TODO 进行处理，以方便空格的处理
                    list.add(" ");
                    continue;
                }
                int type = cell.getCellType();
                switch (type) {
                case Cell.CELL_TYPE_NUMERIC:
                    // 数值、日期类型，默认数值类型为double类型
                    double d = cell.getNumericCellValue();
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 日期类型
                        // Date date = cell.getDateCellValue();
                        Date date = HSSFDateUtil.getJavaDate(d);
                        //输出读取到的数值
                        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                        //map.put("日期", format);
                        list.add(format);
                        System.out.print(format);
                    } else {// 数值类型
                        System.out.print( d );
                        list.add(d+"");
                    }
                    break;
                case Cell.CELL_TYPE_BLANK:
                    //TODO 进行处理，以方便空格的处理
                    // 空白单元格
                    System.out.print(" null ");
                    //list.add(" null ");
                    list.add(" ");
                    break;
                case Cell.CELL_TYPE_STRING:
                    // 字符类型
                    System.out.print(cell.getStringCellValue());
                    list.add(cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    // 布尔类型
                    System.out.println(cell.getBooleanCellValue());
                    list.add(cell.getBooleanCellValue()+"");
                    break;
                case HSSFCell.CELL_TYPE_ERROR: 
                    // 故障， 非法字符;
                    System.err.println("非法字符");
                    list.add("非法字符");
                    break;
                default:
                    // 未知类型
                    System.err.println("error");
                    list.add("error");
                    break;
                }
            }
            
            System.out.println();
            resultList.add(list);
        }
        return resultList;
    }

}
