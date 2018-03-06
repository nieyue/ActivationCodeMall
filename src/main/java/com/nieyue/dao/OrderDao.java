package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Order;

/**
 * 订单数据库接口
 * @author yy
 *
 */
@Mapper
public interface OrderDao {
	/** 新增订单*/	
	public boolean addOrder(Order order) ;	
	/** 删除订单 */	
	public boolean delOrder(Integer orderId) ;
	/** 更新订单*/	
	public boolean updateOrder(Order order);
	/** 装载订单 */	
	public Order loadOrder(Integer orderId);	
	/** 订单总共数目 */	
	public int countAll(
			@Param("type")Integer type,
			@Param("payType")Integer payType,
			@Param("accountId")Integer accountId,
			@Param("status")Integer status,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页订单信息 */
	public List<Order> browsePagingOrder(
			@Param("type")Integer type,
			@Param("payType")Integer payType,
			@Param("accountId")Integer accountId,
			@Param("status")Integer status,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
