package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品类型
 * @author yy
 *
 */
@ApiModel(value="商品类型",description="商品类型")
public class MerCate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品类型id
	 */
	@ApiModelProperty(value="商品类型id",example="商品类型id")
	private Integer merCateId;
	
	/**
	 * 商品类型名
	 */
	@ApiModelProperty(value="简介",example="商品类型名")
	private String name;
	/**
	 * 简介
	 */
	@ApiModelProperty(value="简介",example="简介")
	private String summary;
	/**
	 * 商品类型更新时间
	 */
	@ApiModelProperty(value="商品类型更新时间",example="商品类型更新时间")
	private Date updateDate;
	public Integer getMerCateId() {
		return merCateId;
	}
	public void setMerCateId(Integer merCateId) {
		this.merCateId = merCateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
