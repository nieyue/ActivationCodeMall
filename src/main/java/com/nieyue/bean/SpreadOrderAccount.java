package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 推广订单账户
 * @author yy
 *
 */
@ApiModel(value="推广订单账户",description="推广订单账户")
public class SpreadOrderAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 推广订单账户id
	 */
	@ApiModelProperty(value="推广订单账户id",example="推广订单账户id")
	private Integer spreadOrderAccountId;
	/**
	 *用户名
	 */
	@ApiModelProperty(value="用户名",example="用户名")
	private String name;
	/**
	 *用户邮箱
	 */
	@ApiModelProperty(value="用户邮箱",example="用户邮箱")
	private String email;
	/**
	 *交易笔数
	 */
	@ApiModelProperty(value="交易笔数",example="交易笔数")
	private Integer tradeNumber;
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
	public Integer getSpreadOrderAccountId() {
		return spreadOrderAccountId;
	}
	public void setSpreadOrderAccountId(Integer spreadOrderAccountId) {
		this.spreadOrderAccountId = spreadOrderAccountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getTradeNumber() {
		return tradeNumber;
	}
	public void setTradeNumber(Integer tradeNumber) {
		this.tradeNumber = tradeNumber;
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
