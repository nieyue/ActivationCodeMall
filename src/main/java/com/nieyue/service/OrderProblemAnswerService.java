package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.OrderProblemAnswer;

/**
 * 商品订单问题反馈逻辑层接口
 * @author yy
 *
 */
public interface OrderProblemAnswerService {
	/** 新增商品订单问题反馈 */	
	public boolean addOrderProblemAnswer(OrderProblemAnswer orderProblemAnswer) ;	
	/** 删除商品订单问题反馈 */	
	public boolean delOrderProblemAnswer(Integer orderProblemAnswerId) ;
	/** 更新商品订单问题反馈*/	
	public boolean updateOrderProblemAnswer(OrderProblemAnswer orderProblemAnswer);
	/** 装载商品订单问题反馈 */	
	public OrderProblemAnswer loadOrderProblemAnswer(Integer orderProblemAnswerId);	
	/** 商品订单问题反馈总共数目 */	
	public int countAll(
			Integer orderProblemId,
			Integer accountId
			);
	/** 分页商品订单问题反馈信息 */
	public List<OrderProblemAnswer> browsePagingOrderProblemAnswer(
			Integer orderProblemId,
			Integer accountId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
