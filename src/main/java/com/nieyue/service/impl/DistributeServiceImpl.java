package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Distribute;
import com.nieyue.dao.DistributeDao;
import com.nieyue.service.DistributeService;
@Service
public class DistributeServiceImpl implements DistributeService{
	@Resource
	DistributeDao distributeDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addDistribute(Distribute distribute) {
		distribute.setCreateDate(new Date());
		distribute.setUpdateDate(new Date());
		boolean b = distributeDao.addDistribute(distribute);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delDistribute(Integer distributeId) {
		boolean b = distributeDao.delDistribute(distributeId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateDistribute(Distribute distribute) {
		distribute.setUpdateDate(new Date());
		boolean b = distributeDao.updateDistribute(distribute);
		return b;
	}

	@Override
	public Distribute loadDistribute(Integer distributeId) {
		Distribute r = distributeDao.loadDistribute(distributeId);
		return r;
	}

	@Override
	public int countAll(
			Integer accountId,
			Integer buyAccountId,
			Date distributeDate,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = distributeDao.countAll(
				accountId,buyAccountId,distributeDate,createDate,updateDate,status);
		return c;
	}

	@Override
	public List<Distribute> browsePagingDistribute(
			Integer accountId,
			Integer buyAccountId,
			Date distributeDate,
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
		List<Distribute> l = distributeDao.browsePagingDistribute(
				accountId,
				buyAccountId,
				distributeDate,
				createDate,
				updateDate,
				status,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}

	
}
