package com.nieyue.util;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回状态List Bean
 * @author yy
 *
 */
@ApiModel(value="返回状态List",description="返回状态List")
public class StateResultList {
	@ApiModelProperty(value="返回码",example="返回码")
	private Integer code;
	@ApiModelProperty(value="返回信息",example="返回信息")
	private String msg;
	@ApiModelProperty(value="返回列表",example="返回列表")
	private List<?> list;
	public StateResultList() {
		super();
	}

	
	
	public StateResultList(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}



	public StateResultList(Integer code, String msg, List<?> list) {
		super();
		this.code = code;
		this.msg = msg;
		this.list = list;
	}



	public Integer getCode() {
		return code;
	}



	public void setCode(Integer code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public List<?> getList() {
		return list;
	}



	public void setList(List<?> list) {
		this.list = list;
	}

}
