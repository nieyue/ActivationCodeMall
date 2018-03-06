package com.nieyue.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 状态码常量类
 * @author yy
 *
 */
public class Timer {
	
	/**
	 * 读取 properties
	 * @param key
	 * @return
	 */
	public static String GetValueByKey(String key){
		Properties prop = new Properties();  
 		String path = Configure.class.getClassLoader().getResource("config/timer.properties").getPath();  
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
	public static void main(String[] args) {
		System.out.println(Timer.GetValueByKey("SLEEP_TIME"));
		//System.out.println(SUCCESS);
	}
	/**
	 * 开始时间
	 */
	public static String START_TIME="START_TIME";
	/**
	 * 睡眠时间
	 */
	public static String SLEEP_TIME="SLEEP_TIME";
	
}
