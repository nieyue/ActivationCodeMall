package com.nieyue.util;
/**
 * 正则验证
 * @author yy
 *
 */
public class MyValidator {
	  	/**
		* 正则表达式：验证用户名
	     */
	    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
	 
	    /**
	     * 正则表达式：验证密码
	     */
	    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
	 
	    /**
	     * 正则表达式：验证手机号
	     */
	    public static final String REGEX_PHONE = "^1[0-9]{10}$";
	 
	    /**
	     * 正则表达式：验证邮箱
	     */
	    public static final String REGEX_EMAIL = "^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+(\\.[a-zA-Z]{2,3})+$";
	 
	    /**
	     * 正则表达式：验证汉字
	     */
	    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
	 
	    /**
	     * 正则表达式：验证身份证
	     */
	    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
	 
	    /**
	     * 正则表达式：验证URL
	     */
	    public static final String REGEX_URL = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?";
	 
	    /**
	     * 正则表达式：验证IP地址
	     */
	    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
}
