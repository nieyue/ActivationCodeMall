package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.AccountLevel;
import com.nieyue.bean.Finance;
import com.nieyue.bean.Integral;
import com.nieyue.dao.AccountDao;
import com.nieyue.dao.AccountLevelDao;
import com.nieyue.dao.FinanceDao;
import com.nieyue.dao.IntegralDao;
import com.nieyue.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{
	@Resource
	AccountDao accountDao;
	@Resource
	FinanceDao financeDao;
	@Resource
	AccountLevelDao accountLevelDao;
	@Resource
	IntegralDao integralDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addAccount(Account account) {
		boolean b=false;
		List<AccountLevel> accountLevell = accountLevelDao.browsePagingAccountLevel(0, 0, 1, "account_level_id", "asc");
		if(accountLevell.size()!=1){
			return b;
		}
		Date date=new Date();
		account.setCreateDate(new Date());
		account.setLoginDate(new Date());
		account.setStatus(0);
		account.setCardSecretReceive(0);//默认卡密接收全部
		account.setSafetyGrade(1);//默认安全低
		account.setAuth(0);//没认证
		//增加账户
		b = accountDao.addAccount(account);
		Finance finance=new Finance();
		finance.setRecharge(0.0);//充值金额
		finance.setConsume(0.0);//消费金额
		finance.setWithdrawals(0.0);//提现金额
		finance.setRefund(0.0);//退款金额
		finance.setFrozen(0.0);//冻结金额
		finance.setRecommendCommission(0.0);//推荐佣金
		Double unitBaseProfit=0.0;//赠送金钱
		finance.setBaseProfit(unitBaseProfit);
		finance.setMoney(finance.getBaseProfit());//初始余额=赠送金钱+0.0
		finance.setCreateDate(date);
		finance.setUpdateDate(date);
		finance.setAccountId(account.getAccountId());
		//增加财务
		b=financeDao.addFinance(finance);
		//增加积分
		Integral integral =new Integral();
		integral.setAccountId(account.getAccountId());
		integral.setBaseProfit(0.0);
		integral.setIntegral(0.0);
		integral.setConsume(0.0);
		integral.setLevel(0);
		integral.setName("");
		integral.setUpgradeIntegral(0.0);//升级积分
		integral.setCreateDate(new Date());
		integral.setUpdateDate(new Date());
		b=integralDao.addIntegral(integral);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delAccount(Integer accountId) {
		boolean b = accountDao.delAccount(accountId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateAccount(Account account) {
		boolean b = accountDao.updateAccount(account);
		return b;
	}

	@Override
	public Account loadAccount(Integer accountId) {
		Account r = accountDao.loadAccount(accountId);
		return r;
	}

	@Override
	public int countAll(
			Integer accountId,
			Integer auth,
			String phone,
			String email,
			String realname,
			Integer roleId,
			Integer status,
			Date createDate,
			Date loginDate) {
		int c = accountDao.countAll(
				accountId,
				auth,
				phone,
				email,
				realname,
				roleId,
				status,
				createDate,
				loginDate);
		return c;
	}

	@Override
	public List<Account> browsePagingAccount(
			Integer accountId,
			Integer auth,
			String phone,
			String email,
			String realname,
			Integer roleId,
			Integer status,
			Date createDate,
			Date loginDate,
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
		List<Account> l = accountDao.browsePagingAccount(
				accountId,
				auth,
				phone,
				email,
				realname,
				roleId,
				status,
				createDate,
				loginDate,
				pageNum-1,
				pageSize, 
				orderName,
				orderWay);
		return l;
	}
	@Override
	public Account loginAccount(String adminName, String password,Integer accountId) {
		Account Account = accountDao.loginAccount(adminName, password, accountId);
		return Account;
	}

}
