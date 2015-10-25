package com.saick.base.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.saick.base.excel.ExcelExportSXXSSF2;
import com.saick.base.result.ResultInfoUtil;

/**
 *备份的导出和导入Controller
 *
 */
@Controller
@RequestMapping("/ypml")
public class YpxxAction {
	
	//导入药品信息信息
	@RequestMapping("/ypxximport.action")
	public ModelAndView ypxximport() throws Exception{
		
		return new ModelAndView("/ypml/ypxximport");
	}
	
	@RequestMapping("/ypxximportsubmit.action")
	public @ResponseBody Object ypxximportSubmit(@RequestParam MultipartFile ypxximportfile) throws Exception
	{
		System.out.println("开始");  
        String path = "";
        String fileName = ypxximportfile.getOriginalFilename();  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        /*HxlsRead hxlsRead=null;
        //保存  
        try {  
        	ypxximportfile.transferTo(targetFile);  
        	//hxlsRead = new HxlsRead(targetFile.getAbsolutePath(),0,ypxxImport);
        	//hxlsRead.process();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        //如果存在失败记录则导出供用户下载查看
        if(hxlsRead.getOptRows_failure()>0){
        	String export_path = "";
    		String export_webpath = "";
    		String filePrefix = "ypxx"; 
    		List<String> fieldName = hxlsRead.getRowtitle();
    		fieldName.add("errorinfo");
    		ExcelExportSXXSSF2 excelExportSXXSSF = ExcelExportSXXSSF2.start(export_path, export_webpath,filePrefix,fieldName ,null, 300);
    		List<List<String>> failrows = hxlsRead.getFailrows();
    		List<String> failmsgs = hxlsRead.getFailmsgs();
    		for(int k=0;k<failrows.size();k++){
    			List<String> failrow = failrows.get(k);
    			if(failrow != null){
    				String failmsg = failmsgs.get(k);
    				failrow.add(failmsg == null?"":failmsg);
    				excelExportSXXSSF.writeDatasByString(failrows.get(k));
    			}
    		}
    		String file_webpath = excelExportSXXSSF.exportFile();

    		return ResultInfoUtil.createSubmitResultInfo(ResultInfoUtil.createResultSuccess(315, new Object[]{hxlsRead.getOptRows_failure(),file_webpath}));
        }else{
        	return ResultInfoUtil.createSubmitResultInfo(ResultInfoUtil.createResultSuccess(314, new Object[]{hxlsRead.getOptRows_success(),hxlsRead.getOptRows_failure()}));
        }*/
        
     return null;
		
	}
	/**
	 * 导出数据
	 */
	@SuppressWarnings("rawtypes")
    @RequestMapping("/ypxxexport.action")
	public @ResponseBody Object ypxxExport(@RequestBody Map map) throws Exception{
	    //读取配置文件获取
		//String ypxxexportfield = "";
		//String[] fieldname = ypxxexportfield.split(",");
		//在这里写死也可以
		String[] fieldname = new String[] { 
				"bm#药品流水号",
				"pmmc#通用名",
				"pmjx#剂型",
				"pmgg#规格",
				"jyztmc#药品交易状态"
		};
		List<String> fieldNames = new ArrayList<String>();
		List<String> fieldCodes = new ArrayList<String>();
		for (int i = 0; i < fieldname.length; i++)
		{
			String fieldandname = fieldname[i];
			String[] namearray = fieldandname.split("#");
			fieldNames.add(namearray[1]);
			fieldCodes.add(namearray[0]);
		}
		String path = "";
		String webpath = "";
		String filePrefix = "yaopinxinxi";// 导出的文件名的前缀		
		ExcelExportSXXSSF2 excelExportSXXSSF = ExcelExportSXXSSF2.start(path, webpath,filePrefix, fieldNames,fieldCodes, 300);
		
		//从数据库查询药品信息
		List list = null;
		
		excelExportSXXSSF.writeDatasByObject(list);
		
		String file_webpath = excelExportSXXSSF.exportFile();
		
		int total = excelExportSXXSSF.getRownum();
		
		return ResultInfoUtil.createSubmitResultInfo(ResultInfoUtil.createResultSuccess(313, new Object[]{file_webpath}));
	}
	
	

}
