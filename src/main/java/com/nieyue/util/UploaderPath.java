package com.nieyue.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 文件上传路径
 * @author yy
 *
 */
public class UploaderPath {
	
	/**
	 * 读取 properties
	 * @param key
	 * @return
	 */
	public static String GetValueByKey(String key){
		 Locale locale = Locale.getDefault();  
         ResourceBundle localResource = ResourceBundle.getBundle("config/uploaderPath", locale);
         try {
        	 String value = localResource.getString(key); 
        	 return value;
			
		} catch (Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
		System.out.println(UploaderPath.GetValueByKey("SUCCESS"));
		//System.out.println(SUCCESS);
	}
	/**
	 * 根目录
	 */
	public static String ROOTPATH="ROOTPATH";
	/**
	 * 文件
	 */
	public static String FILE="FILE";
	/**
	 * 图片
	 */
	public static String IMG="IMG";
	/**
	 * 音频
	 */
	public static String AUDIO="AUDIO";
	/**
	 * 视频
	 */
	public static String VIDEO="VIDEO";
	
}
