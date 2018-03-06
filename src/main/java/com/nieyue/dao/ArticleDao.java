package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Article;

/**
 * 文章数据库接口
 * @author yy
 *
 */
@Mapper
public interface ArticleDao {
	/** 新增文章*/	
	public boolean addArticle(Article article) ;	
	/** 删除文章 */	
	public boolean delArticle(Integer articleId) ;
	/** 更新文章*/	
	public boolean updateArticle(Article article);
	/** 装载文章 */	
	public Article loadArticle(Integer articleId);	
	/** 文章总共数目 */	
	public int countAll(
			@Param("articleCateId")Integer articleCateId,
			@Param("commentNumber")Integer commentNumber,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页文章信息 */
	public List<Article> browsePagingArticle(
			@Param("articleCateId")Integer articleCateId,
			@Param("commentNumber")Integer commentNumber,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
