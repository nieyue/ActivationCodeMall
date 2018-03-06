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
	 * 支付密码
	 */
	@ApiModelProperty(value="支付密码",example="支付密码")
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
	 * 招收学员佣金
	 */
	@ApiModelProperty(value="招收学员佣金",example="招收学员佣金")
	private Double recruitingCommission;
	/**
	 * 推荐佣金
	 */
	@ApiModelProperty(value="推荐佣金",example="推荐佣金")
	private Double recommendCommission;
	/**
	 * 团购账单
	 */
	@ApiModelProperty(value="团购账单",example="团购账单")
	private Double teamPurchasePrice;
	/**
	 * 拆分奖励
	 */
	@ApiModelProperty(value="拆分奖励",example="拆分奖励")
	private Double splitReward;
	/**
	 * 拆分上级奖励
	 */
	@ApiModelProperty(value="拆分上级奖励",example="拆分上级奖励")
	private Double splitParentReward;
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
	public Double getRecruitingCommission() {
		return recruitingCommission;
	}
	public void setRecruitingCommission(Double recruitingCommission) {
		this.recruitingCommission = recruitingCommission;
	}
	public Double getRecommendCommission() {
		return recommendCommission;
	}
	public void setRecommendCommission(Double recommendCommission) {
		this.recommendCommission = recommendCommission;
	}
	public Double getTeamPurchasePrice() {
		return teamPurchasePrice;
	}
	public void setTeamPurchasePrice(Double teamPurchasePrice) {
		this.teamPurchasePrice = teamPurchasePrice;
	}
	public Double getSplitReward() {
		return splitReward;
	}
	public void setSplitReward(Double splitReward) {
		this.splitReward = splitReward;
	}
	public Double getSplitParentReward() {
		return splitParentReward;
	}
	public void setSplitParentReward(Double splitParentReward) {
		this.splitParentReward = splitParentReward;
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
