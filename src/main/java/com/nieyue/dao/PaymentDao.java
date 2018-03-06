package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Payment;

/**
 * 支付数据库接口
 * @author yy
 *
 */
@Mapper
public interface PaymentDao {
	/** 新增支付*/	
	public boolean addPayment(Payment payment) ;	
	/** 删除支付 */	
	public boolean delPayment(Integer paymentId) ;
	/** 更新支付*/	
	public boolean updatePayment(Payment payment);
	/** 装载支付 */	
	public Payment loadPayment(Integer paymentId);	
	/** 支付总共数目 */	
	public int countAll(
			@Param("orderNumber")String orderNumber,
			@Param("type")Integer type,
			@Param("businessType")Integer businessType,
			@Param("businessId")Integer businessId,
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页支付信息 */
	public List<Payment> browsePagingPayment(
			@Param("orderNumber")String orderNumber,
			@Param("type")Integer type,
			@Param("businessType")Integer businessType,
			@Param("businessId")Integer businessId,
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
