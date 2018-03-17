package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单问题
 * @author yy
 *
 */
@ApiModel(value="订单问题",description="订单问题")
public class OrderProblem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单问题id
	 */
	@ApiModelProperty(value="订单问题id",example="订单问题id")
	private Integer orderProblemId;
	/**
	 * 原因，0其他，1不能充值，2卡密无效，3提示卡密错误
	 */
	@ApiModelProperty(value="原因，0其他，1不能充值，2卡密无效，3提示卡密错误",example="原因，0其他，1不能充值，2卡密无效，3提示卡密错误")
	private Integer reason;
	/**
	 * 顺序，默认1初次，2二次，以此类推
	 */
	@ApiModelProperty(value="顺序，默认1初次，2二次，以此类推",example="顺序，默认1初次，2二次，以此类推")
	private Integer number;
	/**
	 * 内容
	 */
	@ApiModelProperty(value="内容",example="内容")
	private String content;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 商品id
	 */
	@ApiModelProperty(value="商品id",example="商品id")
	private Integer merId;
	/**
	 * 订单id外键
	 */
	@ApiModelProperty(value="订单id外键",example="订单id外键")
	private Integer orderId;
	/**
	 * 提问人id外键
	 */
	@ApiModelProperty(value="提问人id外键",example="提问人id外键")
	private Integer accountId;
	public Integer getOrderProblemId() {
		return orderProblemId;
	}
	public void setOrderProblemId(Integer orderProblemId) {
		this.orderProblemId = orderProblemId;
	}
	public Integer getReason() {
		return reason;
	}
	public void setReason(Integer reason) {
		this.reason = reason;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
