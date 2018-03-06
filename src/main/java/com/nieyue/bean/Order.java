package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 订单
 * @author 聂跃
 */
@ApiModel(value="订单",description="订单")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单id
	 */
	@ApiModelProperty(value="订单id",example="订单id")
	private Integer orderId;
	/**
	 * 订单号
	 */
	@ApiModelProperty(value="订单号",example="订单号")
	private String orderNumber;
	/**
	 * 类型，1VIP购买，2团购卡团购，3付费课程
	 */
	@ApiModelProperty(value="类型，1VIP购买，2团购卡团购，3付费课程",example="类型，1VIP购买，2团购卡团购，3付费课程")
	private Integer type;
	/**
	 * 支付类型，1支付宝，2微信,3余额支付,4ios内购
	 */
	@ApiModelProperty(value="支付类型，1支付宝，2微信,3余额支付,4ios内购",example="支付类型，1支付宝，2微信,3余额支付,4ios内购")
	private Integer payType;
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
	 * 订单状态，-1待处理删除，0已完成删除,1待处理，2已完成
	 */
	@ApiModelProperty(value="订单状态，-1待处理删除，0已完成删除,1待处理，2已完成",example="订单状态，-1待处理删除，0已完成删除,1待处理，2已完成")
	private Integer status;
	/**
	 * 下单人
	 */
	@ApiModelProperty(value="下单人",example="下单人")
	private Integer accountId;
	/**
	 * 订单详情列表
	 */
	private List<OrderDetail> orderDetailList;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
}
