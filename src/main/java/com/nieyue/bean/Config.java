package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 配置
 * @author yy
 *
 */
@ApiModel(value="配置",description="配置")
public class Config implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 配置id
	 */
	@ApiModelProperty(value="配置id",example="配置id")
	private Integer configId;
	/**
	 * 客服电话
	 */
	@ApiModelProperty(value="客服电话",example="客服电话")
	private String customerServicePhone;
	/**
	 * 最大订单商品数量
	 */
	@ApiModelProperty(value="最大订单商品数量",example="最大订单商品数量")
	private Integer orderMerMaxNumber;
	/**
	 * 商户每盈利一元钱获得积分
	 */
	@ApiModelProperty(value="商户每盈利一元钱获得积分",example="商户每盈利一元钱获得积分")
	private Double sellerIntegralPer;
	/**
	 * 用户每消费一元钱获得积分
	 */
	@ApiModelProperty(value="用户每消费一元钱获得积分",example="用户每消费一元钱获得积分")
	private Double userIntegralPer;
	/**
	 * 商户诚信升级金额
	 */
	@ApiModelProperty(value="商户诚信升级金额",example="商户诚信升级金额")
	private Double sellerSincerityUpgradeMoney;
	/**
	 * 冻结天数
	 */
	@ApiModelProperty(value="冻结天数",example="冻结天数")
	private Integer freezeDayNumber;
	/**
	 * 平台分成比例，单位%
	 */
	@ApiModelProperty(value="平台分成比例，单位%",example="平台分成比例，单位%")
	private Double platformProportion;
	/**
	 * 推广分成比例，单位%
	 */
	@ApiModelProperty(value="推广分成比例，单位%",example="推广分成比例，单位%")
	private Double spreadProportion;
	/**
	 * 提现最低额度
	 */
	@ApiModelProperty(value="提现最低额度",example="提现最低额度")
	private Double minWithdrawals;
	/**
	 * 提现手续费比例，单位%
	 */
	@ApiModelProperty(value="提现手续费比例，单位%",example="提现手续费比例，单位%")
	private Double withdrawalsProportion;
	/**
	 * 无提现手续费最低额度
	 */
	@ApiModelProperty(value="无提现手续费最低额度",example="无提现手续费最低额度")
	private Double withdrawalsMinBrokerage;
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
	public Integer getConfigId() {
		return configId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	public String getCustomerServicePhone() {
		return customerServicePhone;
	}
	public void setCustomerServicePhone(String customerServicePhone) {
		this.customerServicePhone = customerServicePhone;
	}
	public Integer getOrderMerMaxNumber() {
		return orderMerMaxNumber;
	}
	public void setOrderMerMaxNumber(Integer orderMerMaxNumber) {
		this.orderMerMaxNumber = orderMerMaxNumber;
	}
	public Double getSellerIntegralPer() {
		return sellerIntegralPer;
	}
	public void setSellerIntegralPer(Double sellerIntegralPer) {
		this.sellerIntegralPer = sellerIntegralPer;
	}
	public Double getUserIntegralPer() {
		return userIntegralPer;
	}
	public void setUserIntegralPer(Double userIntegralPer) {
		this.userIntegralPer = userIntegralPer;
	}
	public Double getSellerSincerityUpgradeMoney() {
		return sellerSincerityUpgradeMoney;
	}
	public void setSellerSincerityUpgradeMoney(Double sellerSincerityUpgradeMoney) {
		this.sellerSincerityUpgradeMoney = sellerSincerityUpgradeMoney;
	}
	public Integer getFreezeDayNumber() {
		return freezeDayNumber;
	}
	public void setFreezeDayNumber(Integer freezeDayNumber) {
		this.freezeDayNumber = freezeDayNumber;
	}
	public Double getPlatformProportion() {
		return platformProportion;
	}
	public void setPlatformProportion(Double platformProportion) {
		this.platformProportion = platformProportion;
	}
	public Double getSpreadProportion() {
		return spreadProportion;
	}
	public void setSpreadProportion(Double spreadProportion) {
		this.spreadProportion = spreadProportion;
	}
	public Double getMinWithdrawals() {
		return minWithdrawals;
	}
	public void setMinWithdrawals(Double minWithdrawals) {
		this.minWithdrawals = minWithdrawals;
	}
	public Double getWithdrawalsProportion() {
		return withdrawalsProportion;
	}
	public void setWithdrawalsProportion(Double withdrawalsProportion) {
		this.withdrawalsProportion = withdrawalsProportion;
	}
	public Double getWithdrawalsMinBrokerage() {
		return withdrawalsMinBrokerage;
	}
	public void setWithdrawalsMinBrokerage(Double withdrawalsMinBrokerage) {
		this.withdrawalsMinBrokerage = withdrawalsMinBrokerage;
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

}
