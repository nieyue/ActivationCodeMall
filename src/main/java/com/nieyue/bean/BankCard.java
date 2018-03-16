package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 银行卡
 * @author yy
 *
 */
@ApiModel(value="银行卡",description="银行卡")
public class BankCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 银行卡id
	 */
	@ApiModelProperty(value="银行卡id",example="银行卡id")
	private Integer bankCardId;
	
	/**
	 * 姓名
	 */
	@ApiModelProperty(value="姓名",example="姓名")
	private String realname;
	/**
	 * 身份证
	 */
	@ApiModelProperty(value="身份证",example="身份证")
	private String identity;
	/**
	 * 银行名
	 */
	@ApiModelProperty(value="银行名",example="银行名")
	private String bankName;
	/**
	 * 银行卡卡号
	 */
	@ApiModelProperty(value="银行卡卡号",example="银行卡卡号")
	private String number;
	/**
	 * 预留手机号
	 */
	@ApiModelProperty(value="预留手机号",example="预留手机号")
	private String phone;
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
	 * 账户id
	 */
	@ApiModelProperty(value="账户id",example="账户id")
	private Integer accountId;
	public Integer getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(Integer bankCardId) {
		this.bankCardId = bankCardId;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
