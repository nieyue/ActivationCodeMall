package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Article;
import com.nieyue.dao.ArticleDao;
import com.nieyue.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{
	@Resource
	ArticleDao articleDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addArticle(Article article) {
		article.setCreateDate(new Date());
		article.setUpdateDate(new Date());
		article.setCommentNumber(0);
		if(article.getStatus()==null){
			article.setStatus(1);
		}
		boolean b = articleDao.addArticle(article);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delArticle(Integer articleId) {
		boolean b = articleDao.delArticle(articleId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateArticle(Article article) {
		boolean b = articleDao.updateArticle(article);
		return b;
	}

	@Override
	public Article loadArticle(Integer articleId) {
		Article r = articleDao.loadArticle(articleId);
		return r;
	}

	@Override
	public int countAll(
			Integer articleCateId,
			Integer  commentNumber,
			Date createDate,
			Date updateDate,
			Integer status
			) {
		int c = articleDao.countAll(
				articleCateId,commentNumber,createDate,updateDate,status);
		return c;
	}

	@Override
	public List<Article> browsePagingArticle(
			Integer articleCateId,
			Integer  commentNumber,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Article> l = articleDao.browsePagingArticle(
				articleCateId,
				commentNumber,
				createDate,
				updateDate,
				status,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}

	
}
