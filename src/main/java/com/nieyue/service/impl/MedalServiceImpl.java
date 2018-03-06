package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Medal;
import com.nieyue.dao.MedalDao;
import com.nieyue.service.MedalService;
@Service
public class MedalServiceImpl implements MedalService{
	@Resource
	MedalDao medalDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMedal(Medal medal) {
		medal.setCreateDate(new Date());
		medal.setUpdateDate(new Date());
		boolean b = medalDao.addMedal(medal);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMedal(Integer medalId) {
		boolean b =medalDao.delMedal(medalId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMedal(Medal medal) {
		medal.setUpdateDate(new Date());
		boolean b = medalDao.updateMedal(medal);
		return b;
	}

	@Override
	public Medal loadMedal(Integer medalId) {
		Medal r = medalDao.loadMedal(medalId);
		return r;
	}

	@Override
	public int countAll(
			Integer medalTermId,
			Integer accountId,
			Date createDate,
			Date updateDate) {
		int c = medalDao.countAll(
				medalTermId,
				accountId,
				createDate,
				updateDate);
		return c;
	}

	@Override
	public List<Medal> browsePagingMedal(
			Integer medalTermId,
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
		List<Medal> l = medalDao.browsePagingMedal(
				medalTermId,
				accountId,
				createDate,
				updateDate,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}

	
}
