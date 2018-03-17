package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品公告
 * @author yy
 *
 */
@ApiModel(value="商品公告",description="商品公告")
public class MerNotice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品公告id
	 */
	@ApiModelProperty(value="商品公告id",example="商品公告id")
	private Integer merNoticeId;
	
	/**
	 * 标题
	 */
	@ApiModelProperty(value="标题",example="标题")
	private String title;
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
	 * 商品id外键
	 */
	@ApiModelProperty(value="商品id外键",example="商品id外键")
	private Integer merId;
	public Integer getMerNoticeId() {
		return merNoticeId;
	}
	public void setMerNoticeId(Integer merNoticeId) {
		this.merNoticeId = merNoticeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
