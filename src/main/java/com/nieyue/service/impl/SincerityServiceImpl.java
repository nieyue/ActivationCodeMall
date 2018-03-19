package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Sincerity;
import com.nieyue.dao.SincerityDao;
import com.nieyue.service.SincerityService;
@Service
public class SincerityServiceImpl implements SincerityService{
	@Resource
	SincerityDao sincerityDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addSincerity(Sincerity sincerity) {
		sincerity.setCreateDate(new Date());
		sincerity.setUpdateDate(new Date());
		boolean b = sincerityDao.addSincerity(sincerity);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delSincerity(Integer sincerityId) {
		boolean b = sincerityDao.delSincerity(sincerityId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateSincerity(Sincerity sincerity) {
		sincerity.setUpdateDate(new Date());
		boolean b = sincerityDao.updateSincerity(sincerity);
		return b;
	}

	@Override
	public Sincerity loadSincerity(Integer sincerityId) {
		Sincerity r = sincerityDao.loadSincerity(sincerityId);
		return r;
	}

	@Override
	public int countAll(
			Integer accountId,
			Date createDate,
			Date updateDate
			) {
		int c = sincerityDao.countAll(accountId,createDate,updateDate);
		return c;
	}

	@Override
	public List<Sincerity> browsePagingSincerity(
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
		List<Sincerity> l = sincerityDao.browsePagingSincerity(accountId,createDate,updateDate,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
