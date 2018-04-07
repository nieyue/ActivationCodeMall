package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.OrderReceiptInfo;

/**
 * 订单收货信息逻辑层接口
 * @author yy
 *
 */
public interface OrderReceiptInfoService {
	/** 新增订单收货信息 */	
	public boolean addOrderReceiptInfo(OrderReceiptInfo orderReceiptInfo) ;	
	/** 删除订单收货信息 */	
	public boolean delOrderReceiptInfo(Integer orderReceiptInfoId) ;
	/** 更新订单收货信息*/	
	public boolean updateOrderReceiptInfo(OrderReceiptInfo orderReceiptInfo);
	/** 装载订单收货信息 */	
	public OrderReceiptInfo loadOrderReceiptInfo(Integer orderReceiptInfoId);	
	/** 订单收货信息总共数目 */	
	public int countAll(
			Integer accountId,
			Integer isDefault,
			Integer orderId,
			Date createDate,
			Date updateDate);
	/** 分页订单收货信息信息 */
	public List<OrderReceiptInfo> browsePagingOrderReceiptInfo(
			Integer accountId,
			Integer isDefault,
			Integer orderId,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
