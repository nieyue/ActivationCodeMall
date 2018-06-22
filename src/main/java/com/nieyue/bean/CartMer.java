package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 购物车商品
 * @author yy
 *
 */
@ApiModel(value="购物车商品",description="购物车商品")
public class CartMer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 购物车商品id
	 */
	@ApiModelProperty(value="购物车商品id",example="购物车商品id")
	private Integer cartMerId;
	/**
	 *数量
	 */
	@ApiModelProperty(value="数量",example="数量")
	private Integer number;
	/**
	 *总价
	 */
	@ApiModelProperty(value="总价",example="总价")
	private Double totalPrice;
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
	 * 推广账户id
	 */
	@ApiModelProperty(value="推广账户id",example="推广账户id")
	private Integer spreadAccountId;
	/**
	 * 商品id外键
	 */
	@ApiModelProperty(value="商品id外键",example="商品id外键")
	private Integer merId;
	/**
	 * 账户id外键
	 */
	@ApiModelProperty(value="账户id外键",example="账户id外键")
	private Integer accountId;
	/**
	 * 商品
	 */
	@ApiModelProperty(value="商品",example="商品")
	private Mer mer;
	public Integer getCartMerId() {
		return cartMerId;
	}
	public void setCartMerId(Integer cartMerId) {
		this.cartMerId = cartMerId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
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
	public Mer getMer() {
		return mer;
	}
	public void setMer(Mer mer) {
		this.mer = mer;
	}
	public Integer getSpreadAccountId() {
		return spreadAccountId;
	}
	public void setSpreadAccountId(Integer spreadAccountId) {
		this.spreadAccountId = spreadAccountId;
	}
	
}
