package com.nieyue.business;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.nieyue.bean.Order;
import com.nieyue.bean.OrderDetail;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.AccountLevelService;
import com.nieyue.service.OrderDetailService;
import com.nieyue.service.OrderService;

/**
 * 支付业务
 */
@Configuration
public class PaymentBusiness {
	@Resource
	AccountLevelService accountLevelService;
	@Resource
	OrderService orderService;
	@Resource
	OrderDetailService orderDetailService;

	/**
	 * 支付回调，订单进入冻结期
	 * payType 支付方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
	 * accountId 下单人id外键
	 * ids 订单id列表如:[101,3,44]
	 */
	public void getPaymentNotify(
			Integer payType,
			Integer accountId,
			String[] ids,
			OrderDetail orderDetail){
		boolean b=false;
		for (int i = 0; i < ids.length; i++) {
			Order order = orderService.loadOrder(new Integer(ids[i]));
			if(order==null){
				throw new CommonRollbackException("订单不存在");
			}
			order.setStatus(3);//已支付
			order.setSubstatus(1);//冻结单
			order.setUpdateDate(new Date());
			order.setPaymentDate(new Date());
			b =orderService.updateOrder(order);
		}
	}
}
