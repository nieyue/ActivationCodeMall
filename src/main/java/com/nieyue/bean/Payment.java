package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付
 * @author yy
 *
 */
public class Payment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 支付id
	 */
	private Integer paymentId;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 内容
	 */
	private String body;
	/**
	 * 异步通知
	 */
	private String notifyUrl;
	/**
	 * 支付类型，默认1支付宝支付，2微信支付，3银联支付,4ios内购
	 */
	private Integer type;
	/**
	 * 平台订单号
	 */
	private String  orderNumber;
	/**
	 * 金额
	 */
	private Double money;
	/**
	 *状态，1已下单，2成功，3失败,4异常
	 */
	private Integer status;
	/**
	 *业务类型默认1VIP购买，2团购卡团购，3付费课程,4充值
	 */
	private Integer businessType;
	/**
	 *业务Id
	 */
	private Integer businessId;
	/**
	 *业务回调 ，如果是微服务就存带参数url，本项目直接存json参数
	 */
	private String businessNotifyUrl;
	/**
	 *账户id,外键
	 */
	private Integer accountId;
	/**
	 * 支付创建时间
	 */
	private Date createDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;

	public Payment() {
		super();
	}



	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


	public String getNotifyUrl() {
		return notifyUrl;
	}


	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}


	public String getBusinessNotifyUrl() {
		return businessNotifyUrl;
	}


	public void setBusinessNotifyUrl(String businessNotifyUrl) {
		this.businessNotifyUrl = businessNotifyUrl;
	}


	public Integer getBusinessType() {
		return businessType;
	}


	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

}
