package com.nieyue.comments;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 *获取ObjectMapper
 * @author 聂跃
 *
 */
public class MyObjectMapper{
	/**
	 * 获取
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @param resource 数据源
	 * @param target 数据容器
	 * 
	 */
	public static  Object getObjectMapper(String resource,Object target) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper om = new ObjectMapper();
		om.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		Object result = om.readValue(resource, target.getClass());
		return result;
	} 
}
