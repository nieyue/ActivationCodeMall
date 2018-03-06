package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.VipNumber;
import com.nieyue.dao.VipNumberDao;
import com.nieyue.service.VipNumberService;
@Service
public class VipNumberServiceImpl implements VipNumberService{
	@Resource
	VipNumberDao vipNumberDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVipNumber(VipNumber vipNumber) {
		vipNumber.setCreateDate(new Date());
		vipNumber.setUpdateDate(new Date());
		boolean b = vipNumberDao.addVipNumber(vipNumber);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVipNumber(Integer vipNumberId) {
		boolean b = vipNumberDao.delVipNumber(vipNumberId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVipNumber(VipNumber vipNumber) {
		vipNumber.setUpdateDate(new Date());
		boolean b = vipNumberDao.updateVipNumber(vipNumber);
		return b;
	}

	@Override
	public VipNumber loadVipNumber(Integer vipNumberId) {
		VipNumber r = vipNumberDao.loadVipNumber(vipNumberId);
		return r;
	}

	@Override
	public int countAll(
			Integer number,
			Integer  accountId,
			Integer realMasterId,
			Date createDate,
			Date updateDate,
			Integer status
			) {
		int c = vipNumberDao.countAll(
				number,accountId,realMasterId,createDate,updateDate,status);
		return c;
	}

	@Override
	public List<VipNumber> browsePagingVipNumber(
			Integer number,
			Integer  accountId,
			Integer realMasterId,
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
		List<VipNumber> l = vipNumberDao.browsePagingVipNumber(
				number,
				accountId,
				realMasterId,
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
