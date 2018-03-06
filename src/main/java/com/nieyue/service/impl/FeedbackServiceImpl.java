package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Feedback;
import com.nieyue.dao.FeedbackDao;
import com.nieyue.service.FeedbackService;
@Service
public class FeedbackServiceImpl implements FeedbackService{
	@Resource
	FeedbackDao feedbackDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addFeedback(Feedback feedback) {
		feedback.setCreateDate(new Date());
		boolean b = feedbackDao.addFeedback(feedback);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delFeedback(Integer feedbackId) {
		boolean b = feedbackDao.delFeedback(feedbackId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateFeedback(Feedback feedback) {
		boolean b = feedbackDao.updateFeedback(feedback);
		return b;
	}

	@Override
	public Feedback loadFeedback(Integer feedbackId) {
		Feedback r = feedbackDao.loadFeedback(feedbackId);
		return r;
	}

	@Override
	public int countAll(
			) {
		int c = feedbackDao.countAll();
		return c;
	}

	@Override
	public List<Feedback> browsePagingFeedback(
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Feedback> l = feedbackDao.browsePagingFeedback(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
