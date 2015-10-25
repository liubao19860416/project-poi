package com.saick.base.service.api;

import java.util.List;
import java.util.Map;

/**
 * dwr的服务接口
 * 
 * @author Thinkpad
 * 
 */
public interface DwrService {
    // 测试方法
    public String testdwr() throws Exception;

    // 获取近几年记录
    public List<Map<String, String>> businessyear() throws Exception;
}
