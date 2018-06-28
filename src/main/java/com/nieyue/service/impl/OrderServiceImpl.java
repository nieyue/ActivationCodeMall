package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Mer;
import com.nieyue.bean.Order;
import com.nieyue.bean.OrderDetail;
import com.nieyue.bean.OrderReceiptInfo;
import com.nieyue.bean.Payment;
import com.nieyue.business.OrderBusiness;
import com.nieyue.business.PaymentBusiness;
import com.nieyue.dao.OrderDao;
import com.nieyue.exception.CommonNotRollbackException;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.pay.AlipayUtil;
import com.nieyue.service.AccountService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.MerService;
import com.nieyue.service.OrderDetailService;
import com.nieyue.service.OrderReceiptInfoService;
import com.nieyue.service.OrderService;
import com.nieyue.util.Arith;
import com.nieyue.util.NumberUtil;
import com.nieyue.util.SnowflakeIdWorker;

@Service
public class OrderServiceImpl implements OrderService{
	@Resource
	OrderDao orderDao;
	@Resource
	MerService merService;
	@Resource
	OrderDetailService orderDetailService;
	@Resource
	OrderReceiptInfoService orderReceiptInfoService;
	@Resource
	AccountService accountService;
	@Resource
	FinanceService financeService;
	@Resource
	PaymentBusiness paymentBusiness;
	@Resource
	OrderBusiness orderBusiness;
	@Resource
	AlipayUtil alipayUtil;
	@Value("${myPugin.activationCodeMallProjectDomainUrl}")
	String activationCodeMallProjectDomainUrl;
	/**
	 * 第三方支付
	 * @throws CommonNotRollbackException 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String thirdPartyPaymentOrder(
			Integer payType,
			Integer accountId,
			String orderIds
			) throws CommonNotRollbackException {
		String result=null;
		Integer type=1;//业务类型，1购买商品，2账户提现，3退款，4诚信押金
		Double totalPrice=0.0;//总金额
		//1.验证支付条件
		boolean b=false;
		String[] ids = orderIds.replace(" ","").split(",");
		for (int i = 0; i < ids.length; i++) {
			if(!NumberUtil.isNumeric(ids[i])){
				throw new CommonRollbackException("参数错误");
			}
			Order order = this.loadOrder(new Integer(ids[i]));
			OrderDetail orderDetail = order.getOrderDetailList().get(0);
			totalPrice=Arith.add(totalPrice, orderDetail.getTotalPrice());
			Mer mer = merService.loadMer(orderDetail.getMerId());
			if(mer.getStockNumber()-orderDetail.getNumber()<0){
				throw new CommonRollbackException("商品名："+mer.getName()+"库存不足");
			}
		}
		if(!b){
			return result;//不满足验证直接失败
		}
		//2.支付存储类,一个支付类对应多个订单类
		Payment payment=new Payment();
		payment.setAccountId(accountId);
		payment.setBusinessType(type);
		payment.setCreateDate(new Date());
		payment.setMoney(totalPrice);
		//支付单号
		payment.setOrderNumber(SnowflakeIdWorker.getId().toString());
		payment.setStatus(1);//默认已下单
		payment.setType(payType);
		payment.setUpdateDate(new Date());
			payment.setSubject("商品购买");
			payment.setBody("商品购买");
			payment.setBusinessNotifyUrl(orderIds);//存放订单id集合
		if(payType==1){//支付宝
			payment.setNotifyUrl(activationCodeMallProjectDomainUrl+"/order/alipayOrderNotifyUrl");
			//测试环境
			result=paymentBusiness.getPaymentNotify(payment);
			/*
			 //真实环境
			  try {
				result=alipayUtil.getPcWebPayment(payment);
			} catch (UnsupportedEncodingException e) {
				throw new PayException();//回滚
			}*/
			return result;
		}else if(payType==2){//微信
			return "暂未开通";
		}else if(payType==3){//百度钱包
			return "暂未开通";
		}else if(payType==4){//Paypay
			return "暂未开通";
		}else if(payType==5){//网银
			return "暂未开通";
		}
		return result;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addOrder(Order order) {
		order.setCreateDate(new Date());
		order.setUpdateDate(new Date());
		boolean b = orderDao.addOrder(order);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delOrder(Integer orderId) {
		boolean b = orderDao.delOrder(orderId);
		List<OrderDetail> orderDetailList = orderDetailService.browsePagingOrderDetail(null,null,orderId, null, null, 1, Integer.MAX_VALUE, "order_detail_id", "asc");
		for (int i = 0; i < orderDetailList.size(); i++) {
			b=orderDetailService.delOrderDetail(orderDetailList.get(i).getOrderDetailId());
		}
		List<OrderReceiptInfo> oril = orderReceiptInfoService.browsePagingOrderReceiptInfo(null, null, orderId, null, null, 1, 1, "update_date", "asc");
		if(oril.size()>0){
			b=orderReceiptInfoService.delOrderReceiptInfo(oril.get(0).getOrderReceiptInfoId());
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateOrder(Order order) {
		boolean b = orderDao.updateOrder(order);
		return b;
	}

	@Override
	public Order loadOrder(Integer orderId) {
		Order r = orderDao.loadOrder(orderId);
		List<OrderDetail> orderDetailList = orderDetailService.browsePagingOrderDetail(null,null,orderId, null, null, 1, Integer.MAX_VALUE, "order_detail_id", "asc");
		r.setOrderDetailList(orderDetailList);
		List<OrderReceiptInfo> oril = orderReceiptInfoService.browsePagingOrderReceiptInfo(null, null, orderId, null, null, 1, 1, "update_date", "asc");
		if(oril.size()>0){
			r.setOrderReceiptInfo(oril.get(0));
		}
		return r;
	}

	@Override
	public int countAll(
			Integer type,
			Integer payType,
			Integer merchantAccountId,
			Integer spreadAccountId,
			Integer accountId,
			Integer status,
			Integer substatus,
			Date createDate,
			Date updateDate
			) {
		int c = orderDao.countAll(
				type,payType, merchantAccountId, spreadAccountId,accountId,status,substatus,createDate,updateDate);
		return c;
	}

	@Override
	public List<Order> browsePagingOrder(
			Integer type,
			Integer payType,
			Integer merchantAccountId,
			Integer spreadAccountId,
			Integer accountId,
			Integer status,
			Integer substatus,
			Date createDate,
			Date updateDate,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Order> l = orderDao.browsePagingOrder(
				type,
				payType,
				merchantAccountId,
				spreadAccountId,
				accountId,
				status,
				substatus,
				createDate,
				updateDate,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		for (int i = 0; i < l.size(); i++) {
			Order o = l.get(i);
			List<OrderDetail> orderDetailList = orderDetailService.browsePagingOrderDetail(null,null,o.getOrderId(), null, null, 1, Integer.MAX_VALUE, "order_detail_id", "asc");
			o.setOrderDetailList(orderDetailList);
			List<OrderReceiptInfo> oril = orderReceiptInfoService.browsePagingOrderReceiptInfo(null, null, o.getOrderId(), null, null, 1, 1, "update_date", "asc");
			if(oril.size()>0){
				o.setOrderReceiptInfo(oril.get(0));
			}
		}
		return l;
	}

	
}
