package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.SpreadOrderAccount;
import com.nieyue.dao.SpreadOrderAccountDao;
import com.nieyue.service.SpreadOrderAccountService;
@Service
public class SpreadOrderAccountServiceImpl implements SpreadOrderAccountService{
	@Resource
	SpreadOrderAccountDao spreadOrderAccountDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addSpreadOrderAccount(SpreadOrderAccount spreadOrderAccount) {
		spreadOrderAccount.setCreateDate(new Date());
		boolean b = spreadOrderAccountDao.addSpreadOrderAccount(spreadOrderAccount);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delSpreadOrderAccount(Integer spreadOrderAccountId) {
		boolean b = spreadOrderAccountDao.delSpreadOrderAccount(spreadOrderAccountId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateSpreadOrderAccount(SpreadOrderAccount spreadOrderAccount) {
		boolean b = spreadOrderAccountDao.updateSpreadOrderAccount(spreadOrderAccount);
		return b;
	}

	@Override
	public SpreadOrderAccount loadSpreadOrderAccount(Integer spreadOrderAccountId) {
		SpreadOrderAccount r = spreadOrderAccountDao.loadSpreadOrderAccount(spreadOrderAccountId);
		return r;
	}

	@Override
	public int countAll(Integer accountId) {
		int c = spreadOrderAccountDao.countAll(accountId);
		return c;
	}

	@Override
	public List<SpreadOrderAccount> browsePagingSpreadOrderAccount(Integer accountId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<SpreadOrderAccount> l = spreadOrderAccountDao.browsePagingSpreadOrderAccount(accountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
