package com.nieyue.comments;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

  
/** 
 * @Title 扩展BeanUtils.copyProperties支持data类型 
 * @Description 
 * @date 2017-1-5 
 */  
public class MyDateConverter implements Converter { 
	private String dateformat;
	public MyDateConverter(String dateformat){
		this.dateformat=dateformat;
	};
	public Object convert(Class arg0, Object arg1) {
		 if(arg0== null || arg0.equals("")){
	            return null;
	        }   
		 System.out.println(arg0);
		 System.out.println(arg1);
		 if (arg1 instanceof Date) {
	       try {
	           SimpleDateFormat df = new SimpleDateFormat(dateformat);
	           return df.format(arg1);
	       } catch (Exception e) {
	               return null;
	           }
		 }
		return arg1;
	}

	public String getDateformat() {
		return dateformat;
	}

	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}
}  
