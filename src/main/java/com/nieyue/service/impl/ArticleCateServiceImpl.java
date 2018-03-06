package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.ArticleCate;
import com.nieyue.dao.ArticleCateDao;
import com.nieyue.service.ArticleCateService;
@Service
public class ArticleCateServiceImpl implements ArticleCateService{
	@Resource
	ArticleCateDao articleCateDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addArticleCate(ArticleCate articleCate) {
		articleCate.setUpdateDate(new Date());
		boolean b = articleCateDao.addArticleCate(articleCate);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delArticleCate(Integer articleCateId) {
		boolean b = articleCateDao.delArticleCate(articleCateId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateArticleCate(ArticleCate articleCate) {
		articleCate.setUpdateDate(new Date());
		boolean b = articleCateDao.updateArticleCate(articleCate);
		return b;
	}

	@Override
	public ArticleCate loadArticleCate(Integer articleCateId) {
		ArticleCate r = articleCateDao.loadArticleCate(articleCateId);
		return r;
	}

	@Override
	public int countAll(
			) {
		int c = articleCateDao.countAll();
		return c;
	}

	@Override
	public List<ArticleCate> browsePagingArticleCate(
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<ArticleCate> l = articleCateDao.browsePagingArticleCate(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
