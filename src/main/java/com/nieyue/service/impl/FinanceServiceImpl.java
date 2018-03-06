package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Finance;
import com.nieyue.dao.FinanceDao;
import com.nieyue.service.FinanceService;
@Service
public class FinanceServiceImpl implements FinanceService{
	@Resource
	FinanceDao financeDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addFinance(Finance finance) {
		finance.setCreateDate(new Date());
		finance.setUpdateDate(new Date());
		boolean b = financeDao.addFinance(finance);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delFinance(Integer financeId) {
		boolean b = financeDao.delFinance(financeId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateFinance(Finance finance) {
		finance.setUpdateDate(new Date());
		boolean b = financeDao.updateFinance(finance);
		return b;
	}

	@Override
	public Finance loadFinance(Integer financeId) {
		Finance r = financeDao.loadFinance(financeId);
		return r;
	}

	@Override
	public int countAll(
			Double money,
			Integer accountId) {
		int c = financeDao.countAll(money,accountId);
		return c;
	}

	@Override
	public List<Finance> browsePagingFinance(Double money,Integer accountId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Finance> l = financeDao.browsePagingFinance(money,accountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}
	
}
