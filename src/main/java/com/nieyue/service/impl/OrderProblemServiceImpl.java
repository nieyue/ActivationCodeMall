package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.OrderProblem;
import com.nieyue.dao.OrderProblemDao;
import com.nieyue.service.OrderProblemService;
@Service
public class OrderProblemServiceImpl implements OrderProblemService{
	@Resource
	OrderProblemDao orderProblemDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addOrderProblem(OrderProblem orderProblem) {
		orderProblem.setCreateDate(new Date());
		boolean b = orderProblemDao.addOrderProblem(orderProblem);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delOrderProblem(Integer orderProblemId) {
		boolean b = orderProblemDao.delOrderProblem(orderProblemId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateOrderProblem(OrderProblem orderProblem) {
		boolean b = orderProblemDao.updateOrderProblem(orderProblem);
		return b;
	}

	@Override
	public OrderProblem loadOrderProblem(Integer orderProblemId) {
		OrderProblem r = orderProblemDao.loadOrderProblem(orderProblemId);
		return r;
	}

	@Override
	public int countAll(
			Integer number,
			Integer merId,
			Integer orderId,
			Integer accountId
			) {
		int c = orderProblemDao.countAll(number,merId,orderId,accountId);
		return c;
	}

	@Override
	public List<OrderProblem> browsePagingOrderProblem(
			Integer number,
			Integer merId,
			Integer orderId,
			Integer accountId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<OrderProblem> l = orderProblemDao.browsePagingOrderProblem(number,merId,orderId,accountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
