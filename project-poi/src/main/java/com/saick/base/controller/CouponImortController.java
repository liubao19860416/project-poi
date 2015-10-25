package com.saick.base.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.meidusa.fastjson.JSON;
import com.saick.base.dao.entiy.Coupon;
import com.saick.base.excel.Excel2007And2003Reader;
import com.saick.base.excel.Excel2007XSSFReader;
import com.saick.base.result.ResultInfoUtil;
import com.saick.base.service.api.ExcelImportService;
import com.saick.base.utils.DateUtil;


/**
 * excel信息导出、导入Coupon信息表的控制层
 */
@Controller
@RequestMapping("/excel")
public class CouponImortController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    ExcelImportService<Coupon> importService;
    
    /**
     * 文件上传测试
     */
    @RequestMapping(value="/fileUpload",method={RequestMethod.POST})
    public @ResponseBody Object fileUpload(@RequestParam("fileToUpload") MultipartFile fileToUpload,
            HttpServletRequest request,HttpServletResponse response)throws Exception{
        
        //String tempPath = request.getSession().getServletContext().getRealPath("/template") + File.separator;
        String tempPath = request.getSession().getServletContext().getRealPath("/WEB-INF/temp")+ File.separator;
        //获取文件的原始名称
        String originalFileName = fileToUpload.getOriginalFilename();
        //如果使用原始名称为了避免并发存在导入文件名称重复问题，需要新定义的文件名
        String newFileName = DateUtil.getTimeString()+originalFileName.substring(originalFileName.lastIndexOf("."));
        newFileName=originalFileName.substring(0,originalFileName.lastIndexOf("."))+"_"+newFileName;
        //获取文件导入存放的临时文件路径
        String tempPathFileName=tempPath+File.separator+newFileName;
        System.out.println("存储全路径："+tempPathFileName);
        //定义准备将写入磁盘文件的路径
        File file = new File(tempPathFileName);
        //如果文件所在的路径不存在则创建
        if(!file.exists()){
            file.mkdirs();
        }
        fileToUpload.transferTo(file);
        
        
        String jsonString = JSON.toJSONString(file.getAbsolutePath());
        return jsonString;
    }

    @RequestMapping("/export/couponView")
	public ModelAndView exportViewCoupon()throws Exception{
	    logger.info("请求信息【/export/couponView】跳转到exportView页面成功。。。");
		return new ModelAndView("/excel/export/couponView");
	}
	
	@RequestMapping("/import/couponView")
	public String importViewCoupon()throws Exception{
	    logger.info("请求信息【/import/couponView】跳转到importView页面成功。。。");
	    return "/excel/import/couponView";
	}
	
	 /**
     * 测试显示导出文件路径下载弹窗
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/export/couponSubmit")
	public @ResponseBody Object exportSubmitCoupon(@RequestBody Map params,
	        HttpServletRequest request,HttpServletResponse response)throws Exception{
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        
        String tempPath = request.getSession().getServletContext().getRealPath("/template") + File.separator;
        //String tempPath = request.getSession().getServletContext().getRealPath("/WEB-INF/temp")+ File.separator;
        logger.warn("/import/submit/coupon获取文件导入存放的临时文件路径为："+tempPath);

        // 2.导出文件的名称及后缀，前缀等信息，这里使用的是一个新生成的文件名称；
        String suffix = "template.xls";
        //String downLoadFilePath = "file://"+tempPath+suffix;
        String downLoadFilePath =tempPath+suffix;
        String downLoadATag="<a href=\""+downLoadFilePath+"\" target=\"_blank\">点击下载文件</a>";
        
        //调用导出服务方法，执行导出操作
        //String downLoadFilePath = exportService.exoprtExcel(exportFileTempPath, exportFileName);
        
        //给一个下载提示的url链接信息，提示用户下载对应的信息
        String downLoadJsonString = JSON.toJSONString(downLoadFilePath);
        return downLoadJsonString;
	    //return ResultInfoUtil.createSubmitResultInfo(ResultInfoUtil.createResultSuccess(318, new Object[]{downLoadFilePath}));
    }
	
	/**
	 * 信息导入页面提交-单个文件:importFile
	 * 首先上传文件到服务器，然后再读取文件，进行解析；
	 */
    @RequestMapping(value="/import/couponSubmit",method={RequestMethod.POST})
	public @ResponseBody Object importSubmitCoupon(@RequestParam("importFile") MultipartFile importFile,
	        HttpServletRequest request,HttpServletResponse response)throws Exception{
        //设置编码
        //response.reset();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //response.setContentType("application/json;charset=UTF-8");
        
		String tempPath = request.getSession().getServletContext().getRealPath("/template") + File.separator;
        //String tempPath = request.getSession().getServletContext().getRealPath("/WEB-INF/temp");
		logger.warn("/import/submit/coupon获取文件导入存放的临时文件路径为："+tempPath);
        //获取文件的原始名称
        String originalFileName = importFile.getOriginalFilename();
        //如果使用原始名称为了避免并发存在导入文件名称重复问题，需要新定义的文件名
        String newFileName = DateUtil.getTimeString()+originalFileName.substring(originalFileName.lastIndexOf("."));
        newFileName=originalFileName.substring(0,originalFileName.lastIndexOf("."))+"_"+newFileName;
        //获取文件导入存放的临时文件路径
        String tempPathFileName=tempPath+File.separator+newFileName;
        System.out.println("存储全路径："+tempPathFileName);
		//定义准备将写入磁盘文件的路径
		File file = new File(tempPathFileName);
		//如果文件所在的路径不存在则创建
		if(!file.exists()){
			file.mkdirs();
		}
		/**
		 * 将内存中文件的数据写入磁盘，这里相当于是创建一个临时文件
		 */
		importFile.transferTo(file);
		
		int importSuccessNum=0;
		//方式1
		try {
		    Excel2007And2003Reader reader = new Excel2007And2003Reader();
		    List<List<String>> readExcel = reader.readExcel(tempPathFileName);
		    for (int i = 1; i < readExcel.size(); i++) {
		        System.out.println(readExcel.get(i));
		    }
		    importSuccessNum = readExcel.size();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		//方式2
		try {
		    Excel2007XSSFReader reader = new Excel2007XSSFReader(tempPathFileName);
		    reader.setSheet(0);
		    importSuccessNum= reader.getRowNum();
		    System.out.println(importSuccessNum);
		    System.out.println(reader.getColNum());
		    for (int i = 1; i < reader.getRowNum(); i++) {
		        List<String> rowValues = reader.getRowValues(i);
		        System.out.println("方式2==>>"+rowValues);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//方式3:不用，麻烦
		/*try {
		    ExcelHSSFReader<Coupon> xls2csv = new ExcelHSSFReader<Coupon>(tempPathFileName,0,importService);
		    xls2csv.process();
		    
		    //读取操作结果相关信息
	        long importSuccessNum =xls2csv.getRowsSuccessNum();
	        logger.warn("/import/submit/coupon导入信息成功条数为："+importSuccessNum);
	        
	        //导入记录的title,OK
	        List<String> fieldCodeList = xls2csv.getFieldCodeList();
	        System.out.println(fieldCodeList);
	        List<String> titleList = xls2csv.getTitleList();	
	        System.out.println(titleList);
		} catch (Exception e) {
		    e.printStackTrace();
		}*/
		
		return ResultInfoUtil.createSubmitResultInfo(ResultInfoUtil.createResultSuccess(314, new Object[]{importSuccessNum}));
		
    }	
	
}
