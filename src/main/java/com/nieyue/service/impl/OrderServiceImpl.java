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
import com.nieyue.bean.OrderReceiptInfo;
import com.nieyue.bean.Payment;
import com.nieyue.business.OrderBusiness;
import com.nieyue.business.PaymentBusiness;
import com.nieyue.dao.OrderDao;
import com.nieyue.exception.CommonNotRollbackException;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.exception.PayException;
import com.nieyue.pay.AlipayUtil;
import com.nieyue.service.AccountService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.OrderDetailService;
import com.nieyue.service.OrderReceiptInfoService;
import com.nieyue.service.OrderService;
import com.nieyue.util.NumberUtil;
import com.nieyue.util.SnowflakeIdWorker;

import net.sf.json.JSONObject;
@Service
public class OrderServiceImpl implements OrderService{
	@Resource
	OrderDao orderDao;
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
		Integer type=1;
		//1.验证支付条件
		boolean b=false;
	//	boolean b = financeBusiness.canThirdPay(type, payType, accountId, businessId, nickname, phone, contactPhone);
		if(!b){
			return result;//不满足验证直接失败
		}
		String[] ids = orderIds.replace(" ","").split(",");
		boolean dm=false;
		for (int i = 0; i < ids.length; i++) {
			if(!NumberUtil.isNumeric(ids[i])){
				throw new CommonRollbackException("参数错误");
			}
		}
		for (int i = 0; i < ids.length; i++) {
			Order order = this.loadOrder(new Integer(ids[i]));
			order.setStatus(7);//已删除
			order.setSubstatus(1);
			dm =this.updateOrder(order);
		}
		OrderDetail orderDetail=null;
		//3.支付存储类
		Payment payment=new Payment();
		payment.setAccountId(accountId);
		payment.setBusinessType(type);
		payment.setCreateDate(new Date());
		payment.setMoney(orderDetail.getTotalPrice());
		payment.setOrderNumber(SnowflakeIdWorker.getId().toString());
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
			payment.setBusinessNotifyUrl(jsono.toString());
		}else if(type==3){
			payment.setSubject("付费课程");
			payment.setBody("付费课程");
		}
		if(payType==1){//支付宝
			payment.setNotifyUrl(activationCodeMallProjectDomainUrl+"/payment/alipayNotifyUrl");
			try {
				result=alipayUtil.getAppPayment(payment);
			} catch (UnsupportedEncodingException e) {
				throw new PayException();//回滚
			}
			return result;
		}else if(payType==2){//微信
			//payment.setNotifyUrl(activationCodeMallProjectDomainUrl+"/payment/wechatNotifyUrl");
			return "暂未开通";
		}else if(payType==4){//ios内购
			//payment.setNotifyUrl(activationCodeMallProjectDomainUrl+"/payment/iosNotifyUrl");
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
			Integer accountId,
			Integer status,
			Integer substatus,
			Date createDate,
			Date updateDate
			) {
		int c = orderDao.countAll(
				type,payType,accountId,status,substatus,createDate,updateDate);
		return c;
	}

	@Override
	public List<Order> browsePagingOrder(
			Integer type,
			Integer payType,
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
