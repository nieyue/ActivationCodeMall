package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.ArticleComment;

/**
 * 文章评论数据库接口
 * @author yy
 *
 */
@Mapper
public interface ArticleCommentDao {
	/** 新增文章评论*/	
	public boolean addArticleComment(ArticleComment articleComment) ;	
	/** 删除文章评论 */	
	public boolean delArticleComment(Integer articleCommentId) ;
	/** 更新文章评论*/	
	public boolean updateArticleComment(ArticleComment articleComment);
	/** 装载文章评论 */	
	public ArticleComment loadArticleComment(Integer articleCommentId);	
	/** 文章评论总共数目 */	
	public int countAll(
			@Param("pointNumber")Integer pointNumber,
			@Param("articleId")Integer articleId,
			@Param("accountId")Integer accountId
			);	
	/** 分页文章评论信息 */
	public List<ArticleComment> browsePagingArticleComment(
			@Param("pointNumber")Integer pointNumber,
			@Param("articleId")Integer articleId,
			@Param("accountId")Integer accountId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
