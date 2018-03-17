package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠劵项
 * @author yy
 *
 */
@ApiModel(value="优惠劵项",description="优惠劵项")
public class CouponTerm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 优惠劵项id
	 */
	@ApiModelProperty(value="优惠劵项id",example="优惠劵项id")
	private Integer couponTermId;
	
	/**
	 * 名称
	 */
	@ApiModelProperty(value="名称",example="名称")
	private String name;
	/**
	 * 图片
	 */
	@ApiModelProperty(value="图片",example="图片")
	private String imgAddress;
	/**
	 * 折扣
	 */
	@ApiModelProperty(value="折扣",example="折扣")
	private Double discount;
	/**
	 * 内容
	 */
	@ApiModelProperty(value="内容",example="内容")
	private String content;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	/**
	 * 商品类型id,外键
	 */
	@ApiModelProperty(value="商品类型id,外键",example="商品类型id,外键")
	private Integer merCateId;
	public Integer getCouponTermId() {
		return couponTermId;
	}
	public void setCouponTermId(Integer couponTermId) {
		this.couponTermId = couponTermId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getMerCateId() {
		return merCateId;
	}
	public void setMerCateId(Integer merCateId) {
		this.merCateId = merCateId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
