package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 文章评论
 * @author 聂跃
 */
@ApiModel(value="文章评论",description="文章评论")
public class ArticleComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 文章评论id
	 */
	@ApiModelProperty(value="文章评论id",example="文章评论id")
	private Integer articleCommentId;
	/**
	 * 内容
	 */
	@ApiModelProperty(value="内容",example="内容")
	private String content;
	/**
	 *点赞数
	 */
	@ApiModelProperty(value="点赞数",example="点赞数")
	private Integer pointNumber;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 文章id外键
	 */
	@ApiModelProperty(value="文章id外键",example="文章id外键")
	private Integer articleId;
	/**
	 * 评论人id外键
	 */
	@ApiModelProperty(value="评论人id外键",example="评论人id外键")
	private Integer accountId;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value="昵称",example="昵称")
	private String nickname;
	/**
	 * 图像
	 */
	@ApiModelProperty(value="图像",example="图像")
	private String icon;
	public Integer getArticleCommentId() {
		return articleCommentId;
	}
	public void setArticleCommentId(Integer articleCommentId) {
		this.articleCommentId = articleCommentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPointNumber() {
		return pointNumber;
	}
	public void setPointNumber(Integer pointNumber) {
		this.pointNumber = pointNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
