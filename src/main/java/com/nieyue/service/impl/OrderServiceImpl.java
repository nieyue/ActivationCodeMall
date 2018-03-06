package com.nieyue.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Order;
import com.nieyue.bean.OrderDetail;
import com.nieyue.bean.Payment;
import com.nieyue.business.FinanceBusiness;
import com.nieyue.business.OrderBusiness;
import com.nieyue.business.PaymentBusiness;
import com.nieyue.dao.OrderDao;
import com.nieyue.exception.PayException;
import com.nieyue.pay.AlipayUtil;
import com.nieyue.service.AccountService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.OrderDetailService;
import com.nieyue.service.OrderService;
import com.nieyue.util.DateUtil;

import net.sf.json.JSONObject;
@Service
public class OrderServiceImpl implements OrderService{
	@Resource
	OrderDao orderDao;
	@Resource
	OrderDetailService orderDetailService;
	@Resource
	AccountService accountService;
	@Resource
	FinanceService financeService;
	@Resource
	FinanceBusiness financeBusiness;
	@Resource
	PaymentBusiness paymentBusiness;
	@Resource
	OrderBusiness orderBusiness;
	@Resource
	AlipayUtil alipayUtil;
	@Value("${myPugin.lordSayProjectDomainUrl}")
	String lordSayProjectDomainUrl;
	/**
	 * 第三方支付
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String thirdPartyPaymentOrder(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer businessId,
			String nickname,
			String phone,
			String contactPhone
			) {
		String result=null;
		//1.验证支付条件
		boolean b = financeBusiness.canThirdPay(type, payType, accountId, businessId, nickname, phone, contactPhone);
		if(!b){
			return result;//不满足验证直接失败
		}
		//2.生成订单号
		String orderNumber=orderBusiness.getOrderNumber(accountId);
		OrderDetail orderDetail = paymentBusiness.getPaymentType(type, payType, accountId, businessId);
		//3.支付存储类
		Payment payment=new Payment();
		payment.setAccountId(accountId);
		payment.setBusinessId(businessId);
		payment.setBusinessType(type);
		payment.setCreateDate(new Date());
		payment.setMoney(orderDetail.getTotalPrice());
		payment.setOrderNumber(orderNumber);
		payment.setStatus(1);//默认已下单
		payment.setType(payType);
		payment.setUpdateDate(new Date());
		if(type==1){
			payment.setSubject("VIP购买");
			payment.setBody("VIP购买");
		}else if(type==2){
			payment.setSubject("团购卡团购");
			payment.setBody("团购卡团购");
			JSONObject jsono=new JSONObject();
			jsono.put("nickname", nickname);
			jsono.put("phone", phone);
			jsono.put("contactPhone", contactPhone);
			payment.setBusinessNotifyUrl(jsono.toString());
		}else if(type==3){
			payment.setSubject("付费课程");
			payment.setBody("付费课程");
		}
		if(payType==1){//支付宝
			payment.setNotifyUrl(lordSayProjectDomainUrl+"/payment/alipayNotifyUrl");
			try {
				result=alipayUtil.getAppPayment(payment);
			} catch (UnsupportedEncodingException e) {
				throw new PayException();//回滚
			}
			return result;
		}else if(payType==2){//微信
			//payment.setNotifyUrl(lordSayProjectDomainUrl+"/payment/wechatNotifyUrl");
			return "暂未开通";
		}else if(payType==4){//ios内购
			//payment.setNotifyUrl(lordSayProjectDomainUrl+"/payment/iosNotifyUrl");
			return "暂未开通";
		}
		return result;
	}
	/**
	 * 余额支付
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean balancePaymentOrder(Integer type,
			Integer payType,
			Integer accountId,
			Integer businessId,
			String nickname,
			String phone,
			String contactPhone) {
		//1.生成订单号
		String orderNumber=orderBusiness.getOrderNumber(accountId);
		//2.财务执行
		int r = financeBusiness.financeExcute(type, payType, accountId,businessId,orderNumber,nickname,phone,contactPhone);
		if(r==-1){
			throw new PayException();
		}else if(r==0){//部分成功
			return false;
		}	
		return true;
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
		List<OrderDetail> orderDetailList = orderDetailService.browsePagingOrderDetail(orderId, null, null, 1, Integer.MAX_VALUE, "order_detail_id", "asc");
		for (int i = 0; i < orderDetailList.size(); i++) {
			b=orderDetailService.delOrderDetail(orderDetailList.get(i).getOrderDetailId());
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
		List<OrderDetail> orderDetailList = orderDetailService.browsePagingOrderDetail(orderId, null, null, 1, Integer.MAX_VALUE, "order_detail_id", "asc");
		r.setOrderDetailList(orderDetailList);
		return r;
	}

	@Override
	public int countAll(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer status,
			Date createDate,
			Date updateDate
			) {
		int c = orderDao.countAll(
				type,payType,accountId,status,createDate,updateDate);
		return c;
	}

	@Override
	public List<Order> browsePagingOrder(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer status,
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
				accountId,
				status,
				createDate,
				updateDate,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		for (int i = 0; i < l.size(); i++) {
			Order o = l.get(i);
			List<OrderDetail> orderDetailList = orderDetailService.browsePagingOrderDetail(o.getOrderId(), null, null, 1, Integer.MAX_VALUE, "order_detail_id", "asc");
			o.setOrderDetailList(orderDetailList);
		}
		return l;
	}

	
}
