package com.nieyue.filter.params;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 参数转换
 * @author yy
 *
 */
public class MyNamingStrategy {
	public static final char UNDERLINE='_';  
	/**
	 * aaaBbb转aaa_bbb	
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param){  
	       if (param==null||"".equals(param.trim())){  
	           return "";  
	       }  
	       int len=param.length();  
	       StringBuilder sb=new StringBuilder(len);  
	       for (int i = 0; i < len; i++) {  
	           char c=param.charAt(i);  
	           if (Character.isUpperCase(c)){  
	               sb.append(UNDERLINE);  
	               sb.append(Character.toLowerCase(c));  
	           }else{  
	               sb.append(c);  
	           }  
	       }  
	       return sb.toString();  
	   }  
	
	/**
	 * aaa_bbb转aaaBbb	
	 * @param param
	 * @return
	 */
	   public static String underlineToCamel(String param){  
	       if (param==null||"".equals(param.trim())){  
	           return "";  
	       }  
	       int len=param.length();  
	       StringBuilder sb=new StringBuilder(len);  
	       for (int i = 0; i < len; i++) {  
	           char c=param.charAt(i);  
	           if (c==UNDERLINE){  
	              if (++i<len){  
	                  sb.append(Character.toUpperCase(param.charAt(i)));  
	              }  
	           }else{  
	               sb.append(c);  
	           }  
	       }  
	       return sb.toString();  
	   }  
	   public static String underlineToCamel2(String param){  
	       if (param==null||"".equals(param.trim())){  
	           return "";  
	       }  
	       StringBuilder sb=new StringBuilder(param);  
	       Matcher mc= Pattern.compile("_").matcher(param);  
	       int i=0;  
	       while (mc.find()){  
	           int position=mc.end()-(i++);  
	           //String.valueOf(Character.toUpperCase(sb.charAt(position)));  
	           sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());  
	       }  
	       return sb.toString();  
	   }  
	public static void main(String[] args) {
		String userName="userName";
		String[] pass_word={"pass_word","p_ds","sd_dd"};
		StringBuffer pw = new StringBuffer();
		String ctu = camelToUnderline(userName);
		for (int i = 0; i < pass_word.length; i++) {
			String utc = underlineToCamel(pass_word[i]);
			pw.append(utc);
		}
		//String utc2 = underlineToCamel2(pass_word);
		System.out.println(ctu);
		System.out.println(pw.toString());
		//System.out.println(utc2);
	}  
}
