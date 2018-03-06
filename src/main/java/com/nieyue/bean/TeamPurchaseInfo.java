package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 团购信息
 * @author 聂跃
 */
@ApiModel(value="团购信息",description="团购信息")
public class TeamPurchaseInfo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 团购信息id
	 */
	@ApiModelProperty(value="团购信息id",example="团购信息id")
	private Integer teamPurchaseInfoId;
	/**
	 * 团购卡余量
	 */
	@ApiModelProperty(value="团购卡余量",example="团购卡余量")
	private Integer teamPurchaseCardAllowance;
	/**
	 * 已团购（张）
	 */
	@ApiModelProperty(value="已团购（张）",example="已团购（张）")
	private Integer alreadyTeamPurchase;
	/**
	 * 待处理（张）
	 */
	@ApiModelProperty(value="待处理（张）",example="待处理（张）")
	private Integer waitDispose;
	/**
	 * 待处理总额
	 */
	@ApiModelProperty(value="待处理总额",example="待处理总额")
	private Double waitDisposePrice;
	/**
	 * 待处理更新时间
	 */
	@ApiModelProperty(value="待处理更新时间",example="待处理更新时间")
	private Date waitDisposeUpdateDate;
	/**
	 *团购成功（张）
	 */
	@ApiModelProperty(value="团购成功（张）",example="团购成功（张）")
	private Integer teamPurchaseSuccess;
	/**
	 *团购成功总额
	 */
	@ApiModelProperty(value="团购成功总额",example="团购成功总额")
	private Double teamPurchaseSuccessPrice;
	/**
	 *团购成功更新时间
	 */
	@ApiModelProperty(value="团购成功更新时间",example="团购成功更新时间")
	private Date teamPurchaseSuccessUpdateDate;
	/**
	 *已拆分（张）
	 */
	@ApiModelProperty(value="已拆分（张）",example="已拆分（张）")
	private Integer alreadySplit;
	/**
	 *已拆分总额
	 */
	@ApiModelProperty(value="已拆分总额",example="已拆分总额")
	private Double alreadySplitPrice;
	/**
	 *已拆分更新时间
	 */
	@ApiModelProperty(value="已拆分更新时间",example="已拆分更新时间")
	private Date alreadySplitUpdateDate;
	/**
	 *已推荐给上级（张）
	 */
	@ApiModelProperty(value="已推荐给上级（张）",example="已推荐给上级（张）")
	private Integer alreadyRecommend;
	/**
	 *已推荐给上级总额
	 */
	@ApiModelProperty(value="已推荐给上级总额",example="已推荐给上级总额")
	private Double alreadyRecommendPrice;
	/**
	 *已推荐给上级更新时间
	 */
	@ApiModelProperty(value="已推荐给上级更新时间",example="已推荐给上级更新时间")
	private Date alreadyRecommendUpdateDate;
	/**
	 *创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	/**
	 * 账户id,外键
	 */
	@ApiModelProperty(value="账户id,外键",example="账户id,外键")
	private Integer accountId;
	public Integer getTeamPurchaseInfoId() {
		return teamPurchaseInfoId;
	}
	public void setTeamPurchaseInfoId(Integer teamPurchaseInfoId) {
		this.teamPurchaseInfoId = teamPurchaseInfoId;
	}
	public Integer getTeamPurchaseCardAllowance() {
		return teamPurchaseCardAllowance;
	}
	public void setTeamPurchaseCardAllowance(Integer teamPurchaseCardAllowance) {
		this.teamPurchaseCardAllowance = teamPurchaseCardAllowance;
	}
	public Integer getAlreadyTeamPurchase() {
		return alreadyTeamPurchase;
	}
	public void setAlreadyTeamPurchase(Integer alreadyTeamPurchase) {
		this.alreadyTeamPurchase = alreadyTeamPurchase;
	}
	public Integer getWaitDispose() {
		return waitDispose;
	}
	public void setWaitDispose(Integer waitDispose) {
		this.waitDispose = waitDispose;
	}
	public Double getWaitDisposePrice() {
		return waitDisposePrice;
	}
	public void setWaitDisposePrice(Double waitDisposePrice) {
		this.waitDisposePrice = waitDisposePrice;
	}
	public Date getWaitDisposeUpdateDate() {
		return waitDisposeUpdateDate;
	}
	public void setWaitDisposeUpdateDate(Date waitDisposeUpdateDate) {
		this.waitDisposeUpdateDate = waitDisposeUpdateDate;
	}
	public Integer getTeamPurchaseSuccess() {
		return teamPurchaseSuccess;
	}
	public void setTeamPurchaseSuccess(Integer teamPurchaseSuccess) {
		this.teamPurchaseSuccess = teamPurchaseSuccess;
	}
	public Double getTeamPurchaseSuccessPrice() {
		return teamPurchaseSuccessPrice;
	}
	public void setTeamPurchaseSuccessPrice(Double teamPurchaseSuccessPrice) {
		this.teamPurchaseSuccessPrice = teamPurchaseSuccessPrice;
	}
	public Date getTeamPurchaseSuccessUpdateDate() {
		return teamPurchaseSuccessUpdateDate;
	}
	public void setTeamPurchaseSuccessUpdateDate(Date teamPurchaseSuccessUpdateDate) {
		this.teamPurchaseSuccessUpdateDate = teamPurchaseSuccessUpdateDate;
	}
	public Integer getAlreadySplit() {
		return alreadySplit;
	}
	public void setAlreadySplit(Integer alreadySplit) {
		this.alreadySplit = alreadySplit;
	}
	public Double getAlreadySplitPrice() {
		return alreadySplitPrice;
	}
	public void setAlreadySplitPrice(Double alreadySplitPrice) {
		this.alreadySplitPrice = alreadySplitPrice;
	}
	public Date getAlreadySplitUpdateDate() {
		return alreadySplitUpdateDate;
	}
	public void setAlreadySplitUpdateDate(Date alreadySplitUpdateDate) {
		this.alreadySplitUpdateDate = alreadySplitUpdateDate;
	}
	public Integer getAlreadyRecommend() {
		return alreadyRecommend;
	}
	public void setAlreadyRecommend(Integer alreadyRecommend) {
		this.alreadyRecommend = alreadyRecommend;
	}
	public Double getAlreadyRecommendPrice() {
		return alreadyRecommendPrice;
	}
	public void setAlreadyRecommendPrice(Double alreadyRecommendPrice) {
		this.alreadyRecommendPrice = alreadyRecommendPrice;
	}
	public Date getAlreadyRecommendUpdateDate() {
		return alreadyRecommendUpdateDate;
	}
	public void setAlreadyRecommendUpdateDate(Date alreadyRecommendUpdateDate) {
		this.alreadyRecommendUpdateDate = alreadyRecommendUpdateDate;
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
