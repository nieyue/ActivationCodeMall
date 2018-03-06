package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.PlatformDay;
import com.nieyue.dao.PlatformDayDao;
import com.nieyue.service.PlatformDayService;
@Service
public class PlatformDayServiceImpl implements PlatformDayService{
	@Resource
	PlatformDayDao platformDayDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addPlatformDay(PlatformDay platformDay) {
		platformDay.setCreateDate(new Date());
		platformDay.setUpdateDate(new Date());
		boolean b = platformDayDao.addPlatformDay(platformDay);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delPlatformDay(Integer platformDayId) {
		boolean b = platformDayDao.delPlatformDay(platformDayId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updatePlatformDay(PlatformDay platformDay) {
		platformDay.setUpdateDate(new Date());
		boolean b = platformDayDao.updatePlatformDay(platformDay);
		return b;
	}

	@Override
	public PlatformDay loadPlatformDay(Integer platformDayId) {
		PlatformDay r = platformDayDao.loadPlatformDay(platformDayId);
		return r;
	}

	@Override
	public int countAll(Date createDate) {
		int c = platformDayDao.countAll(createDate);
		return c;
	}

	@Override
	public List<PlatformDay> browsePagingPlatformDay(Date createDate,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<PlatformDay> l = platformDayDao.browsePagingPlatformDay(createDate,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
