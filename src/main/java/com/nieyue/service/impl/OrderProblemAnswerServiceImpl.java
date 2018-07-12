package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Order;
import com.nieyue.bean.OrderProblem;
import com.nieyue.bean.OrderProblemAnswer;
import com.nieyue.dao.OrderProblemAnswerDao;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.OrderProblemAnswerService;
import com.nieyue.service.OrderProblemService;
import com.nieyue.service.OrderService;
@Service
public class OrderProblemAnswerServiceImpl implements OrderProblemAnswerService{
	@Resource
	OrderProblemAnswerDao orderProblemAnswerDao;
	@Resource
	OrderProblemService orderProblemService;
	@Resource
	OrderService orderService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addOrderProblemAnswer(OrderProblemAnswer orderProblemAnswer) {
		List<OrderProblemAnswer> opal = this.browsePagingOrderProblemAnswer(orderProblemAnswer.getOrderProblemId(), null, 1, 1, "order_problem_id", "asc");
		if(opal.size()>0){
			throw new CommonRollbackException("已经回复了");
		}
		orderProblemAnswer.setCreateDate(new Date());
		boolean b = orderProblemAnswerDao.addOrderProblemAnswer(orderProblemAnswer);
		OrderProblem orderProblem = orderProblemService.loadOrderProblem(orderProblemAnswer.getOrderProblemId());
		Order order = orderService.loadOrder(orderProblem.getOrderId());
		if(order!=null&&(order.getSubstatus().equals(1)||order.getSubstatus().equals(2))){
			order.setStatus(5);//订单状态为问题单
			order.setSubstatus(2);//1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决
			b=orderService.updateOrder(order);
		}
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
			Integer orderProblemId,
			Integer accountId
			) {
		int c = orderProblemAnswerDao.countAll(
				 orderProblemId,
				 accountId);
		return c;
	}

	@Override
	public List<OrderProblemAnswer> browsePagingOrderProblemAnswer(
			Integer orderProblemId,
			Integer accountId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<OrderProblemAnswer> l = orderProblemAnswerDao.browsePagingOrderProblemAnswer(
				orderProblemId,
				accountId,
				pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
