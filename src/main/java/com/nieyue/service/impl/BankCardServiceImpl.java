package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.BankCard;
import com.nieyue.dao.BankCardDao;
import com.nieyue.service.BankCardService;
@Service
public class BankCardServiceImpl implements BankCardService{
	@Resource
	BankCardDao bankCardDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addBankCard(BankCard bankCard) {
		bankCard.setCreateDate(new Date());
		bankCard.setUpdateDate(new Date());
		boolean b = bankCardDao.addBankCard(bankCard);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delBankCard(Integer bankCardId) {
		boolean b = bankCardDao.delBankCard(bankCardId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateBankCard(BankCard bankCard) {
		bankCard.setUpdateDate(new Date());
		boolean b = bankCardDao.updateBankCard(bankCard);
		return b;
	}

	@Override
	public BankCard loadBankCard(Integer bankCardId) {
		BankCard r = bankCardDao.loadBankCard(bankCardId);
		return r;
	}

	@Override
	public int countAll(Integer accountId) {
		int c = bankCardDao.countAll(accountId);
		return c;
	}

	@Override
	public List<BankCard> browsePagingBankCard(Integer accountId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<BankCard> l = bankCardDao.browsePagingBankCard(accountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
