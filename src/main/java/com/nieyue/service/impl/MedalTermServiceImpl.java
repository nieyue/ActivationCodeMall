package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.MedalTerm;
import com.nieyue.dao.MedalTermDao;
import com.nieyue.service.MedalTermService;
@Service
public class MedalTermServiceImpl implements MedalTermService{
	@Resource
	MedalTermDao medalTermDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMedalTerm(MedalTerm medalTerm) {
		medalTerm.setUpdateDate(new Date());
		boolean b = medalTermDao.addMedalTerm(medalTerm);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMedalTerm(Integer medalTermId) {
		boolean b = medalTermDao.delMedalTerm(medalTermId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMedalTerm(MedalTerm medalTerm) {
		medalTerm.setUpdateDate(new Date());
		boolean b = medalTermDao.updateMedalTerm(medalTerm);
		return b;
	}

	@Override
	public MedalTerm loadMedalTerm(Integer medalTermId) {
		MedalTerm r = medalTermDao.loadMedalTerm(medalTermId);
		return r;
	}

	@Override
	public int countAll(
			) {
		int c = medalTermDao.countAll();
		return c;
	}

	@Override
	public List<MedalTerm> browsePagingMedalTerm(
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MedalTerm> l = medalTermDao.browsePagingMedalTerm(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
