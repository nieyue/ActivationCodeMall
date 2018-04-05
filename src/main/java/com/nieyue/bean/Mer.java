package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品
 * @author yy
 *
 */
@ApiModel(value="商品",description="商品")
public class Mer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品id
	 */
	@ApiModelProperty(value="商品id",example="商品id")
	private Integer merId;
	/**
	 * 范围，1官网自营，2商家自营
	 */
	@ApiModelProperty(value="范围，1官网自营，2商家自营",example="范围，1官网自营，2商家自营")
	private Integer region;
	/**
	 * 平台分成比例，单位%
	 */
	@ApiModelProperty(value="平台分成比例，单位%",example="平台分成比例，单位%")
	private Double platformProportion;
	/**
	 * 类型，1普通商品，2降价商品，3预购商品
	 */
	@ApiModelProperty(value="类型，1普通商品，2降价商品，3预购商品",example="类型，1普通商品，2降价商品，3预购商品")
	private Integer type;
	/**
	 * 最迟发货时间，预购商品可选
	 */
	@ApiModelProperty(value="最迟发货时间，预购商品可选",example="最迟发货时间，预购商品可选")
	private Date deliverEndDate;
	/**
	 * 商品名
	 */
	@ApiModelProperty(value="商品名",example="商品名")
	private String name;
	/**
	 * 简介
	 */
	@ApiModelProperty(value="简介",example="简介")
	private String summary;
	/**
	 * 封面
	 */
	@ApiModelProperty(value="封面",example="封面")
	private String imgAddress;
	/**
	 * 平台
	 */
	@ApiModelProperty(value="平台",example="平台")
	private String platform;
	/**
	 * 推荐，默认0不推，1封推，2推荐
	 */
	@ApiModelProperty(value="推荐，默认0不推，1封推，2推荐",example="推荐，默认0不推，1封推，2推荐")
	private Integer recommend;
	/**
	 * 原始单价
	 */
	@ApiModelProperty(value="原始单价",example="原始单价")
	private Double oldUnitPrice;
	/**
	 * 单价
	 */
	@ApiModelProperty(value="单价",example="单价")
	private Double unitPrice;
	/**
	 * 折扣
	 */
	@ApiModelProperty(value="折扣",example="折扣")
	private Double discount;
	/**
	 * 销量
	 */
	@ApiModelProperty(value="销量",example="销量")
	private Integer saleNumber;
	/**
	 * 库存数
	 */
	@ApiModelProperty(value="库存数",example="库存数")
	private Integer stockNumber;
	/**
	 * 商品评分
	 */
	@ApiModelProperty(value="商品评分",example="商品评分")
	private Double merScore;
	/**
	 * 商品评论数
	 */
	@ApiModelProperty(value="商品评论数",example="商品评论数")
	private Integer merCommentNumber;
	/**
	 * 商品详情
	 */
	@ApiModelProperty(value="商品详情",example="商品详情")
	private String details;
	/**
	 * 配置要求
	 */
	@ApiModelProperty(value="配置要求",example="配置要求")
	private String configuration;
	/**
	 * 安装激活
	 */
	@ApiModelProperty(value="安装激活",example="安装激活")
	private String installActivation;
	/**
	 * 状态0下架,默认1上架
	 */
	@ApiModelProperty(value="状态0下架,默认1上架",example="状态0下架,默认1上架")
	private Integer status;
	/**
	 * 商品类型id,外键
	 */
	@ApiModelProperty(value="商品类型id,外键",example="商品类型id,外键")
	private Integer merCateId;
	/**
	 * 商户账户id,外键
	 */
	@ApiModelProperty(value="商户账户id,外键",example="商户账户id,外键")
	private Integer sellerAccountId;
	/**
	 * 商品创建时间
	 */
	@ApiModelProperty(value="商品更新时间",example="商品更新时间")
	private Date createDate;
	/**
	 * 商品更新时间
	 */
	@ApiModelProperty(value="商品更新时间",example="商品更新时间")
	private Date updateDate;
	/**
	 * 商品图片
	 */
	@ApiModelProperty(value="商品图片",example="商品图片")
	private List<MerImg> merImgList;
	
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public Integer getRegion() {
		return region;
	}
	public void setRegion(Integer region) {
		this.region = region;
	}
	public Double getPlatformProportion() {
		return platformProportion;
	}
	public void setPlatformProportion(Double platformProportion) {
		this.platformProportion = platformProportion;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getDeliverEndDate() {
		return deliverEndDate;
	}
	public void setDeliverEndDate(Date deliverEndDate) {
		this.deliverEndDate = deliverEndDate;
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
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Double getOldUnitPrice() {
		return oldUnitPrice;
	}
	public void setOldUnitPrice(Double oldUnitPrice) {
		this.oldUnitPrice = oldUnitPrice;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(Integer saleNumber) {
		this.saleNumber = saleNumber;
	}
	public Integer getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}
	public Double getMerScore() {
		return merScore;
	}
	public void setMerScore(Double merScore) {
		this.merScore = merScore;
	}
	public Integer getMerCommentNumber() {
		return merCommentNumber;
	}
	public void setMerCommentNumber(Integer merCommentNumber) {
		this.merCommentNumber = merCommentNumber;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	public String getInstallActivation() {
		return installActivation;
	}
	public void setInstallActivation(String installActivation) {
		this.installActivation = installActivation;
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
	public Integer getSellerAccountId() {
		return sellerAccountId;
	}
	public void setSellerAccountId(Integer sellerAccountId) {
		this.sellerAccountId = sellerAccountId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<MerImg> getMerImgList() {
		return merImgList;
	}
	public void setMerImgList(List<MerImg> merImgList) {
		this.merImgList = merImgList;
	}

	
}
