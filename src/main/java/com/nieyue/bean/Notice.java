package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通知
 * @author 聂跃
 */
@ApiModel(value="通知",description="通知")
public class Notice  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 通知id
	 */
	@ApiModelProperty(value="通知id",example="通知id")
	private Integer noticeId;
	/**
	 * 范围，1全局，2个人
	 */
	@ApiModelProperty(value="范围，1全局，2个人",example="范围，1全局，2个人")
	private Integer region;
	/**
	 * 类型，1系统消息，2商品动态，3问题单反馈，4商品申请自营，5提现申请，6新增商品类型
	 */
	@ApiModelProperty(value="类型，1系统消息，2商品动态，3问题单反馈，4商品申请自营，5提现申请，6新增商品类型",example="类型，1系统消息，2商品动态，3问题单反馈，4商品申请自营，5提现申请，6新增商品类型")
	private Integer type;
	/**
	 * 标题
	 */
	@ApiModelProperty(value="标题",example="标题")
	private String title;
	/**
	 * 图片
	 */
	@ApiModelProperty(value="图片",example="图片")
	private String imgAddress;
	/**
	 * 内容
	 */
	@ApiModelProperty(value="内容",example="内容")
	private String content;
	/**
	 *状态，默认为1审核中，2申请成功，3申请失败
	 */
	@ApiModelProperty(value="状态，默认为1审核中，2申请成功，3申请失败",example="状态，默认为1审核中，2申请成功，3申请失败")
	private Integer status;
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
	 * 申请人id
	 */
	@ApiModelProperty(value="申请人id",example="申请人id")
	private Integer accountId;
	/**
	 * 接收人id外键
	 */
	@ApiModelProperty(value="接收人id外键",example="接收人id外键")
	private Integer receiveAccountId;
	/**
	 * 业务id,外键
	 */
	@ApiModelProperty(value="业务id,外键",example="业务id,外键")
	private Integer businessId;
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public Integer getRegion() {
		return region;
	}
	public void setRegion(Integer region) {
		this.region = region;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Integer getReceiveAccountId() {
		return receiveAccountId;
	}
	public void setReceiveAccountId(Integer receiveAccountId) {
		this.receiveAccountId = receiveAccountId;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
