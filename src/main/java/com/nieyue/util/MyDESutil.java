package com.nieyue.util;


import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * 
 *加密
 *解密
 * @author yy
 *
 */
public class MyDESutil {
	private static final String SHA_SLAT="sadfe4f#23%.;'+sdfssdf43543534";
	private static final String MD5_SLAT="sa2334f#23%.;'+sdfs233543dssdf4";
	/**
	 * 获取SHA
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getSHA(Object obj){
	 String sha1 = DigestUtils.sha1Hex(obj+SHA_SLAT);
		return sha1;
	}
	/**
	 * 获取MD5
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getMD5(Object obj){
		String md5 = DigestUtils.md5Hex(obj+MD5_SLAT);
		return md5;
	}
	/**
	 * 获取MD5 SESSIONID证书
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getMD5SESSIONID(Object obj,String sessionId){
		String md5 = DigestUtils.md5Hex(obj+MD5_SLAT+sessionId);
		return md5;
	}
	/**
	 * 获取MD5
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getMD5Timestamp(Object obj,long timestamp){
		String md5 = DigestUtils.md5Hex(obj+MD5_SLAT+timestamp);
		return md5;
	}
	public static void main(String[] args) throws Exception {
		String sha1 =getSHA("sdfadsf");
		System.out.println(sha1);
		
		String md5=getMD5("123456");
		System.out.println(md5);
		long newtime=new Date().getTime()/1000/120;
		System.out.println(newtime);
		System.out.println(DateUtil.dateFormatSimpleDate(new Date(newtime), "yyyy-MM-dd HH:mm:ss"));
		String md5timestamp=getMD5Timestamp("123456",newtime);
		System.out.println(md5timestamp);
		String mddd=getMD5("auth1000");
		String mddd2=getMD5(1000);
				System.out.println(mddd);
				System.out.println(mddd2);
		
		 
	}
}
