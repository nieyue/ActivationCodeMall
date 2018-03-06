package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.AccountParent;
import com.nieyue.dao.AccountParentDao;
import com.nieyue.service.AccountParentService;
@Service
public class AccountParentServiceImpl implements AccountParentService{
	@Resource
	AccountParentDao accountParentDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addAccountParent(AccountParent accountParent) {
		accountParent.setCreateDate(new Date());
		accountParent.setUpdateDate(new Date());
		accountParent.setSubordinateNumber(0);
		boolean b = accountParentDao.addAccountParent(accountParent);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delAccountParent(Integer accountParentId) {
		boolean b = accountParentDao.delAccountParent(accountParentId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateAccountParent(AccountParent accountParent) {
		accountParent.setUpdateDate(new Date());
		boolean b = accountParentDao.updateAccountParent(accountParent);
		return b;
	}

	@Override
	public AccountParent loadAccountParent(Integer accountParentId) {
		AccountParent r = accountParentDao.loadAccountParent(accountParentId);
		return r;
	}

	@Override
	public int countAll(
			String phone,
			Integer accountLevelId,
			Integer accountId,
			Integer masterId,
			Integer realMasterId,
			Date createDate,
			Date updateDate) {
		int c = accountParentDao.countAll(
				 phone,
				 accountLevelId,
				 accountId,
				 masterId,
				 realMasterId,
				 createDate,
				 updateDate);
		return c;
	}

	@Override
	public List<AccountParent> browsePagingAccountParent(
			String phone,
			Integer accountLevelId,
			Integer accountId,
			Integer masterId,
			Integer realMasterId,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AccountParent> l = accountParentDao.browsePagingAccountParent(
				 phone, 
				 accountLevelId,
				 accountId,
				 masterId,
				 realMasterId,
				 createDate,
				 updateDate,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
