package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.SpreadRecord;
import com.nieyue.dao.SpreadRecordDao;
import com.nieyue.service.SpreadRecordService;
@Service
public class SpreadRecordServiceImpl implements SpreadRecordService{
	@Resource
	SpreadRecordDao spreadRecordDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addSpreadRecord(SpreadRecord spreadRecord) {
		spreadRecord.setCreateDate(new Date());
		boolean b = spreadRecordDao.addSpreadRecord(spreadRecord);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delSpreadRecord(Integer spreadRecordId) {
		boolean b = spreadRecordDao.delSpreadRecord(spreadRecordId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateSpreadRecord(SpreadRecord spreadRecord) {
		boolean b =  spreadRecordDao.updateSpreadRecord(spreadRecord);
		return b;
	}

	@Override
	public SpreadRecord loadSpreadRecord(Integer spreadRecordId) {
		SpreadRecord r = spreadRecordDao.loadSpreadRecord(spreadRecordId);
		return r;
	}

	@Override
	public int countAll(Integer accountId) {
		int c = spreadRecordDao.countAll(accountId);
		return c;
	}

	@Override
	public List<SpreadRecord> browsePagingSpreadRecord(Integer accountId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<SpreadRecord> l = spreadRecordDao.browsePagingSpreadRecord(accountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
