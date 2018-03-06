package com.nieyue.exception;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
/**
 * 自定义全局异常解析
 * @author yy
 *
 */
public class MySimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex
			) {
		//return super.getModelAndView("redirect:"+viewName, ex, request);
            //return new ModelAndView("redirect:"+viewName);  
            return new ModelAndView("forward:"+viewName);  
	}

}
