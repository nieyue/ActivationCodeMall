package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.VipGrowthRecord;
import com.nieyue.dao.VipGrowthRecordDao;
import com.nieyue.service.VipGrowthRecordService;
@Service
public class VipGrowthRecordServiceImpl implements VipGrowthRecordService{
	@Resource
	VipGrowthRecordDao vipGrowthRecordDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVipGrowthRecord(VipGrowthRecord vipGrowthRecord) {
		vipGrowthRecord.setCreateDate(new Date());
		boolean b = vipGrowthRecordDao.addVipGrowthRecord(vipGrowthRecord);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVipGrowthRecord(Integer vipGrowthRecordId) {
		boolean b = vipGrowthRecordDao.delVipGrowthRecord(vipGrowthRecordId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVipGrowthRecord(VipGrowthRecord vipGrowthRecord) {
		boolean b = vipGrowthRecordDao.updateVipGrowthRecord(vipGrowthRecord);
		return b;
	}

	@Override
	public VipGrowthRecord loadVipGrowthRecord(Integer vipGrowthRecordId) {
		VipGrowthRecord r = vipGrowthRecordDao.loadVipGrowthRecord(vipGrowthRecordId);
		return r;
	}

	@Override
	public int countAll(Integer accountId,Integer level,Date createDate) {
		int c = vipGrowthRecordDao.countAll(accountId,level,createDate);
		return c;
	}

	@Override
	public List<VipGrowthRecord> browsePagingVipGrowthRecord(Integer accountId,Integer level,Date createDate,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<VipGrowthRecord> l = vipGrowthRecordDao.browsePagingVipGrowthRecord(accountId,level,createDate,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
