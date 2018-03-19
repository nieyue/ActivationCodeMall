package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.OrderProblemAnswer;
import com.nieyue.dao.OrderProblemAnswerDao;
import com.nieyue.service.OrderProblemAnswerService;
@Service
public class OrderProblemAnswerServiceImpl implements OrderProblemAnswerService{
	@Resource
	OrderProblemAnswerDao orderProblemAnswerDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addOrderProblemAnswer(OrderProblemAnswer orderProblemAnswer) {
		orderProblemAnswer.setCreateDate(new Date());
		boolean b = orderProblemAnswerDao.addOrderProblemAnswer(orderProblemAnswer);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delOrderProblemAnswer(Integer orderProblemAnswerId) {
		boolean b = orderProblemAnswerDao.delOrderProblemAnswer(orderProblemAnswerId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateOrderProblemAnswer(OrderProblemAnswer orderProblemAnswer) {
		boolean b = orderProblemAnswerDao.updateOrderProblemAnswer(orderProblemAnswer);
		return b;
	}

	@Override
	public OrderProblemAnswer loadOrderProblemAnswer(Integer orderProblemAnswerId) {
		OrderProblemAnswer r = orderProblemAnswerDao.loadOrderProblemAnswer(orderProblemAnswerId);
		return r;
	}

	@Override
	public int countAll(
			) {
		int c = orderProblemAnswerDao.countAll();
		return c;
	}

	@Override
	public List<OrderProblemAnswer> browsePagingOrderProblemAnswer(
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<OrderProblemAnswer> l = orderProblemAnswerDao.browsePagingOrderProblemAnswer(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
