package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 推广记录
 * @author yy
 *
 */
@ApiModel(value="推广记录",description="推广记录")
public class SpreadRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 推广记录id
	 */
	@ApiModelProperty(value="推广记录id",example="推广记录id")
	private Integer spreadRecordId;
	/**
	 *用户名
	 */
	@ApiModelProperty(value="用户名",example="用户名")
	private String name;
	/**
	 *交易金额
	 */
	@ApiModelProperty(value="交易金额",example="交易金额")
	private Double money;
	/**
	 *返利分成比例，单位%
	 */
	@ApiModelProperty(value="返利分成比例，单位%",example="返利分成比例，单位%")
	private Double spreadProportion;
	/**
	 *返利金额
	 */
	@ApiModelProperty(value="返利金额",example="返利金额")
	private Double spreadMoney;
	/**
	 *链接
	 */
	@ApiModelProperty(value="链接",example="链接")
	private String link;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 *推广账户id
	 */
	@ApiModelProperty(value="推广账户id",example="推广账户id")
	private Integer accountId;
	public Integer getSpreadRecordId() {
		return spreadRecordId;
	}
	public void setSpreadRecordId(Integer spreadRecordId) {
		this.spreadRecordId = spreadRecordId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getSpreadProportion() {
		return spreadProportion;
	}
	public void setSpreadProportion(Double spreadProportion) {
		this.spreadProportion = spreadProportion;
	}
	public Double getSpreadMoney() {
		return spreadMoney;
	}
	public void setSpreadMoney(Double spreadMoney) {
		this.spreadMoney = spreadMoney;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
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
