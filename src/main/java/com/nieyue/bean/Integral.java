package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 积分
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="积分",description="积分")
public class Integral implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 积分id
	 */
	@ApiModelProperty(value="积分id",example="积分id")
	private Integer integralId;
	/**
	 * 名称
	 */
	@ApiModelProperty(value="名称",example="名称")
	private String name;
	/**
	 * 等级
	 */
	@ApiModelProperty(value="等级",example="等级")
	private Integer level;
	/**
	 * 剩余积分
	 */
	@ApiModelProperty(value="剩余积分",example="剩余积分")
	private Double integral;
	/**
	 * 升级积分
	 */
	@ApiModelProperty(value="升级积分",example="升级积分")
	private Double upgradeIntegral;
	/**
	 * 消费积分
	 */
	@ApiModelProperty(value="消费积分",example="消费积分")
	private Double consume;
	/**
	 * 赠送积分
	 */
	@ApiModelProperty(value="赠送积分",example="赠送积分")
	private Double baseProfit;
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
	 * 账户id外键
	 */
	@ApiModelProperty(value="账户id外键",example="账户id外键")
	private Integer accountId;
	public Integer getIntegralId() {
		return integralId;
	}
	public void setIntegralId(Integer integralId) {
		this.integralId = integralId;
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
	public Double getIntegral() {
		return integral;
	}
	public void setIntegral(Double integral) {
		this.integral = integral;
	}
	public Double getUpgradeIntegral() {
		return upgradeIntegral;
	}
	public void setUpgradeIntegral(Double upgradeIntegral) {
		this.upgradeIntegral = upgradeIntegral;
	}
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
	}
	public Double getBaseProfit() {
		return baseProfit;
	}
	public void setBaseProfit(Double baseProfit) {
		this.baseProfit = baseProfit;
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
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
