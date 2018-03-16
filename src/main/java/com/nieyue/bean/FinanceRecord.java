package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 财务记录
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="财务记录",description="财务记录")
public class FinanceRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 财务记录id
	 */
	@ApiModelProperty(value="财务记录id",example="财务记录id")
	private Integer financeRecordId;
	/**
	 * 方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
	 */
	@ApiModelProperty(value="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银",example="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银")
	private Integer method;
	/**
	 * 类型，1购买商品，2账户提现，3退款，4诚信押金
	 */
	@ApiModelProperty(value="类型，1购买商品，2账户提现，3退款，4诚信押金",example="类型，1购买商品，2账户提现，3退款，4诚信押金")
	private Integer type;
	/**
	 * 交易单号
	 */
	@ApiModelProperty(value="交易单号",example="交易单号")
	private String transactionNumber;
	/**
	 * 手续费
	 */
	@ApiModelProperty(value="手续费",example="手续费")
	private Double brokerage;
	/**
	 * 金额
	 */
	@ApiModelProperty(value="金额",example="金额")
	private Double money;
	/**
	 * 实际金额
	 */
	@ApiModelProperty(value="实际金额",example="实际金额")
	private Double realMoney;
	/**
	 * 状态，默认1待处理，2成功，3已拒绝
	 */
	@ApiModelProperty(value="状态，默认1待处理，2成功，3已拒绝",example="状态，默认1待处理，2成功，3已拒绝")
	private Integer status;
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
	 * 账户id外键
	 */
	@ApiModelProperty(value="账户id外键",example="账户id外键")
	private Integer accountId;
	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value="真实姓名",example="真实姓名")
	private String realname;
	public Integer getFinanceRecordId() {
		return financeRecordId;
	}
	public void setFinanceRecordId(Integer financeRecordId) {
		this.financeRecordId = financeRecordId;
	}
	public Integer getMethod() {
		return method;
	}
	public void setMethod(Integer method) {
		this.method = method;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public Double getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(Double brokerage) {
		this.brokerage = brokerage;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getRealMoney() {
		return realMoney;
	}
	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
