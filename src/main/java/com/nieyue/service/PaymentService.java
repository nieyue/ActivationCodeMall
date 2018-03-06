package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Payment;

/**
 * 支付逻辑层接口
 * @author yy
 *
 */
public interface PaymentService {
	/** 新增支付 */	
	public boolean addPayment(Payment payment) ;	
	/** 删除支付 */	
	public boolean delPayment(Integer paymentId) ;
	/** 更新支付*/	
	public boolean updatePayment(Payment payment);
	/** 装载支付 */	
	public Payment loadPayment(Integer paymentId);	
	/** 支付总共数目 */	
	public int countAll(
			String orderNumber,
			Integer type,
			Integer businessType,
			Integer businessId,
			Integer accountId,
			Date createDate,
			Date updateDate,
			Integer status);
	/** 分页支付信息 */
	public List<Payment> browsePagingPayment(
			String orderNumber,
			Integer type,
			Integer businessType,
			Integer businessId,
			Integer accountId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
