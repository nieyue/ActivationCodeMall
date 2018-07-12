package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.Mer;
import com.nieyue.bean.Order;
import com.nieyue.bean.OrderProblem;
import com.nieyue.bean.OrderProblemAnswer;
import com.nieyue.dao.OrderProblemDao;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.AccountService;
import com.nieyue.service.MerService;
import com.nieyue.service.OrderProblemAnswerService;
import com.nieyue.service.OrderProblemService;
import com.nieyue.service.OrderService;
@Service
public class OrderProblemServiceImpl implements OrderProblemService{
	@Resource
	OrderProblemDao orderProblemDao;
	@Resource
	OrderService orderService;
	@Resource
	AccountService accountService;
	@Resource
	MerService merService;
	@Resource
	OrderProblemAnswerService orderProblemAnswerService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addOrderProblem(OrderProblem orderProblem) {
		Order order = orderService.loadOrder(orderProblem.getOrderId());
		if(order==null){
			throw new CommonRollbackException("订单不存在");
		}
		orderProblem.setNumber(1);//默认为1
		List<OrderProblem> opl = this.browsePagingOrderProblem(null, orderProblem.getMerId(), orderProblem.getOrderId(), orderProblem.getAccountId(), 1, 1, "create_date", "desc");
		if(opl.size()==1){
			orderProblem.setNumber(opl.get(0).getNumber()+1);
		}
		orderProblem.setCreateDate(new Date());
		boolean b = orderProblemDao.addOrderProblem(orderProblem);
		if(b){
			order.setStatus(5);//订单状态为问题单
			order.setSubstatus(1);//1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决
			b=orderService.updateOrder(order);
		}
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
		List<OrderProblemAnswer> orderProblemAnswerList = orderProblemAnswerService.browsePagingOrderProblemAnswer(r.getOrderProblemId(), null, 1, Integer.MAX_VALUE, "order_problem_answer_id", "asc");
		r.setOrderProblemAnswerList(orderProblemAnswerList);
		Account account = accountService.loadAccount(r.getAccountId());
		r.setAccount(account);
		Mer mer = merService.loadMer(r.getMerId());
		r.setMer(mer);
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
		l.forEach((op)->{
			List<OrderProblemAnswer> orderProblemAnswerList = orderProblemAnswerService.browsePagingOrderProblemAnswer(op.getOrderProblemId(), null, 1, Integer.MAX_VALUE, "order_problem_answer_id", "asc");
			op.setOrderProblemAnswerList(orderProblemAnswerList);
			Account account = accountService.loadAccount(op.getAccountId());
			op.setAccount(account);
			Mer mer = merService.loadMer(op.getMerId());
			op.setMer(mer);
		});
		return l;
	}

	
}
