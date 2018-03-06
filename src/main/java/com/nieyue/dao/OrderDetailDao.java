package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.OrderDetail;

/**
 * 订单详情数据库接口
 * @author yy
 *
 */
@Mapper
public interface OrderDetailDao {
	/** 新增订单详情*/	
	public boolean addOrderDetail(OrderDetail orderDetail) ;	
	/** 删除订单详情 */	
	public boolean delOrderDetail(Integer orderDetailId) ;
	/** 更新订单详情*/	
	public boolean updateOrderDetail(OrderDetail orderDetail);
	/** 装载订单详情 */	
	public OrderDetail loadOrderDetail(Integer orderDetailId);	
	/** 订单详情总共数目 */	
	public int countAll(
			@Param("orderId")Integer orderId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页订单详情信息 */
	public List<OrderDetail> browsePagingOrderDetail(
			@Param("orderId")Integer orderId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
