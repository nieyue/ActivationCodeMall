package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠劵
 * @author yy
 *
 */
@ApiModel(value="优惠劵",description="优惠劵")
public class Coupon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 优惠劵id
	 */
	@ApiModelProperty(value="优惠劵id",example="优惠劵id")
	private Integer couponId;
	/**
	 * 优惠劵码
	 */
	@ApiModelProperty(value="优惠劵码",example="优惠劵码")
	private String code;
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
	 * 开始时间
	 */
	@ApiModelProperty(value="开始时间",example="开始时间")
	private Date startDate;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value="结束时间",example="结束时间")
	private Date endDate;
	/**
	 * 状态，默认1可用，2已用，3已失效
	 */
	@ApiModelProperty(value="状态，默认1可用，2已用，3已失效",example="状态，默认1可用，2已用，3已失效")
	private Integer status;
	/**
	 * 商品类型id,此商品类型才能使用
	 */
	@ApiModelProperty(value="商品类型id,此商品类型才能使用",example="商品类型id,此商品类型才能使用")
	private Integer merCateId;
	/**
	 * 优惠劵人ID，此id账户才能使用
	 */
	@ApiModelProperty(value="优惠劵人ID，此id账户才能使用",example="优惠劵人ID，此id账户才能使用")
	private Integer accountId;
	/**
	 * 优惠劵项ID
	 */
	@ApiModelProperty(value="优惠劵项ID",example="优惠劵项ID")
	private Integer couponTermId;
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getMerCateId() {
		return merCateId;
	}
	public void setMerCateId(Integer merCateId) {
		this.merCateId = merCateId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getCouponTermId() {
		return couponTermId;
	}
	public void setCouponTermId(Integer couponTermId) {
		this.couponTermId = couponTermId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
