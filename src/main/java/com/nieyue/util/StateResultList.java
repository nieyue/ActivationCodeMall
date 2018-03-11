package com.nieyue.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回状态List Bean
 * @author yy
 *
 */
@ApiModel(value="返回状态Data",description="返回状态Data")
public class StateResultList<Data> {
	@ApiModelProperty(value="返回码",example="返回码")
	private Integer code;
	@ApiModelProperty(value="返回信息",example="返回信息")
	private String msg;
	@ApiModelProperty(value="返回数据")
	private Data data;
	public StateResultList() {
		super();
	}

	
	
	public StateResultList(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}



	public StateResultList(Integer code, String msg, Data data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
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



	public Data getData() {
		return data;
	}



	public void setData(Data data) {
		this.data = data;
	}

}
