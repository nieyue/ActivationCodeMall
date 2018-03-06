package com.nieyue.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 * 配置类
 * @author yy
 *
 */
public class Configure {
	
	/**
	 * 读取 properties
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	public static String GetValueByKey(String key){
		Properties prop = new Properties();  
		String path = Configure.class.getClassLoader().getResource("config/configure.properties").getPath();  
		InputStream is;
		try {
			is = new FileInputStream(path);
				prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}  
		String value = (String) prop.get(key);
		return value;
	}
	/**
	 * 获取OriginArray
	 * @param args
	 */
	public static String[] getOriginArray(){
		String[] ol = Configure.GetValueByKey("Origin").split(",");
		return ol;
	}
	/**
	 * 获取oldOrigin 
	 * @param args
	 */
	public static String getOldOrigin(){
		String e = Configure.GetValueByKey("env");
		String oldO=Configure.GetValueByKey("Origin").split(",")[0];//默认本地
		if(e.equals("prod")){
			oldO = Configure.GetValueByKey("Origin").split(",")[1];
		}
		return oldO;
	}
	public static void main(String[] args) {
		System.out.println(Configure.GetValueByKey("Origin"));
		//System.out.println(SUCCESS);
	}
	/**
	 * Origin 控制来源
	 */
	public static String Origin="Origin";
	/**
	 * env环境
	 */
	public static String env="env";
	
}
