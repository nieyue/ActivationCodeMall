package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.nieyue.bean.Coupon;
import com.nieyue.bean.CouponTerm;
import com.nieyue.business.OrderBusiness;
import com.nieyue.dao.CouponDao;
import com.nieyue.service.CouponService;
import com.nieyue.service.CouponTermService;
@Service
public class CouponServiceImpl implements CouponService{
	@Resource
	CouponDao couponDao;
	@Resource
	CouponTermService couponTermService;
	@Resource
	OrderBusiness orderBusiness;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addCoupon(Coupon coupon) {
		coupon.setCreateDate(new Date());
		coupon.setUpdateDate(new Date());
	CouponTerm	ct=couponTermService.loadCouponTerm(coupon.getCouponTermId());
	coupon.setName(ct.getName());
	coupon.setImgAddress(ct.getImgAddress());
	coupon.setDiscount(ct.getDiscount());
	coupon.setContent(ct.getContent());
	coupon.setStatus(1);//默认可用
	Integer merCateId=ct.getMerCateId();
	if(!ObjectUtils.isEmpty(merCateId)){
		coupon.setMerCateId(ct.getMerCateId());		
	}
	coupon.setCode(orderBusiness.generateShortUuid());
	coupon.setCouponTermId(ct.getCouponTermId());
	boolean b = couponDao.addCoupon(coupon);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delCoupon(Integer couponId) {
		boolean b = couponDao.delCoupon(couponId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateCoupon(Coupon coupon) {
		coupon.setUpdateDate(new Date());
		boolean b = couponDao.updateCoupon(coupon);
		return b;
	}

	@Override
	public Coupon loadCoupon(Integer couponId) {
		Coupon r = couponDao.loadCoupon(couponId);
		return r;
	}

	@Override
	public int countAll(
			String code,
			Integer merCateId,
			Integer couponTermId,
			Integer accountId,
			Integer orderId,
			Date startDate,
			Date endDate,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = couponDao.countAll(
				 code,
				 merCateId,
				 couponTermId,
				 accountId,
				 orderId,
				 startDate,
				 endDate,
				 createDate,
				 updateDate,
				 status
				);
		return c;
	}

	@Override
	public List<Coupon> browsePagingCoupon(
			String code,
			Integer merCateId,
			Integer couponTermId,
			Integer accountId,
			Integer orderId,
			Date startDate,
			Date endDate,
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
		List<Coupon> l = couponDao.browsePagingCoupon(
				code,
				 merCateId,
				 couponTermId,
				 accountId,
				 orderId,
				 startDate,
				 endDate,
				 createDate,
				 updateDate,
				 status,
				pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
