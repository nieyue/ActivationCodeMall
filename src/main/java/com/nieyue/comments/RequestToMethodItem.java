package com.nieyue.comments;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 请求接口信息类
 * @author 聂跃
 * @date 2017年7月20日
 */
public class RequestToMethodItem implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String controllerName;//控制器名字
    public String methodName;//方法名
    public List<RequestMethod> requestType;//请求类型
    public String requestUrl;//请求URL
    public String methodParamTypes;//请求参数
 
    
    public RequestToMethodItem(String controllerName, String methodName, List<RequestMethod> requestType, String requestUrl,
			String methodParamTypes) {
		super();
		this.controllerName = controllerName;
		this.methodName = methodName;
		this.requestType = requestType;
		this.requestUrl = requestUrl;
		this.methodParamTypes = methodParamTypes;
	}

	public RequestToMethodItem() {
		super();
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<RequestMethod> getRequestType() {
		return requestType;
	}

	public void setRequestType(List<RequestMethod> requestType) {
		this.requestType = requestType;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getMethodParamTypes() {
		return methodParamTypes;
	}

	public void setMethodParamTypes(String methodParamTypes) {
		this.methodParamTypes = methodParamTypes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
