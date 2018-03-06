package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.IntegralDetail;
import com.nieyue.dao.IntegralDetailDao;
import com.nieyue.service.IntegralDetailService;
@Service
public class IntegralDetailServiceImpl implements IntegralDetailService{
	@Resource
	IntegralDetailDao integralDetailDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addIntegralDetail(IntegralDetail integralDetail) {
		integralDetail.setCreateDate(new Date());
		integralDetail.setUpdateDate(new Date());
		boolean b = integralDetailDao.addIntegralDetail(integralDetail);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delIntegralDetail(Integer integralDetailId) {
		boolean b = integralDetailDao.delIntegralDetail(integralDetailId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateIntegralDetail(IntegralDetail integralDetail) {
		integralDetail.setUpdateDate(new Date());
		boolean b = integralDetailDao.updateIntegralDetail(integralDetail);
		return b;
	}

	@Override
	public IntegralDetail loadIntegralDetail(Integer integralDetailId) {
		IntegralDetail r = integralDetailDao.loadIntegralDetail(integralDetailId);
		return r;
	}

	@Override
	public int countAll(
			Integer type,
			Integer accountId,
			Date createDate,
			Date updateDate) {
		int c = integralDetailDao.countAll(type,accountId,createDate,updateDate);
		return c;
	}

	@Override
	public List<IntegralDetail> browsePagingIntegralDetail(
			Integer type,
			Integer accountId,
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
		List<IntegralDetail> l = integralDetailDao.browsePagingIntegralDetail(type,accountId,createDate,updateDate,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
