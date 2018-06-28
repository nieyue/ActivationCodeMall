package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 推广链接
 * @author yy
 *
 */
@ApiModel(value="推广链接",description="推广链接")
public class SpreadLink implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 推广链接id
	 */
	@ApiModelProperty(value="推广链接id",example="推广链接id")
	private Integer spreadLinkId;
	/**
	 *链接
	 */
	@ApiModelProperty(value="链接",example="链接")
	private String link;
	/**
	 *推广次数
	 */
	@ApiModelProperty(value="推广次数",example="推广次数")
	private Integer spreadNumber;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 *商品id,外键
	 */
	@ApiModelProperty(value="商品id,外键",example="商品id,外键")
	private Integer merId;
	/**
	 *推广账户id
	 */
	@ApiModelProperty(value="推广账户id",example="推广账户id")
	private Integer accountId;
	public Integer getSpreadLinkId() {
		return spreadLinkId;
	}
	public void setSpreadLinkId(Integer spreadLinkId) {
		this.spreadLinkId = spreadLinkId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getSpreadNumber() {
		return spreadNumber;
	}
	public void setSpreadNumber(Integer spreadNumber) {
		this.spreadNumber = spreadNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
}
