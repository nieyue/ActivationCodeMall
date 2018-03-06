package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.OrderDetail;

/**
 * 订单详情逻辑层接口
 * @author yy
 *
 */
public interface OrderDetailService {
	/** 新增订单详情 */	
	public boolean addOrderDetail(OrderDetail orderDetail) ;	
	/** 删除订单详情 */	
	public boolean delOrderDetail(Integer orderDetailId) ;
	/** 更新订单详情*/	
	public boolean updateOrderDetail(OrderDetail orderDetail);
	/** 装载订单详情 */	
	public OrderDetail loadOrderDetail(Integer orderDetailId);	
	/** 订单详情总共数目 */	
	public int countAll(
			Integer orderId,
			Date createDate,
			Date updateDate
			);
	/** 分页订单详情信息 */
	public List<OrderDetail> browsePagingOrderDetail(
			Integer orderId,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
