package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.ArticleComment;

/**
 * 文章评论逻辑层接口
 * @author yy
 *
 */
public interface ArticleCommentService {
	/** 新增文章评论 */	
	public boolean addArticleComment(ArticleComment articleComment) ;	
	/** 删除文章评论 */	
	public boolean delArticleComment(Integer articleCommentId) ;
	/** 更新文章评论*/	
	public boolean updateArticleComment(ArticleComment articleComment);
	/** 装载文章评论 */	
	public ArticleComment loadArticleComment(Integer articleCommentId);	
	/** 文章评论总共数目 */	
	public int countAll(
			Integer pointNumber,
			Integer articleId,
			Integer accountId
			);
	/** 分页文章评论信息 */
	public List<ArticleComment> browsePagingArticleComment(
			Integer pointNumber,
			Integer articleId,
			Integer accountId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
