package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.MerNotice;
import com.nieyue.dao.MerNoticeDao;
import com.nieyue.service.MerNoticeService;
@Service
public class MerNoticeServiceImpl implements MerNoticeService{
	@Resource
	MerNoticeDao merNoticeDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerNotice(MerNotice merNotice) {
		merNotice.setCreateDate(new Date());
		boolean b = merNoticeDao.addMerNotice(merNotice);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerNotice(Integer merNoticeId) {
		boolean b = merNoticeDao.delMerNotice(merNoticeId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerNotice(MerNotice merNotice) {
		boolean b = merNoticeDao.updateMerNotice(merNotice);
		return b;
	}

	@Override
	public MerNotice loadMerNotice(Integer merNoticeId) {
		MerNotice r = merNoticeDao.loadMerNotice(merNoticeId);
		return r;
	}

	@Override
	public int countAll(
			Integer merId) {
		int c = merNoticeDao.countAll(merId);
		return c;
	}

	@Override
	public List<MerNotice> browsePagingMerNotice(
			Integer merId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MerNotice> l = merNoticeDao.browsePagingMerNotice(merId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
