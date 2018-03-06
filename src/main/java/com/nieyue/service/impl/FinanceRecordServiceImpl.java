package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.FinanceRecord;
import com.nieyue.dao.FinanceRecordDao;
import com.nieyue.service.FinanceRecordService;
@Service
public class FinanceRecordServiceImpl implements FinanceRecordService{
	@Resource
	FinanceRecordDao financeRecordDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addFinanceRecord(FinanceRecord financeRecord) {
		financeRecord.setCreateDate(new Date());
		financeRecord.setUpdateDate(new Date());
		boolean b = financeRecordDao.addFinanceRecord(financeRecord);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delFinanceRecord(Integer financeRecordId) {
		boolean b = financeRecordDao.delFinanceRecord(financeRecordId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateFinanceRecord(FinanceRecord financeRecord) {
		financeRecord.setUpdateDate(new Date());
		boolean b = financeRecordDao.updateFinanceRecord(financeRecord);
		return b;
	}

	@Override
	public FinanceRecord loadFinanceRecord(Integer financeRecordId) {
		FinanceRecord r = financeRecordDao.loadFinanceRecord(financeRecordId);
		return r;
	}

	@Override
	public int countAll(
			Integer accountId,
			Integer status,
			Integer method,
			Integer type,
			String transactionNumber,
			Date createDate,
			Date updateDate) {
		int c = financeRecordDao.countAll(accountId,status,method,type,transactionNumber,createDate,updateDate);
		return c;
	}

	@Override
	public List<FinanceRecord> browsePagingFinanceRecord(
			Integer accountId,
			Integer status,
			Integer method,
			Integer type,
			String transactionNumber,
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
		List<FinanceRecord> l = financeRecordDao.browsePagingFinanceRecord(accountId,status,method,type,transactionNumber,createDate,updateDate,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
