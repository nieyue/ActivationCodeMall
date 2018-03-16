package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 财务
 * @author 聂跃
 * @date 2017年4月12日
 */
@ApiModel(value="财务",description="财务")
public class Finance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 财务id
	 */
	@ApiModelProperty(value="财务id",example="财务id")
	private Integer financeId;
	/**
	 * 提现密码
	 */
	@ApiModelProperty(value="提现密码",example="提现密码")
	private String password;
	/**
	 * 余额
	 */
	@ApiModelProperty(value="余额",example="余额")
	private Double money;
	/**
	 * 充值金额
	 */
	@ApiModelProperty(value="充值金额",example="充值金额")
	private Double recharge;
	/**
	 * 消费金额
	 */
	@ApiModelProperty(value="消费金额",example="消费金额")
	private Double consume;
	/**
	 * 提现金额
	 */
	@ApiModelProperty(value="提现金额",example="提现金额")
	private Double withdrawals;
	/**
	 * 退款金额
	 */
	@ApiModelProperty(value="退款金额",example="退款金额")
	private Double refund;
	/**
	 * 冻结金额
	 */
	@ApiModelProperty(value="冻结金额",example="冻结金额")
	private Double frozen;
	/**
	 * 推荐佣金
	 */
	@ApiModelProperty(value="推荐佣金",example="推荐佣金")
	private Double recommendCommission;
	/**
	 * 赠送金钱
	 */
	@ApiModelProperty(value="赠送金钱",example="赠送金钱")
	private Double baseProfit;
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
	public Integer getFinanceId() {
		return financeId;
	}
	public void setFinanceId(Integer financeId) {
		this.financeId = financeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getRecharge() {
		return recharge;
	}
	public void setRecharge(Double recharge) {
		this.recharge = recharge;
	}
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
	}
	public Double getWithdrawals() {
		return withdrawals;
	}
	public void setWithdrawals(Double withdrawals) {
		this.withdrawals = withdrawals;
	}
	public Double getRefund() {
		return refund;
	}
	public void setRefund(Double refund) {
		this.refund = refund;
	}
	public Double getFrozen() {
		return frozen;
	}
	public void setFrozen(Double frozen) {
		this.frozen = frozen;
	}
	public Double getRecommendCommission() {
		return recommendCommission;
	}
	public void setRecommendCommission(Double recommendCommission) {
		this.recommendCommission = recommendCommission;
	}
	public Double getBaseProfit() {
		return baseProfit;
	}
	public void setBaseProfit(Double baseProfit) {
		this.baseProfit = baseProfit;
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
	
}
