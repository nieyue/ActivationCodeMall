package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 文章
 * @author 聂跃
 */
@ApiModel(value="文章",description="文章")
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 文章id
	 */
	@ApiModelProperty(value="文章id",example="文章id")
	private Integer articleId;
	/**
	 * 标题
	 */
	@ApiModelProperty(value="标题",example="标题")
	private String title;
	/**
	 * 子标题
	 */
	@ApiModelProperty(value="子标题",example="子标题")
	private String subtitle;
	/**
	 *封面
	 */
	@ApiModelProperty(value="封面",example="封面")
	private String imgAddress;
	/**
	 * 跳转url
	 */
	@ApiModelProperty(value="跳转url",example="跳转url")
	private String redirectUrl;
	/**
	 * 内容
	 */
	@ApiModelProperty(value="内容",example="内容")
	private String content;
	/**
	 * 评论数
	 */
	@ApiModelProperty(value="评论数",example="评论数")
	private Integer commentNumber;
	/**
	 * 状态,下架0，上架1
	 */
	@ApiModelProperty(value="状态,下架0，上架1",example="状态,下架0，上架1")
	private Integer status;
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
	 * 文章类型id外键
	 */
	@ApiModelProperty(value="文章类型id外键",example="文章类型id外键")
	private Integer articleCateId;
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(Integer commentNumber) {
		this.commentNumber = commentNumber;
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
	public Integer getArticleCateId() {
		return articleCateId;
	}
	public void setArticleCateId(Integer articleCateId) {
		this.articleCateId = articleCateId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
}
