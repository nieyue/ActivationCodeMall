package com.nieyue.comments;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
/**
 * 拷贝Bean工具，Date自动转String
 * @author Administrator
 *
 */
public class MyBeanUtils extends BeanUtils {
	
	public static void copyProperties(Object dest, Object orig,String dateformat) {
	       try {
	    	   ConvertUtils.register(new MyDateConverter(dateformat), java.util.Date.class);
		       ConvertUtils.register( new MyDateConverter(dateformat), String.class);
	           BeanUtils.copyProperties(dest, orig);
	       } catch (Exception ex) {
	           ex.printStackTrace();
	       } 
	   }
}
