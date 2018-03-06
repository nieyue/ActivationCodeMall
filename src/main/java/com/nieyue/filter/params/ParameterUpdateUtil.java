package com.nieyue.filter.params;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 修改接收map
 * @author yy
 *
 */
public class ParameterUpdateUtil {
	/**
	 * map修改后再封装
	 * @param map
	 * @return
	 */
	public static Map<String,String[]> UpdateParameterNames(Map<String, String[]> map){
	     Map<String, String[]> newMap = new HashMap<String, String[]>();
	     for(Iterator iter = map.entrySet().iterator();iter.hasNext();){  
			Map.Entry element = (Map.Entry)iter.next();  
	    	 String strKey = (String) element.getKey();  
	    	 String[] value = (String[]) element.getValue();  
	    	 strKey=MyNamingStrategy.underlineToCamel(strKey);
	    	 
	    	 List<String> listvalue = new ArrayList<String>();  
	    	 for(int i=0;i <value.length;i++){  
	    	 listvalue.add(value[i]);
	    	 System.out.println(value[i]);
	    	 }  
	    	 String[] valueStr= listvalue.toArray(new String[listvalue.size()]);
	    	 newMap.put(strKey,valueStr);
	    	 }
		
		return newMap;
	}
	public static void main(String[] args) {
		String[] aarray=new String[]{"11","111"};
		String[] zwarray=new String[]{"中文","乱码"};
		Map<String, String[]> test=new HashMap<String,String[]>();
		test.put("aaa", aarray);
		test.put("zw", zwarray);
	/*	for(Iterator iter = test.entrySet().iterator();iter.hasNext();){  
			Map.Entry element = (Map.Entry)iter.next();  
	    	 String strKey = (String) element.getKey();  
	    	 String[] value = (String[]) element.getValue();  
	    	 strKey=MyNamingStrategy.underlineToCamel(strKey);
		System.out.println(strKey);
		for (int i = 0; i < value.length; i++) {
			System.out.println(value[i]);
			
		}
		}*/
		Map<String, String[]> upn = UpdateParameterNames(test);
		System.out.println(upn);
	}
}
