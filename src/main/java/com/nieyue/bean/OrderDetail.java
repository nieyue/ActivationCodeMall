package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 订单详情
 * @author 聂跃
 */
@ApiModel(value="订单详情",description="订单详情")
public class OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单详情id
	 */
	@ApiModelProperty(value="订单详情id",example="订单详情id")
	private Integer orderDetailId;
	/**
	 * 名称
	 */
	@ApiModelProperty(value="名称",example="名称")
	private String name;
	/**
	 * 封面
	 */
	@ApiModelProperty(value="封面",example="封面")
	private String imgAddress;
	/**
	 * 类型名名称
	 */
	@ApiModelProperty(value="类型名名称",example="类型名名称")
	private String merCateName;
	/**
	 * 单价
	 */
	@ApiModelProperty(value="单价",example="单价")
	private Double unitPrice;
	/**
	 * 数量
	 */
	@ApiModelProperty(value="数量",example="数量")
	private Integer number;
	/**
	 * 总价
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
	 * 优惠劵id
	 */
	@ApiModelProperty(value="优惠劵id",example="优惠劵id")
	private Integer couponId;
	/**
	 * 商品id
	 */
	@ApiModelProperty(value="商品id",example="商品id")
	private Integer merId;
	/**
	 * 下单人
	 */
	@ApiModelProperty(value="订单id",example="订单id")
	private Integer orderId;
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public String getMerCateName() {
		return merCateName;
	}
	public void setMerCateName(String merCateName) {
		this.merCateName = merCateName;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
