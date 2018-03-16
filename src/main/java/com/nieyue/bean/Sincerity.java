package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 诚信
 * @author 聂跃
 */
@ApiModel(value="诚信",description="诚信")
public class Sincerity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 诚信id
	 */
	@ApiModelProperty(value="诚信id",example="诚信id")
	private Integer sincerityId;
	/**
	 * 诚信等级
	 */
	@ApiModelProperty(value="诚信等级",example="诚信等级")
	private Integer level;
	/**
	 * 已充值金额
	 */
	@ApiModelProperty(value="已充值金额",example="已充值金额")
	private Double money;
	/**
	 * 升级金额
	 */
	@ApiModelProperty(value="升级金额",example="升级金额")
	private Double upgradeMoney;
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
	public Integer getSincerityId() {
		return sincerityId;
	}
	public void setSincerityId(Integer sincerityId) {
		this.sincerityId = sincerityId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getUpgradeMoney() {
		return upgradeMoney;
	}
	public void setUpgradeMoney(Double upgradeMoney) {
		this.upgradeMoney = upgradeMoney;
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
