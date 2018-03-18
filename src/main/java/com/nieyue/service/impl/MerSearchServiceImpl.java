package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.MerSearch;
import com.nieyue.dao.MerSearchDao;
import com.nieyue.service.MerSearchService;
@Service
public class MerSearchServiceImpl implements MerSearchService{
	@Resource
	MerSearchDao merSearchDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerSearch(MerSearch merSearch) {
		merSearch.setUpdateDate(new Date());
		boolean b = merSearchDao.addMerSearch(merSearch);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerSearch(Integer merSearchId) {
		boolean b = merSearchDao.delMerSearch(merSearchId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerSearch(MerSearch merSearch) {
		merSearch.setUpdateDate(new Date());
		boolean b = merSearchDao.updateMerSearch(merSearch);
		return b;
	}

	@Override
	public MerSearch loadMerSearch(Integer merSearchId) {
		MerSearch r = merSearchDao.loadMerSearch(merSearchId);
		return r;
	}

	@Override
	public int countAll() {
		int c = merSearchDao.countAll();
		return c;
	}

	@Override
	public List<MerSearch> browsePagingMerSearch(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MerSearch> l = merSearchDao.browsePagingMerSearch(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
