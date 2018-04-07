package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.OrderReceiptInfo;

/**
 * 订单收货信息数据库接口
 * @author yy
 *
 */
@Mapper
public interface OrderReceiptInfoDao {
	/** 新增订单收货信息*/	
	public boolean addOrderReceiptInfo(OrderReceiptInfo orderReceiptInfo) ;	
	/** 删除订单收货信息 */	
	public boolean delOrderReceiptInfo(Integer orderReceiptInfoId) ;
	/** 更新订单收货信息*/	
	public boolean updateOrderReceiptInfo(OrderReceiptInfo orderReceiptInfo);
	/** 装载订单收货信息 */	
	public OrderReceiptInfo loadOrderReceiptInfo(Integer orderReceiptInfoId);	
	/** 订单收货信息总共数目 */	
	public int countAll(
			@Param("accountId")Integer accountId,
			@Param("isDefault")Integer isDefault,
			@Param("orderId")Integer orderId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页订单收货信息信息 */
	public List<OrderReceiptInfo> browsePagingOrderReceiptInfo(
			@Param("accountId")Integer accountId,
			@Param("isDefault")Integer isDefault,
			@Param("orderId")Integer orderId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
