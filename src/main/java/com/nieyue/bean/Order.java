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
	 * 类型，1购买商品，2账户提现，3退款，4诚信押金
	 */
	@ApiModelProperty(value="类型，1购买商品，2账户提现，3退款，4诚信押金",example="类型，1购买商品，2账户提现，3退款，4诚信押金")
	private Integer type;
	/**
	 * 方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
	 */
	@ApiModelProperty(value="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银",example="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银")
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
	 * 支付日期
	 */
	@ApiModelProperty(value="支付日期",example="支付日期")
	private Date paymentDate;
	/**
	 * 订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
	 */
	@ApiModelProperty(value="订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除",example="订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除")
	private Integer status;
	/**
	 * 子状态，2（1待支付），3（1冻结单,2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1已取消），7（1已删除）
	 */
	@ApiModelProperty(value="子状态，2（1待支付），3（1冻结单,2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1正常取消,2订单商品库存不够），7（1已删除）",example="子状态，2（1待支付），3（1冻结单,2已完成），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1已取消），7（1已删除）")
	private Integer substatus;
	/**
	 * 商户id
	 */
	@ApiModelProperty(value="商户id",example="商户id")
	private Integer merchantAccountId;
	/**
	 * 推广账户id
	 */
	@ApiModelProperty(value="推广账户id",example="推广账户id")
	private Integer spreadAccountId;
	/**
	 * 下单人
	 */
	@ApiModelProperty(value="下单人",example="下单人")
	private Integer accountId;
	/**
	 * 订单详情列表
	 */
	private List<OrderDetail> orderDetailList;
	/**
	 * 订单收货信息
	 */
	private OrderReceiptInfo orderReceiptInfo;
	/**
	 * 订单问题列表
	 */
	private List<OrderProblem> orderProblemList;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
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
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSubstatus() {
		return substatus;
	}
	public void setSubstatus(Integer substatus) {
		this.substatus = substatus;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getSpreadAccountId() {
		return spreadAccountId;
	}
	public void setSpreadAccountId(Integer spreadAccountId) {
		this.spreadAccountId = spreadAccountId;
	}
	public OrderReceiptInfo getOrderReceiptInfo() {
		return orderReceiptInfo;
	}
	public void setOrderReceiptInfo(OrderReceiptInfo orderReceiptInfo) {
		this.orderReceiptInfo = orderReceiptInfo;
	}
	public Integer getMerchantAccountId() {
		return merchantAccountId;
	}
	public void setMerchantAccountId(Integer merchantAccountId) {
		this.merchantAccountId = merchantAccountId;
	}
	public List<OrderProblem> getOrderProblemList() {
		return orderProblemList;
	}
	public void setOrderProblemList(List<OrderProblem> orderProblemList) {
		this.orderProblemList = orderProblemList;
	}
	
}
