package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品关系
 * @author yy
 *
 */
@ApiModel(value="商品关系",description="商品关系")
public class MerRelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品关系id
	 */
	@ApiModelProperty(value="商品关系id",example="商品关系id")
	private Integer merRelationId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 平台商品id
	 */
	@ApiModelProperty(value="平台商品id",example="平台商品id")
	private Integer platformMerId;
	/**
	 * 商家商品id
	 */
	@ApiModelProperty(value="商家商品id",example="商家商品id")
	private Integer sellerMerId;
	/**
	 * 商家账户id外键
	 */
	@ApiModelProperty(value="商家账户id外键",example="商家账户id外键")
	private Integer sellerAccountId;
	public Integer getMerRelationId() {
		return merRelationId;
	}
	public void setMerRelationId(Integer merRelationId) {
		this.merRelationId = merRelationId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getPlatformMerId() {
		return platformMerId;
	}
	public void setPlatformMerId(Integer platformMerId) {
		this.platformMerId = platformMerId;
	}
	public Integer getSellerMerId() {
		return sellerMerId;
	}
	public void setSellerMerId(Integer sellerMerId) {
		this.sellerMerId = sellerMerId;
	}
	public Integer getSellerAccountId() {
		return sellerAccountId;
	}
	public void setSellerAccountId(Integer sellerAccountId) {
		this.sellerAccountId = sellerAccountId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
