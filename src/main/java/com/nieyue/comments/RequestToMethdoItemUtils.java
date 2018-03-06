package com.nieyue.comments;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * 获取请求列表
 * @author 聂跃
 * @date 2017年7月20日
 */
@Configuration
public class RequestToMethdoItemUtils {
	
		/**
		 * 获取请求信息列表
		 * 
		 */
	 public List<RequestToMethodItem>  getRequestToMethodItemList(HttpServletRequest request)
	    {
	        ServletContext servletContext = request.getSession().getServletContext();
	        if (servletContext == null)
	        {
	            return null;
	        }
	        WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

	        //请求url和处理方法的映射
	        List<RequestToMethodItem> requestToMethodItemList = new ArrayList<RequestToMethodItem>();
	        //获取所有的RequestMapping
	        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext, 
	HandlerMapping.class, true, false);

	        for (HandlerMapping handlerMapping : allRequestMappings.values())
	        {
	            //本项目只需要RequestMappingHandlerMapping中的URL映射
	            if (handlerMapping instanceof RequestMappingHandlerMapping)
	            {
	                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
	                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
	                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet())
	                {
	                    RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
	                    HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();

	                    RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
	                    Set<RequestMethod> requestTypeObject = methodCondition.getMethods();
	                    List<RequestMethod> requestType=new ArrayList<RequestMethod>(requestTypeObject);
	                    
	                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
	                    String requestUrl = (String) (patternsCondition.getPatterns().toArray())[0];

	                    String controllerName = mappingInfoValue.getBeanType().getSimpleName();
	                    String requestMethodName = mappingInfoValue.getMethod().getName();
	                  //  Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
	                    String methodParamTypes = getMethodParams(mappingInfoValue.getBeanType().getName().toString(),requestMethodName);
	                	//System.out.println(mappingInfoValue);
	                    RequestToMethodItem item = new RequestToMethodItem(controllerName, requestMethodName, requestType, requestUrl,methodParamTypes);

	                    requestToMethodItemList.add(item );
	                }
	                break;
	            }
	        }
	        return  requestToMethodItemList;
	    }
	 /**
	   * @param className 类名
	   * @param methodName 方法名
	   * @return 该方法的声明部分
	   * @author nieyue
	   * 创建时间：2017年3月8日 上午11:47:16
	   * 功能：返回一个方法的声明部分，包括参数类型和参数名
	   */
	  private String getMethodParams(String className,String methodName){
	    String result="";
	    try{
	      ClassPool pool=ClassPool.getDefault();
	       ClassClassPath classPath = new ClassClassPath(this.getClass());
	       pool.insertClassPath(classPath);
	      CtMethod cm =pool.getMethod(className, methodName); 
	     // 使用javaassist的反射方法获取方法的参数名 
	      MethodInfo methodInfo = cm.getMethodInfo(); 
	      CodeAttribute codeAttribute = methodInfo.getCodeAttribute(); 
	      LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag); 
	      result=cm.getName() + "(";
	      if (attr == null) { 
	        return result + ")";
	      } 
	      CtClass[] pTypes=cm.getParameterTypes();
	      String[] paramNames = new String[pTypes.length]; 
	      int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1; 
	      for (int i = 0; i < paramNames.length; i++) {
	    	  //System.err.println( attr.variableName(i + pos));
	        if(!pTypes[i].getSimpleName().startsWith("Http")){
	          result += pTypes[i].getSimpleName();
	          paramNames[i] = attr.variableName(i + pos); 
	          result += " " + paramNames[i]+","; 
	        }
	      }
	      if(result.endsWith(",")){
	        result=result.substring(0, result.length()-1);
	      }
	      result+=")";
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    return result;
	  }
}
