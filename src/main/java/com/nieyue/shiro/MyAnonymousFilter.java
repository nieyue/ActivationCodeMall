package com.nieyue.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.AnonymousFilter;

import com.nieyue.exception.CommonRollbackException;
/**
 * 自定义匿名访问
 * @author 聂跃
 * @date 2018年4月4日
 */
public class MyAnonymousFilter extends AnonymousFilter {
	@Override
	protected boolean onPreHandle(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		if(request.getRequestURI().equals("/")){
			//重定向, (请求转发不行)
			try {
				response.sendRedirect("/home/index.html");
			} catch (IOException e) {
				throw new CommonRollbackException("跳转错误");
			}
			return false;
		}
		return super.onPreHandle(request, response, mappedValue);
	}
}
