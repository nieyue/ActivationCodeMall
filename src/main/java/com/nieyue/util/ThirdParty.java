package com.nieyue.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 第三方应用的常量
 * @author yy
 *
 */
public class ThirdParty {
	
	/**
	 * 读取 properties
	 * @param key
	 * @return
	 */
	public static String GetValueByKey(String key){
         Properties prop = new Properties();  
 		String path = ThirdParty.class.getClassLoader().getResource("config/thirdParty.properties").getPath();  
 		//String path = "src/main/java/config/thirdParty.properties";  
         //String path = "c:/config/thirdParty.properties";
 		InputStream is;
 		try {
 			is = new FileInputStream(path);
 				prop.load(is);
 				is.close();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			return null;
 		}  
 		String value = (String) prop.get(key);
 		return value;
	}
	/**
	 * 写入properties
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	public static String SetValueByKey(String key,String value) throws IOException{
		Properties prop = new Properties();  
		String path = ThirdParty.class.getClassLoader().getResource("config/thirdParty.properties").getPath();  
		//String path2 = "src/main/java/config/thirdParty.properties"; 
		// String path2 = "src/main/resources/config/thirdParty.properties";
		//String path = "c:/config/thirdParty.properties";
		InputStream is=new FileInputStream(path) ;
		OutputStream fos;
		String  s="";
		try {
			 prop.load(is);
			 is.close();
	         // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
	         // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			 s = (String) prop.setProperty(key, value);
	         fos = new FileOutputStream(path); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}  
          //String s = (String) prop.setProperty(key, value);
          //String  s=(String) prop.put(key, value);
          prop.store(fos, "Update '" + key + "' value");
		 fos.close();
		return s;
	}
	public static void main(String[] args) {
		try {
			ThirdParty.SetValueByKey("phone", "1aaa");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ThirdParty.GetValueByKey("phone"));
		//System.out.println(ThirdParty.GetValueByKey("WEIXIN_YAYAO_API"));
		//System.out.println(SUCCESS);
		//System.out.println(new ThirdParty().getLocation());
	}
	/**
	 * test appid
	 */
	public static String WEIXIN_TEST_APPID="WEIXIN_TEST_APPID";
	/**
	 *test SECRET 
	 */
	public static String WEIXIN_TEST_SECRET="WEIXIN_TEST_SECRET";
	/**
	 * YaYao appid
	 */
	public static String WEIXIN_YAYAO_APPID="WEIXIN_YAYAO_APPID";
	/**
	 *YaYao SECRET 
	 */
	public static String WEIXIN_YAYAO_SECRET="WEIXIN_YAYAO_SECRET";
	/**
	 *YaYao MCH_ID 
	 */
	public static String WEIXIN_YAYAO_MCH_ID="WEIXIN_YAYAO_MCH_ID";
	/**
	 *YaYao api 
	 */
	public static String WEIXIN_YAYAO_API="WEIXIN_YAYAO_API";
	/**
	 * 放肆约appid
	 */
	public static String WEIXIN_FANGSIYUE_APPID="WEIXIN_FANGSIYUE_APPID";
	/**
	 *放肆约SECRET 
	 */
	public static String WEIXIN_FANGSIYUE_SECRET="WEIXIN_FANGSIYUE_SECRET";
	/**
	 *放肆约  MCH_ID 
	 */
	public static String WEIXIN_FANGSIYUE_MCH_ID="WEIXIN_FANGSIYUE_MCH_ID";
	/**
	 *放肆约api 
	 */
	public static String WEIXIN_FANGSIYUE_API="WEIXIN_FANGSIYUE_API";
	/**
	 *阿里大鱼APPKEY
	 */
	public static String ALIBABA_SMS_APPKEY="ALIBABA_SMS_APPKEY";
	/**
	 *阿里大鱼APPSECRET
	 */
	public static String ALIBABA_SMS_APPSECRET="ALIBABA_SMS_APPSECRET";
	/**
	 *阿里大鱼 模板id
	 */
	public static String ALIBABA_SMS_TEMPLATE_CODE_ID="ALIBABA_SMS_TEMPLATE_CODE_ID";
	/**
	 *阿里大鱼 签名 
	 */
	public static String ALIBABA_SMS_SIGN_NAME="ALIBABA_SMS_SIGN_NAME";
	/**
	 *阿里百川APPKEY
	 */
	public static String ALIBABA_IM_APPKEY="ALIBABA_IM_APPKEY";
	/**
	 *阿里百川APPSECRET
	 */
	public static String ALIBABA_IM_APPSECRET="ALIBABA_IM_APPSECRET";
	
}
