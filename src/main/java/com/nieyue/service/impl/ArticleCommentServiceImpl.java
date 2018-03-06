package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Article;
import com.nieyue.bean.ArticleComment;
import com.nieyue.dao.ArticleCommentDao;
import com.nieyue.service.ArticleCommentService;
import com.nieyue.service.ArticleService;
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService{
	@Resource
	ArticleCommentDao articleCommentDao;
	@Resource
	ArticleService articleService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addArticleComment(ArticleComment articleComment) {
		articleComment.setCreateDate(new Date());
		articleComment.setPointNumber(0);
		boolean b = articleCommentDao.addArticleComment(articleComment);
		if(b){
			Article article = articleService.loadArticle(articleComment.getArticleId());
			int acc = articleCommentDao.countAll(null, article.getArticleId(), null);
			article.setCommentNumber(acc);
			b=articleService.updateArticle(article);
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delArticleComment(Integer articleCommentId) {
		ArticleComment articleComment = articleCommentDao.loadArticleComment(articleCommentId);
		boolean b = articleCommentDao.delArticleComment(articleCommentId);
		if(b){
			Article article = articleService.loadArticle(articleComment.getArticleId());
			int acc = articleCommentDao.countAll(null, article.getArticleId(), null);
			article.setCommentNumber(acc);
			b=articleService.updateArticle(article);
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateArticleComment(ArticleComment articleComment) {
		boolean b = articleCommentDao.updateArticleComment(articleComment);
		return b;
	}

	@Override
	public ArticleComment loadArticleComment(Integer articleCommentId) {
		ArticleComment r = articleCommentDao.loadArticleComment(articleCommentId);
		return r;
	}

	@Override
	public int countAll(
			Integer pointNumber,
			Integer articleId,
			Integer accountId) {
		int c = articleCommentDao.countAll(
				pointNumber,articleId,accountId);
		return c;
	}

	@Override
	public List<ArticleComment> browsePagingArticleComment(
			Integer pointNumber,
			Integer articleId,
			Integer accountId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<ArticleComment> l = articleCommentDao.browsePagingArticleComment(
				pointNumber,
				articleId,
				accountId,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}

	
}
