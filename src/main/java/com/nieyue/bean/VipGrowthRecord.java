package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * vip成长记录
 * @author yy
 *
 */
@ApiModel(value="vip成长记录",description="vip成长记录")
public class VipGrowthRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * vip成长记录id
	 */
	@ApiModelProperty(value="vip成长记录id",example="vip成长记录id")
	private Integer vipGrowthRecordId;
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
	 * 金额
	 */
	@ApiModelProperty(value="金额",example="金额")
	private Double price;
	/**
	 * vip创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 账户id外键
	 */
	@ApiModelProperty(value="账户id外键",example="账户id外键")
	private Integer accountId;
	public Integer getVipGrowthRecordId() {
		return vipGrowthRecordId;
	}
	public void setVipGrowthRecordId(Integer vipGrowthRecordId) {
		this.vipGrowthRecordId = vipGrowthRecordId;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
