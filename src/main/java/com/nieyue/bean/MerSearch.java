package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品搜索
 * @author yy
 *
 */
@ApiModel(value="商品搜索",description="商品搜索")
public class MerSearch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品搜索id
	 */
	@ApiModelProperty(value="商品搜索id",example="商品搜索id")
	private Integer merSearchId;
	
	/**
	 * 商品搜索名
	 */
	@ApiModelProperty(value="商品搜索名",example="商品搜索名")
	private String name;
	/**
	 * 次数
	 */
	@ApiModelProperty(value="次数",example="次数")
	private Integer number;
	/**
	 * 商品搜索更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	public Integer getMerSearchId() {
		return merSearchId;
	}
	public void setMerSearchId(Integer merSearchId) {
		this.merSearchId = merSearchId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
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
