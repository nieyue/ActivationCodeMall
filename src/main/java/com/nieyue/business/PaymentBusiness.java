package com.nieyue.business;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.nieyue.bean.Order;
import com.nieyue.bean.OrderDetail;
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
	 * 生产订单
	 */
	public Order getOrder(
			Integer type,
			Integer payType,
			Integer accountId,
			String orderNumber,
			OrderDetail orderDetail){
		boolean b=false;
		Order order=new Order();
		order.setCreateDate(new Date());
		order.setUpdateDate(new Date());
		order.setAccountId(accountId);
		if(type==2){//团购
			order.setStatus(1);//待处理									
		}else{
			order.setStatus(2);//已完成					
		}
		order.setType(type);
		order.setPayType(payType);
		order.setOrderNumber(orderNumber);
		 b = orderService.addOrder(order);
		if(b){
			orderDetail.setOrderId(order.getOrderId());
			b=orderDetailService.addOrderDetail(orderDetail);
			if(b){
				List<OrderDetail> orderDetailList=new ArrayList<>();
				orderDetailList.add(orderDetail);
				order.setOrderDetailList(orderDetailList);
				return order;
			}
		}
		return order;
	}
}
