package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.CouponTerm;
import com.nieyue.bean.MerCate;
import com.nieyue.dao.CouponTermDao;
import com.nieyue.service.CouponTermService;
import com.nieyue.service.MerCateService;
@Service
public class CouponTermServiceImpl implements CouponTermService{
	@Resource
	CouponTermDao couponTermDao;
	@Resource
	MerCateService merCateService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addCouponTerm(CouponTerm couponTerm) {
		couponTerm.setUpdateDate(new Date());
		boolean b = couponTermDao.addCouponTerm(couponTerm);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delCouponTerm(Integer couponTermId) {
		boolean b = couponTermDao.delCouponTerm(couponTermId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateCouponTerm(CouponTerm couponTerm) {
		couponTerm.setUpdateDate(new Date());
		boolean b = couponTermDao.updateCouponTerm(couponTerm);
		return b;
	}

	@Override
	public CouponTerm loadCouponTerm(Integer couponTermId) {
		CouponTerm r = couponTermDao.loadCouponTerm(couponTermId);
		MerCate merCate=merCateService.loadMerCate(r.getMerCateId());
		r.setMerCate(merCate);
		return r;
	}

	@Override
	public int countAll() {
		int c = couponTermDao.countAll();
		return c;
	}

	@Override
	public List<CouponTerm> browsePagingCouponTerm(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<CouponTerm> l = couponTermDao.browsePagingCouponTerm(pageNum-1, pageSize, orderName, orderWay);
		l.forEach(ct->{
			MerCate merCate=merCateService.loadMerCate(ct.getMerCateId());
			ct.setMerCate(merCate);
		});
		return l;
	}

	
}
