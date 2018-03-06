package com.nieyue.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 聂跃
 * @date 2017年3月21日
 */
public class MyOriginUtil {
	/**
	 * 获取Origin配置
	 * @param request
	 */
	public static HttpServletResponse getOriginPugin(HttpServletRequest request,HttpServletResponse response){
		//实现跨域
		response.setHeader("Access-Control-Allow-Credentials", "true");
        //servletRequest.getHeader("Origin")
       String[] ol = Configure.getOriginArray();//动态设置
       response.setHeader("Access-Control-Allow-Origin",Configure.getOldOrigin());//设定默认的为生产环境的
       for (int i = 0; i < ol.length; i++) {
    	   if(request.getHeader("Origin")!=null&&request.getHeader("Origin").indexOf(ol[i])>-1 ){
    		   response.setHeader("Access-Control-Allow-Origin", ol[i]);
    	   }
       }
       response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
       response.setHeader("Access-Control-Max-Age", "3600");
       response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
       return response;
 }
}
