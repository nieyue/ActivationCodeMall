package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.MerCommon;
import com.nieyue.dao.MerCommonDao;
import com.nieyue.service.MerCommonService;
@Service
public class MerCommonServiceImpl implements MerCommonService{
	@Resource
	MerCommonDao merCommonDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerCommon(MerCommon merCommon) {
		merCommon.setUpdateDate(new Date());
		boolean b = merCommonDao.addMerCommon(merCommon);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerCommon(Integer merCommonId) {
		boolean b = merCommonDao.delMerCommon(merCommonId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerCommon(MerCommon merCommon) {
		merCommon.setUpdateDate(new Date());
		boolean b = merCommonDao.updateMerCommon(merCommon);
		return b;
	}

	@Override
	public MerCommon loadMerCommon(Integer merCommonId) {
		MerCommon r = merCommonDao.loadMerCommon(merCommonId);
		return r;
	}

	@Override
	public int countAll() {
		int c = merCommonDao.countAll();
		return c;
	}

	@Override
	public List<MerCommon> browsePagingMerCommon(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MerCommon> l = merCommonDao.browsePagingMerCommon(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
