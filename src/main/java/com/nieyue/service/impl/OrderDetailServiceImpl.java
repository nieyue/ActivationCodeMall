package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.nieyue.bean.Coupon;
import com.nieyue.bean.OrderDetail;
import com.nieyue.dao.OrderDetailDao;
import com.nieyue.service.CouponService;
import com.nieyue.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Resource
	OrderDetailDao orderDetailDao;
	@Resource
	CouponService couponService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
		orderDetail.setCreateDate(new Date());
		orderDetail.setUpdateDate(new Date());
		boolean b = orderDetailDao.addOrderDetail(orderDetail);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delOrderDetail(Integer orderDetailId) {
		boolean b = orderDetailDao.delOrderDetail(orderDetailId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateOrderDetail(OrderDetail orderDetail) {
		boolean b = orderDetailDao.updateOrderDetail(orderDetail);
		return b;
	}

	@Override
	public OrderDetail loadOrderDetail(Integer orderDetailId) {
		OrderDetail r = orderDetailDao.loadOrderDetail(orderDetailId);
		if(r.getCouponId()!=null){
			Coupon c = couponService.loadCoupon(r.getCouponId());
			if(!ObjectUtils.isEmpty(c)){
				r.setCoupon(c);
			}
		}
		return r;
	}

	@Override
	public int countAll(
			Integer couponId,
			Integer merId,
			Integer orderId,
			Date createDate,
			Date updateDate
			) {
		int c = orderDetailDao.countAll(
				couponId,merId,
				orderId,createDate,updateDate);
		return c;
	}

	@Override
	public List<OrderDetail> browsePagingOrderDetail(
			Integer couponId,
			Integer merId,
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
		List<OrderDetail> l = orderDetailDao.browsePagingOrderDetail(
				couponId,
				merId,
				orderId,
				createDate,
				updateDate,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		l.forEach(r->{
			if(r.getCouponId()!=null){
				Coupon c = couponService.loadCoupon(r.getCouponId());
				if(!ObjectUtils.isEmpty(c)){
					r.setCoupon(c);
				}
			}
		});
		return l;
	}

	
}
