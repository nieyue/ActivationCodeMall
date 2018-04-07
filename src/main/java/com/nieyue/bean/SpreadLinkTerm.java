package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 推广链接项
 * @author yy
 *
 */
@ApiModel(value="推广链接项",description="推广链接项")
public class SpreadLinkTerm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 推广链接项id
	 */
	@ApiModelProperty(value="推广链接项id",example="推广链接项id")
	private Integer spreadLinkTermId;
	/**
	 *链接
	 */
	@ApiModelProperty(value="链接",example="链接")
	private String link;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	public Integer getSpreadLinkTermId() {
		return spreadLinkTermId;
	}
	public void setSpreadLinkTermId(Integer spreadLinkTermId) {
		this.spreadLinkTermId = spreadLinkTermId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
