package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 收货信息
 * @author yy
 *
 */
@ApiModel(value="收货信息",description="收货信息")
public class ReceiptInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 收货信息id
	 */
	@ApiModelProperty(value="收货信息id",example="收货信息id")
	private Integer receiptInfoId;
	
	/**
	 * 收货信息姓名
	 */
	@ApiModelProperty(value="收货信息姓名",example="收货信息姓名")
	private String name;
	/**
	 * 收货地址手机号
	 */
	@ApiModelProperty(value="收货地址手机号",example="收货地址手机号")
	private String phone;
	/**
	 * 收货地址
	 */
	@ApiModelProperty(value="收货地址",example="收货地址")
	private String address;
	/**
	 * 默认为0不是，1是
	 */
	@ApiModelProperty(value="默认为0不是，1是",example="默认为0不是，1是")
	private Integer isDefault;
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
	 * 账户Id
	 */
	@ApiModelProperty(value="账户Id",example="账户Id")
	private Integer accountId;
	public Integer getReceiptInfoId() {
		return receiptInfoId;
	}
	public void setReceiptInfoId(Integer receiptInfoId) {
		this.receiptInfoId = receiptInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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
	public ReceiptInfo(Integer receiptInfoId, String name, String phone, String address, Integer isDefault,
			Date createDate, Date updateDate, Integer accountId) {
		super();
		this.receiptInfoId = receiptInfoId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.isDefault = isDefault;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.accountId = accountId;
	}
	public ReceiptInfo() {
		super();
	}

	
	
}
