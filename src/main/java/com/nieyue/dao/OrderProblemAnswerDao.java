package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.OrderProblemAnswer;

/**
 * 商品订单问题反馈数据库接口
 * @author yy
 *
 */
@Mapper
public interface OrderProblemAnswerDao {
	/** 新增商品订单问题反馈*/	
	public boolean addOrderProblemAnswer(OrderProblemAnswer orderProblemAnswer) ;	
	/** 删除商品订单问题反馈 */	
	public boolean delOrderProblemAnswer(Integer orderProblemAnswerId) ;
	/** 更新商品订单问题反馈*/	
	public boolean updateOrderProblemAnswer(OrderProblemAnswer orderProblemAnswer);
	/** 装载商品订单问题反馈 */	
	public OrderProblemAnswer loadOrderProblemAnswer(Integer orderProblemAnswerId);	
	/** 商品订单问题反馈总共数目 */	
	public int countAll();	
	/** 分页商品订单问题反馈信息 */
	public List<OrderProblemAnswer> browsePagingOrderProblemAnswer(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
