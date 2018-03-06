package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Payment;
import com.nieyue.dao.PaymentDao;
import com.nieyue.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService{
	@Resource
	PaymentDao paymentDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addPayment(Payment payment) {
		payment.setCreateDate(new Date());
		payment.setUpdateDate(new Date());
		if(payment.getType()==null|payment.getType().equals("")){
			return false;
		}
		if(payment.getMoney()==null||payment.getMoney().equals("")||payment.getMoney()<=0.0){
			return false;
		}
		if(payment.getStatus()==null||payment.getStatus().equals("")){
			payment.setStatus(1);//默认已下单
		}
		boolean b = paymentDao.addPayment(payment);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delPayment(Integer paymentId) {
		boolean b = paymentDao.delPayment(paymentId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updatePayment(Payment payment) {
		payment.setUpdateDate(new Date());
		boolean b = paymentDao.updatePayment(payment);
		return b;
	}

	@Override
	public Payment loadPayment(Integer paymentId) {
		Payment payment = paymentDao.loadPayment(paymentId);
		return payment;
	}

	@Override
	public int countAll(
			String orderNumber,
			Integer type,
			Integer businessType,
			Integer businessId,
			Integer accountId,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = paymentDao.countAll(
				 orderNumber,
				 type,
				 businessType,
				 businessId,
				 accountId,
				 createDate,
				 updateDate,
				 status);
		return c;
	}

	@Override
	public List<Payment> browsePagingPayment(
			String orderNumber,
			Integer type,
			Integer businessType,
			Integer businessId,
			Integer accountId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Payment> l = paymentDao.browsePagingPayment(
				 orderNumber,
				 type,
				 businessType,
				 businessId,
				 accountId,
				 createDate,
				 updateDate,
				 status,
				pageNum-1, pageSize, orderName, orderWay);
		
		return l;
	}
}
