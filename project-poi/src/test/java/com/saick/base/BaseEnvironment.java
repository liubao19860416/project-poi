package com.saick.base;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基本环境加载配置类
 * 
 */
public final class BaseEnvironment {

    private static final Logger logger = LoggerFactory.getLogger(BaseEnvironment.class);

    public static final String SYS_ENV; // 基本环境常量（被外部访问，所以为public）
    public static final String LOG_ENV; // 基本log日志文件环境（被外部访问，所以为public）
    private static final String DEFAULT_ENV = "DEV"; 
    private static final String PROJECT_ENV = "PROJECT_ENV"; 

    static {
        //LOG_ENV=SYS_ENV=DEFAULT_ENV;
        //LOG_ENV=SYS_ENV =Pphconfig.getContextProperty("system.conf");
        LOG_ENV=SYS_ENV = retrieveEnvValue(PROJECT_ENV, DEFAULT_ENV);
        logger.warn("SYS_ENV={},LOG_ENV={}",SYS_ENV,LOG_ENV);
        // reset the log context according to the BaseEnvironment.LOG_ENV
        MyLoggerFactory.resetLogContext();
    }

  //通用的读取配置信息的方法
    private static String retrieveEnvValue(String name, String defaultValue) {
        String env = null;
        // 1st: check in jvm app arguments
        env = System.getProperty(name);
        // 2nd: check in environment variables
        if (StringUtils.isBlank(env)) {
            env = System.getenv().get(name);
        }
        
        // 3rd: check in the according file
        if (StringUtils.isBlank(env)) {
            InputStream ii = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
            if (ii != null) {
                InputStreamReader r = new InputStreamReader(ii);
                // limited to 100 chars is enough
                char[] cbuf = new char[100]; 
                try {
                    int n = r.read(cbuf);
                    if (n > 0) {
                        env = new String(cbuf, 0, n).trim();
                    }
                } catch (Exception ex) {
                    logger.error("BaseEnvironment.retrieveEnvValue  method exception。。。", ex);
                } finally {
                    try {
                        if(ii!=null){
                            ii.close();
                        }
                    } catch (Exception ex) {
                        logger.error("释放资源异常！",ex);
                    }finally{
                        ii=null;
                    }
                }
            }
        }

        // 对应的默认值
        if (StringUtils.isBlank(env)) {
            env = defaultValue;
            logger.warn("加载的是对应的【"+name+"】配置信息的默认值:" + env);
        }
        return env;
    }
    
    private BaseEnvironment() {}
    
}
