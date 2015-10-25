package com.saick.base.service.impl.dwr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saick.base.service.api.DwrService;


public class DwrServiceImpl implements DwrService {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/**
	 * 测试方法
	 * @return
	 * @throws Exception
	 */
	public String testdwr() throws Exception{
		return "helloworld";
	}
	public String testdwr2(String name) throws Exception{
		return "helloworld "+name;
	}
	
	/**
	 * 获取年度，获取近5年
	 */
	public List<Map<String, String>> businessyear() throws Exception{
		//当前年
	    Calendar calendar=Calendar.getInstance();
	    calendar.setTime(new Date());
	    int year = calendar.get(Calendar.YEAR);
		//定义了一个list对象，list里边是map
		List<Map<String, String>> result  = new ArrayList<Map<String,String>>();
		//将近5年放入list
		for(int i=year;i>=year-5;i--){
			 Map<String, String> index = new HashMap<String, String>();
			 index.put("info", i+"");
	      	 index.put("value", i+"");
	      	 result.add(index);
		}
		//将list返回
		return result;
	}
	
	

}
