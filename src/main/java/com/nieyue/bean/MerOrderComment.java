package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品订单评论
 * @author yy
 *
 */
@ApiModel(value="商品订单评论",description="商品订单评论")
public class MerOrderComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品订单评论id
	 */
	@ApiModelProperty(value="商品订单评论id",example="商品订单评论id")
	private Integer merOrderCommentId;
	/**
	 *评分
	 */
	@ApiModelProperty(value="评分",example="评分")
	private Double merScore;
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
	 * 评论人id外键
	 */
	@ApiModelProperty(value="评论人id外键",example="评论人id外键")
	private Integer accountId;
	/**
	 * 评论人
	 */
	@ApiModelProperty(value="评论人",example="评论人")
	private Account account;
	public Integer getMerOrderCommentId() {
		return merOrderCommentId;
	}
	public void setMerOrderCommentId(Integer merOrderCommentId) {
		this.merOrderCommentId = merOrderCommentId;
	}
	public Double getMerScore() {
		return merScore;
	}
	public void setMerScore(Double merScore) {
		this.merScore = merScore;
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
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
