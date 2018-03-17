package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单问题反馈
 * @author yy
 *
 */
@ApiModel(value="订单问题反馈",description="订单问题反馈")
public class OrderProblemAnswer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单问题反馈id
	 */
	@ApiModelProperty(value="订单问题反馈id",example="订单问题反馈id")
	private Integer orderProblemAnswerId;
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
	 * 商品订单问题id
	 */
	@ApiModelProperty(value="商品订单问题id",example="商品订单问题id")
	private Integer orderProblemId;
	/**
	 * 回复人id外键
	 */
	@ApiModelProperty(value="回复人id外键",example="回复人id外键")
	private Integer accountId;
	public Integer getOrderProblemAnswerId() {
		return orderProblemAnswerId;
	}
	public void setOrderProblemAnswerId(Integer orderProblemAnswerId) {
		this.orderProblemAnswerId = orderProblemAnswerId;
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
	public Integer getOrderProblemId() {
		return orderProblemId;
	}
	public void setOrderProblemId(Integer orderProblemId) {
		this.orderProblemId = orderProblemId;
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
