package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Order;

/**
 * 订单逻辑层接口
 * @author yy
 *
 */
public interface OrderService {
	/** 新增订单 */	
	public boolean addOrder(Order order) ;	
	/** 删除订单 */	
	public boolean delOrder(Integer orderId) ;
	/** 更新订单*/	
	public boolean updateOrder(Order order);
	/** 装载订单 */	
	public Order loadOrder(Integer orderId);
	/** 余额支付订单请求 */	
	public boolean balancePaymentOrder(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer businessId,
			String nickname,
			String phone,
			String contactPhone
			);
	/** 第三方支付订单请求 */	
	public String thirdPartyPaymentOrder(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer businessId,
			String nickname,
			String phone,
			String contactPhone
			);
	/** 订单总共数目 */	
	public int countAll(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer status,
			Date createDate,
			Date updateDate
			);
	/** 分页订单信息 */
	public List<Order> browsePagingOrder(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer status,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
