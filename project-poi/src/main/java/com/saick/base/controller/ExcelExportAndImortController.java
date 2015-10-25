package com.saick.base.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.saick.base.dao.entiy.Coupon;
import com.saick.base.excel.ExcelHSSFReader;
import com.saick.base.page.PageQuery;
import com.saick.base.result.ResourcesUtil;
import com.saick.base.result.ResultInfo;
import com.saick.base.result.ResultInfoUtil;
import com.saick.base.service.api.ExcelExportService;
import com.saick.base.service.api.ExcelImportService;
import com.saick.base.utils.DateUtil;


/**
 * excel信息导出、导入Coupon信息表的控制层,也可以添加其他的类型bran的导入导出操作
 * @param <T>
 */
@Controller
//@RequestMapping("/excel")
public class ExcelExportAndImortController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    ExcelImportService<Coupon> importService;
    
    @Autowired
    ExcelExportService<Coupon> exportService;
    
	/**
	 * 信息导出页面跳转，返回ModelAndView格式url映射路径
	 */
	@RequestMapping("/export/couponView")
	public ModelAndView exportViewCoupon()throws Exception{
	    
	    logger.info("请求信息【/export/couponView】跳转到exportView页面成功。。。");
		return new ModelAndView("/excel/export/couponView");
	}
	
	/**
	 * 信息导入页面跳转，返回String格式url映射路径
	 */
	@RequestMapping("/import/couponView")
	public String importViewCoupon()throws Exception{

	    logger.info("请求信息【/import/couponView】跳转到importView页面成功。。。");
	    return "/excel/import/couponView";
	}
	
	/**
	 * 信息导出页面提交请求信息
	 */
    @RequestMapping("/export/couponSubmit")
	public @ResponseBody ResultInfo<?> exportSubmitCoupon(PageQuery<?> pageQuery)throws Exception{
        
        // 1.获取文件导出存放的临时文件路径，获取的应该是一个虚拟目录
        String exportFileTempPath = ResourcesUtil.getResourcesValue("exportFileTempPath");

        // 2.导出文件的名称及后缀，前缀等信息，这里使用的是一个新生成的文件名称；
        String filePrefix = "coupon";
        String exportFileName = filePrefix + "_" + DateUtil.getTimeString();
        
        //调用导出服务方法，执行导出操作
        String downLoadFilePath = exportService.exoprtExcel(exportFileTempPath, exportFileName);
        
	    logger.warn("请求信息【/export/submit/coupon】返回的下载url地址为："+downLoadFilePath);
	    
	    //给一个下载提示的url链接信息，提示用户下载对应的信息
	    return ResultInfoUtil.createSubmitResultInfo(ResultInfoUtil.createResultSuccess(318, new Object[]{downLoadFilePath}));
	}
	
	/**
	 * 信息导入页面提交-单个文件:importFile
	 */
	@SuppressWarnings("unused")
    @RequestMapping("/import/couponSubmit")
	public @ResponseBody ResultInfo<?> importSubmitCoupon(@RequestParam("importFile")MultipartFile importFile)throws Exception{
		//获取文件的原始名称
		String originalFileName = /*importFile.getName();*/importFile.getOriginalFilename();
		//获取文件导入存放的临时文件路径
		String importFileTempPath = ResourcesUtil.getResourcesValue("importFileTempPath");
		logger.warn("/import/submit/coupon获取文件导入存放的临时文件路径为："+importFileTempPath);
		
		//如果使用原始名称为了避免并发存在导入文件名称重复问题，需要新定义的文件名
		String newFileName = originalFileName.substring(originalFileName.lastIndexOf("."))+DateUtil.getTimeString();
		//添加文件名后缀
		newFileName += originalFileName.replaceAll(newFileName, "");
		
		//定义准备将写入磁盘文件的路径
		File file = new File(importFileTempPath+newFileName);
		//如果文件所在的路径不存在则创建
		if(!file.exists()){
			file.mkdirs();
		}
		/**
		 * 将内存中文件的数据写入磁盘，这里相当于是创建一个临时文件
		 */
		importFile.transferTo(file);
		
		//获取临时文件的绝对路径
		String absoluteFilePath = file.getAbsolutePath();
		
        //使用工具类解析导入的文件
		ExcelHSSFReader<Coupon> xls2csv=null;
		try {
		    //使用工具类来读取excel文件
			xls2csv = new ExcelHSSFReader<Coupon>(absoluteFilePath,0,importService);
			//执行读取操作
			xls2csv.process();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//读取操作结果相关信息
		long importSuccessNum =xls2csv.getRowsSuccessNum();
		long importFailNum =xls2csv.getRowsFailureNum();
		long importTotalNum =xls2csv.getRowsTotalNum();
		/**
		 * 方法1：将失败信息存储到数据库表中让用户查询失败信息,存在数据存储问题(不容易唯一标识提示)
		 * 方法2：将失败信息导出成excel让用户下载查看，用户根据失败信息查找失败记录困难
		 * 方法3：将原始导入的失败记录及失败信息一并导出给用户，这时用户就可以直接在导出的失败记录中修改后将该文件重新导入,此方案最优
		 */
		//导入失败提示信息(失败原因)
		List<String> detailListFail = xls2csv.getDetailListFail();
		
		//导入失败原始记录:格式为：List<List<String>>
		List<List<String>> dataListFail = xls2csv.getDataListFail();
		//导入失败原始记录:格式为：List<Coupon>
		List<Coupon> objectTListFail = xls2csv.getObjectTListFail();
		//导入失败原始记录字段信息,
		List<String> fieldCodeList = xls2csv.getFieldCodeList();
		//导入记录的title,OK
		List<String> titleList = xls2csv.getTitleList();
		
		//根据上边三个方法，将失败原因及记录导出成excel供用户下载。
		 String downLoadFilePath = null;
		if(!detailListFail.isEmpty()){
		    // 1.获取文件导出存放的临时文件路径，获取的应该是一个虚拟目录
	        String exportFileTempPath = ResourcesUtil.getResourcesValue("exportFileTempPath");

	        // 2.导出文件的名称及后缀，前缀等信息，这里使用的是一个新生成的文件名称；
	        String filePrefix = "coupon";
	        String exportFileName = filePrefix + "_" + DateUtil.getTimeString();
		    
		    //将失败信息列表展示给用户查看
	        downLoadFilePath =  exportService.exoprtExcel(titleList,fieldCodeList,objectTListFail,exportFileTempPath, exportFileName);
		
	        if(downLoadFilePath!=null){
	            logger.warn("/import/submit/coupon导入失败信息条数为"+importFailNum+"，集合为："+objectTListFail);
	            return ResultInfoUtil.createSubmitResultInfo(ResultInfoUtil.createResultSuccess(315, new Object[]{importTotalNum,importSuccessNum,importFailNum,downLoadFilePath}));
	        }
		}
		
		logger.warn("/import/submit/coupon导入信息成功条数为："+importSuccessNum);
		return ResultInfoUtil.createSubmitResultInfo(ResultInfoUtil.createResultSuccess(314, new Object[]{importTotalNum,importSuccessNum,importFailNum}));
	}	
	
	/**
	 * 信息导入页面提交-导入多文件测试方法
	 */
	@SuppressWarnings("unused")
    @RequestMapping("/import/mutiFileSubmit/coupon")
	public @ResponseBody ResultInfo<?> importMutiFileSubmitCoupon(@RequestParam("mutiImportFile")MultipartFile[] mutiImportFile) throws Exception{
		
	    //使用工具类解析导入的文件
	    ExcelHSSFReader<Coupon> xls2csv=null;
        
		for(int i=0;i<mutiImportFile.length;i++){
		  //获取文件的原始名称
	        String originalFileName = mutiImportFile[i].getOriginalFilename();
	        //获取文件导入存放的临时文件路径
	        String importFileTempPath = ResourcesUtil.getResourcesValue("importFileTempPath");
	        logger.warn("/import/mutiFileSubmit/coupon获取文件导入存放的临时文件路径为："+importFileTempPath);
			
			//如果使用原始名称为了避免并发存在导入文件名称重复问题，需要新定义的文件名
			String newFileName = originalFileName.substring(originalFileName.lastIndexOf("."))+DateUtil.getTimeString();
			//添加文件名后缀
	        newFileName += originalFileName.replaceAll(newFileName, "");
			
	        //定义准备将写入磁盘文件的路径
	        File file = new File(importFileTempPath+newFileName);
	        //如果文件所在的路径不存在则创建
	        if(!file.exists()){
	            file.mkdirs();
	        }
	        /**
	         * 将内存中文件的数据写入磁盘，这里相当于是创建一个临时文件
	         */
			mutiImportFile[i].transferTo(file);
			
			//获取临时文件的绝对路径
			String absoluteFilePath = file.getAbsolutePath();
			
	        try {
	            //使用工具类来读取excel文件
	            xls2csv = new ExcelHSSFReader<Coupon>(absoluteFilePath,0,importService);
	            //执行读取操作
	            xls2csv.process();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		//读取操作结果相关信息
		long importSuccessNum =xls2csv.getRowsSuccessNum();
		long importFailNum =xls2csv.getRowsFailureNum();
		long importTotalNum =xls2csv.getRowsTotalNum();
		//导入失败提示信息(失败原因)
        List<String> detailListFail = xls2csv.getDetailListFail();
        //导入失败原始记录
        List<List<String>> dataListFail = xls2csv.getDataListFail();
        //导入记录的title
        List<String> titleList = xls2csv.getTitleList();
		
		logger.warn("/import/mutiFileSubmit/coupon导入失败信息条数为"+importFailNum+"，集合为："+dataListFail);
		
		return ResultInfoUtil.createSubmitResultInfo(ResultInfoUtil.createResultSuccess(314,new Object[]{importTotalNum,importSuccessNum,importFailNum} ));
	}
	
	
	
}
