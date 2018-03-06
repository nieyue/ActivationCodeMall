package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Integral;
import com.nieyue.dao.IntegralDao;
import com.nieyue.service.IntegralService;
@Service
public class IntegralServiceImpl implements IntegralService{
	@Resource
	IntegralDao integralDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addIntegral(Integral integral) {
		integral.setCreateDate(new Date());
		integral.setUpdateDate(new Date());
		boolean b = integralDao.addIntegral(integral);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delIntegral(Integer integralId) {
		boolean b = integralDao.delIntegral(integralId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateIntegral(Integral integral) {
		integral.setUpdateDate(new Date());
		boolean b = integralDao.updateIntegral(integral);
		return b;
	}

	@Override
	public Integral loadIntegral(Integer integralId) {
		Integral r = integralDao.loadIntegral(integralId);
		return r;
	}

	@Override
	public int countAll(
			Integer accountId,
			Date createDate,
			Date updateDate) {
		int c = integralDao.countAll(accountId,createDate,updateDate);
		return c;
	}

	@Override
	public List<Integral> browsePagingIntegral(
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
		List<Integral> l = integralDao.browsePagingIntegral(accountId,createDate,updateDate,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
