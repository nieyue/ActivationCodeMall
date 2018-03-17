package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品图片
 * @author yy
 *
 */
@ApiModel(value="商品图片",description="商品图片")
public class MerImg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品图片id
	 */
	@ApiModelProperty(value="商品图片id",example="商品图片id")
	private Integer merImgId;
	
	/**
	 * 图片地址
	 */
	@ApiModelProperty(value="图片地址",example="图片地址")
	private String imgAddress;
	/**
	 * 图片顺序
	 */
	@ApiModelProperty(value="图片顺序",example="图片顺序")
	private Integer number;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	/**
	 * 商品id外键
	 */
	@ApiModelProperty(value="商品id外键",example="商品id外键")
	private Integer merId;
	public Integer getMerImgId() {
		return merImgId;
	}
	public void setMerImgId(Integer merImgId) {
		this.merImgId = merImgId;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
