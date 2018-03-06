package com.nieyue.util;


/**
 * 图片地址转换
 * @author yy
 *
 */
public class IMGIndivisibleUtil {
	/**
	 * 多地址合一
	 * @param imgs
	 * @return
	 */
	public static String arrayToString(String[] imgs){
		String img="";
	    for (int i = 0; i < imgs.length; i++) {
				img+=imgs[i]+"NY@";
			}
		return img;
	}
	/**
	 * 分割成地址数组
	 * @param imgs
	 * @return
	 */
	public static String[] stringToArray(String img){
		String[] imgs;
		imgs=img.split("NY@");
		return imgs;
	}
	/**
	 * 分割成一个地址
	 * @param imgs
	 * @return
	 */
	public static String arrayToOne(String[] imgs){
		String img="";
		for (int i = 0; i < imgs.length; i++) {
			img+=imgs[i];
		}
		return img;
	}
	
	public static void main(String[] args) {
		String [] ss={"sdfsdf","233423"};
		System.out.println(ss instanceof String[]);
		System.out.println(arrayToString(ss));
		System.out.println(stringToArray(arrayToString(ss))[0]);
		System.out.println(arrayToOne(ss));
	}
}
