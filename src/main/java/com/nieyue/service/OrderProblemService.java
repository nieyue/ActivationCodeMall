package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.OrderProblem;

/**
 * 商品订单问题逻辑层接口
 * @author yy
 *
 */
public interface OrderProblemService {
	/** 新增商品订单问题 */	
	public boolean addOrderProblem(OrderProblem orderProblem) ;	
	/** 删除商品订单问题 */	
	public boolean delOrderProblem(Integer orderProblemId) ;
	/** 更新商品订单问题*/	
	public boolean updateOrderProblem(OrderProblem orderProblem);
	/** 装载商品订单问题 */	
	public OrderProblem loadOrderProblem(Integer orderProblemId);	
	/** 商品订单问题总共数目 */	
	public int countAll(
			Integer number,
			Integer merId,
			Integer orderId,
			Integer accountId
			);
	/** 分页商品订单问题信息 */
	public List<OrderProblem> browsePagingOrderProblem(
			Integer number,
			Integer merId,
			Integer orderId,
			Integer accountId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
