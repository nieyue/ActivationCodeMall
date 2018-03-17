package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品公共
 * @author yy
 *
 */
@ApiModel(value="商品公共",description="商品公共")
public class MerCommon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品公共id
	 */
	@ApiModelProperty(value="商品公共id",example="商品公共id")
	private Integer merCommonId;
	
	/**
	 * 购物指南
	 */
	@ApiModelProperty(value="购物指南",example="购物指南")
	private String guide;
	/**
	 * 售后保障
	 */
	@ApiModelProperty(value="售后保障",example="售后保障")
	private String guarantee;
	/**
	 * 商品公共更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	public Integer getMerCommonId() {
		return merCommonId;
	}
	public void setMerCommonId(Integer merCommonId) {
		this.merCommonId = merCommonId;
	}
	public String getGuide() {
		return guide;
	}
	public void setGuide(String guide) {
		this.guide = guide;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
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
