package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 勋章
 * @author yy
 *
 */
@ApiModel(value="勋章",description="勋章")
public class Medal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 勋章id
	 */
	@ApiModelProperty(value="勋章id",example="勋章id")
	private Integer medalId;
	/**
	 * 名称
	 */
	@ApiModelProperty(value="名称",example="名称")
	private String name;
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
	 * 勋章人ID
	 */
	@ApiModelProperty(value="勋章人ID",example="勋章人ID")
	private Integer accountId;
	/**
	 * 勋章项ID
	 */
	@ApiModelProperty(value="勋章项ID",example="勋章项ID")
	private Integer medalTermId;
	public Integer getMedalId() {
		return medalId;
	}
	public void setMedalId(Integer medalId) {
		this.medalId = medalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getMedalTermId() {
		return medalTermId;
	}
	public void setMedalTermId(Integer medalTermId) {
		this.medalTermId = medalTermId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
