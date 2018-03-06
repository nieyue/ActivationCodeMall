package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 平台日
 * @author yy
 *
 */
@ApiModel(value="平台日",description="平台日")
public class PlatformDay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 平台日id
	 */
	@ApiModelProperty(value="平台日id",example="平台日id")
	private Integer platformDayId;
	/**
	 * 团购金额
	 */
	@ApiModelProperty(value="团购金额",example="团购金额")
	private Double teamPurchasePrice;
	/**
	 * 推荐佣金
	 */
	@ApiModelProperty(value="推荐佣金",example="推荐佣金")
	private Double recommendCommission;
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
	 * 拆分平台奖励
	 */
	@ApiModelProperty(value="拆分平台奖励励",example="拆分平台奖励")
	private Double splitPlatformReward;
	/**
	 * 平台日创建时间
	 */
	@ApiModelProperty(value="平台日创建时间",example="平台日创建时间")
	private Date createDate;
	/**
	 * 平台日更新时间
	 */
	@ApiModelProperty(value="平台日更新时间",example="平台日更新时间")
	private Date updateDate;
	public Integer getPlatformDayId() {
		return platformDayId;
	}
	public void setPlatformDayId(Integer platformDayId) {
		this.platformDayId = platformDayId;
	}
	public Double getTeamPurchasePrice() {
		return teamPurchasePrice;
	}
	public void setTeamPurchasePrice(Double teamPurchasePrice) {
		this.teamPurchasePrice = teamPurchasePrice;
	}
	public Double getRecommendCommission() {
		return recommendCommission;
	}
	public void setRecommendCommission(Double recommendCommission) {
		this.recommendCommission = recommendCommission;
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
	public Double getSplitPlatformReward() {
		return splitPlatformReward;
	}
	public void setSplitPlatformReward(Double splitPlatformReward) {
		this.splitPlatformReward = splitPlatformReward;
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
	
}
