package com.nieyue.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回状态Bean
 * @author yy
 *
 */
@ApiModel(value="返回状态",description="返回状态")
public class StateResult {
	@ApiModelProperty(value="返回码",example="返回码")
	private Integer code;
	@ApiModelProperty(value="返回信息",example="返回信息")
	private String msg;
	public StateResult() {
		super();
	}

	public StateResult(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
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
}
