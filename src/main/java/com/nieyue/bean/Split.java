package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 拆分
 * @author yy
 *
 */
@ApiModel(value="拆分",description="拆分")
public class Split implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 拆分id
	 */
	@ApiModelProperty(value="拆分id",example="拆分id")
	private Integer splitId;
	
	/**
	 * 昵称
	 */
	@ApiModelProperty(value="昵称",example="昵称")
	private String nickname;
	/**
	 *会员账号
	 */
	@ApiModelProperty(value="会员账号",example="会员账号")
	private String phone;
	/**
	 * 联系电话
	 */
	@ApiModelProperty(value="联系电话",example="联系电话")
	private String contactPhone;
	/**
	 *备注
	 */
	@ApiModelProperty(value="备注",example="备注")
	private String remark;
	/**
	 *数量
	 */
	@ApiModelProperty(value="数量",example="数量")
	private Integer number;
	/**
	 *金额
	 */
	@ApiModelProperty(value="金额",example="金额")
	private Double price;
	/**
	 * 申请时间
	 */
	@ApiModelProperty(value="申请时间",example="申请时间")
	private Date applyDate;
	/**
	 * 拆分时间
	 */
	@ApiModelProperty(value="拆分时间",example="拆分时间")
	private Date splitDate;
	/**
	 * 创建更新时间
	 */
	@ApiModelProperty(value="创建更新时间",example="创建更新时间")
	private Date createDate;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	/**
	 * 拆分状态，默认0已申请，1已拆分，2已拒绝，3已退款，4已推荐给上级
	 */
	@ApiModelProperty(value="拆分状态，默认0已申请，1已拆分，2已拒绝，3已退款，4已推荐给上级",example="拆分状态，默认0已申请，1已拆分，2已拒绝，3已退款，4已推荐给上级")
	private Integer status;
	/**
	 * 推荐人ID
	 */
	@ApiModelProperty(value="推荐人ID",example="推荐人ID")
	private Integer recommendAccountId;
	/**
	 * 账户自身id,邀请码
	 */
	@ApiModelProperty(value="账户自身id,邀请码",example="账户自身id,邀请码")
	private Integer accountId;
	/**
	 * 购买者id,外键
	 */
	@ApiModelProperty(value="购买者id,外键",example="购买者id,外键")
	private Integer buyAccountId;
	/**
	 * 订单id,外键
	 */
	@ApiModelProperty(value="订单id,外键",example="订单id,外键")
	private Integer orderId;
	public Integer getSplitId() {
		return splitId;
	}
	public void setSplitId(Integer splitId) {
		this.splitId = splitId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getSplitDate() {
		return splitDate;
	}
	public void setSplitDate(Date splitDate) {
		this.splitDate = splitDate;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getBuyAccountId() {
		return buyAccountId;
	}
	public void setBuyAccountId(Integer buyAccountId) {
		this.buyAccountId = buyAccountId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRecommendAccountId() {
		return recommendAccountId;
	}
	public void setRecommendAccountId(Integer recommendAccountId) {
		this.recommendAccountId = recommendAccountId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
}
