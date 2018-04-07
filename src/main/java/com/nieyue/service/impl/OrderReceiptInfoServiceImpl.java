package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.OrderReceiptInfo;
import com.nieyue.dao.OrderReceiptInfoDao;
import com.nieyue.service.OrderReceiptInfoService;
@Service
public class OrderReceiptInfoServiceImpl implements OrderReceiptInfoService{
	@Resource
	OrderReceiptInfoDao orderReceiptInfoDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addOrderReceiptInfo(OrderReceiptInfo orderReceiptInfo) {
		orderReceiptInfo.setCreateDate(new Date());
		orderReceiptInfo.setUpdateDate(new Date());
		boolean b = orderReceiptInfoDao.addOrderReceiptInfo(orderReceiptInfo);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delOrderReceiptInfo(Integer orderReceiptInfoId) {
		boolean b = orderReceiptInfoDao.delOrderReceiptInfo(orderReceiptInfoId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateOrderReceiptInfo(OrderReceiptInfo orderReceiptInfo) {
		orderReceiptInfo.setUpdateDate(new Date());
		boolean b = orderReceiptInfoDao.updateOrderReceiptInfo(orderReceiptInfo);
		return b;
	}

	@Override
	public OrderReceiptInfo loadOrderReceiptInfo(Integer orderReceiptInfoId) {
		OrderReceiptInfo r = orderReceiptInfoDao.loadOrderReceiptInfo(orderReceiptInfoId);
		return r;
	}

	@Override
	public int countAll(
			Integer accountId,
			Integer isDefault,
			Integer orderId,
			Date createDate,
			Date updateDate
			) {
		int c = orderReceiptInfoDao.countAll(
				accountId,
				isDefault,
				orderId,
				createDate,
				updateDate);
		return c;
	}

	@Override
	public List<OrderReceiptInfo> browsePagingOrderReceiptInfo(
			Integer accountId,
			Integer isDefault,
			Integer orderId,
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
		List<OrderReceiptInfo> l = orderReceiptInfoDao.browsePagingOrderReceiptInfo(
				accountId,
				isDefault,
				orderId,
				createDate,
				updateDate,
				pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
