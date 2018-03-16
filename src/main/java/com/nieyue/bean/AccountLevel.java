package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 等级
 * @author yy
 *
 */
@ApiModel(value="等级",description="等级")
public class AccountLevel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 等级id
	 */
	@ApiModelProperty(value="等级id",example="等级id")
	private Integer accountLevelId;
	
	/**
	 * 等级名
	 */
	@ApiModelProperty(value="等级名",example="等级名")
	private String name;
	/**
	 * 等级,默认是0，数字越大，等级越高
	 */
	@ApiModelProperty(value="等级,默认是0，数字越大，等级越高",example="等级,默认是0，数字越大，等级越高")
	private Integer level;
	/**
	 * 等级图片
	 */
	@ApiModelProperty(value="等级图片",example="等级图片")
	private String imgAddress;
	/**
	 * 商户升级积分
	 */
	@ApiModelProperty(value="商户升级积分",example="商户升级积分")
	private Double sellerUpgradeIntegral;
	/**
	 * 用户升级积分
	 */
	@ApiModelProperty(value="用户升级积分",example="用户升级积分")
	private Double userUpgradeIntegral;
	/**
	 * 备注（权益）
	 */
	@ApiModelProperty(value="备注（权益）",example="备注（权益）")
	private String mark;
	/**
	 * 等级更新时间
	 */
	@ApiModelProperty(value="等级更新时间",example="等级更新时间")
	private Date updateDate;
	public Integer getAccountLevelId() {
		return accountLevelId;
	}
	public void setAccountLevelId(Integer accountLevelId) {
		this.accountLevelId = accountLevelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public Double getSellerUpgradeIntegral() {
		return sellerUpgradeIntegral;
	}
	public void setSellerUpgradeIntegral(Double sellerUpgradeIntegral) {
		this.sellerUpgradeIntegral = sellerUpgradeIntegral;
	}
	public Double getUserUpgradeIntegral() {
		return userUpgradeIntegral;
	}
	public void setUserUpgradeIntegral(Double userUpgradeIntegral) {
		this.userUpgradeIntegral = userUpgradeIntegral;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
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
