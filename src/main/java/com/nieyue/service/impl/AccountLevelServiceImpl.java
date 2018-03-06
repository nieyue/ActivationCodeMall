package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.AccountLevel;
import com.nieyue.dao.AccountLevelDao;
import com.nieyue.service.AccountLevelService;
@Service
public class AccountLevelServiceImpl implements AccountLevelService{
	@Resource
	AccountLevelDao accountLevelDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addAccountLevel(AccountLevel accountLevel) {
		accountLevel.setUpdateDate(new Date());
		boolean b = accountLevelDao.addAccountLevel(accountLevel);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delAccountLevel(Integer accountLevelId) {
		boolean b = accountLevelDao.delAccountLevel(accountLevelId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateAccountLevel(AccountLevel accountLevel) {
		accountLevel.setUpdateDate(new Date());
		boolean b = accountLevelDao.updateAccountLevel(accountLevel);
		return b;
	}

	@Override
	public AccountLevel loadAccountLevel(Integer accountLevelId) {
		AccountLevel r = accountLevelDao.loadAccountLevel(accountLevelId);
		return r;
	}

	@Override
	public int countAll(Integer level,Double teamPurchasePrice) {
		int c = accountLevelDao.countAll( level, teamPurchasePrice);
		return c;
	}

	@Override
	public List<AccountLevel> browsePagingAccountLevel(Integer level,Double teamPurchasePrice,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AccountLevel> l = accountLevelDao.browsePagingAccountLevel( level, teamPurchasePrice,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
