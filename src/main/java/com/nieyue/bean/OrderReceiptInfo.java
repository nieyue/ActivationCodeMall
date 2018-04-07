package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单收货信息
 * @author yy
 *
 */
@ApiModel(value="订单收货信息",description="订单收货信息")
public class OrderReceiptInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单收货信息id
	 */
	@ApiModelProperty(value="订单收货信息id",example="订单收货信息id")
	private Integer orderReceiptInfoId;
	
	/**
	 * 订单收货信息姓名
	 */
	@ApiModelProperty(value="订单收货信息姓名",example="订单收货信息姓名")
	private String name;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value="手机号",example="手机号")
	private String phone;
	/**
	 * 电话区号
	 */
	@ApiModelProperty(value="电话区号",example="电话区号")
	private String telephoneArea;
	/**
	 * 电话号
	 */
	@ApiModelProperty(value="电话号",example="电话号")
	private String telephone;
	/**
	 * 电话分机
	 */
	@ApiModelProperty(value="电话分机",example="电话分机")
	private String telephoneExtension;
	/**
	 * 邮政编码
	 */
	@ApiModelProperty(value="邮政编码",example="邮政编码")
	private String postcode;
	/**
	 * 国家
	 */
	@ApiModelProperty(value="国家",example="国家")
	private String country;
	/**
	 * 省
	 */
	@ApiModelProperty(value="省",example="省")
	private String province;
	/**
	 * 市
	 */
	@ApiModelProperty(value="市",example="市")
	private String city;
	/**
	 * 区
	 */
	@ApiModelProperty(value="区",example="区")
	private String area;
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
	/**
	 * 订单Id
	 */
	@ApiModelProperty(value="订单Id",example="订单Id")
	private Integer orderId;
	public Integer getOrderReceiptInfoId() {
		return orderReceiptInfoId;
	}
	public void setOrderReceiptInfoId(Integer orderReceiptInfoId) {
		this.orderReceiptInfoId = orderReceiptInfoId;
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
	public String getTelephoneArea() {
		return telephoneArea;
	}
	public void setTelephoneArea(String telephoneArea) {
		this.telephoneArea = telephoneArea;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTelephoneExtension() {
		return telephoneExtension;
	}
	public void setTelephoneExtension(String telephoneExtension) {
		this.telephoneExtension = telephoneExtension;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}
