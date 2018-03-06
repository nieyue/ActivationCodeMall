package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.ArticleCate;

/**
 * 文章类型数据库接口
 * @author yy
 *
 */
@Mapper
public interface ArticleCateDao {
	/** 新增文章类型*/	
	public boolean addArticleCate(ArticleCate articleCate) ;	
	/** 删除文章类型 */	
	public boolean delArticleCate(Integer articleCateId) ;
	/** 更新文章类型*/	
	public boolean updateArticleCate(ArticleCate articleCate);
	/** 装载文章类型 */	
	public ArticleCate loadArticleCate(Integer articleCateId);	
	/** 文章类型总共数目 */	
	public int countAll(
			);	
	/** 分页文章类型信息 */
	public List<ArticleCate> browsePagingArticleCate(
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
