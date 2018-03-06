package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.ArticleCate;

/**
 * 文章类型逻辑层接口
 * @author yy
 *
 */
public interface ArticleCateService {
	/** 新增文章类型 */	
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
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
