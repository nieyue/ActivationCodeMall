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
import com.nieyue.bean.Sincerity;
import com.nieyue.dao.AccountDao;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.AccountLevelService;
import com.nieyue.service.AccountService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.IntegralService;
import com.nieyue.service.SincerityService;
@Service
public class AccountServiceImpl implements AccountService{
	@Resource
	AccountDao accountDao;
	@Resource
	FinanceService financeService;
	@Resource
	AccountLevelService accountLevelService;
	@Resource
	IntegralService integralService;
	@Resource
	SincerityService sincerityService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addAccount(Account account) {
		boolean b=false;
		List<AccountLevel> accountLevell = accountLevelService.browsePagingAccountLevel(0, 1, 1, "account_level_id", "asc");
		if(accountLevell.size()!=1){
			return b;
		}
		List<AccountLevel> accountLevell2 = accountLevelService.browsePagingAccountLevel(1, 1, 1, "account_level_id", "asc");
		if(accountLevell2.size()!=1){
			return b;
		}
		account.setCreateDate(new Date());
		account.setLoginDate(new Date());
		account.setStatus(0);
		account.setCardSecretReceive(0);//默认卡密接收全部
		account.setSafetyGrade(1);//默认安全低
		account.setAuth(0);//没认证
		//增加账户
		b = accountDao.addAccount(account);
		if(!b){
		throw new CommonRollbackException("账户增加异常");
			}
		Finance finance=new Finance();
		finance.setAccountId(account.getAccountId());
		//增加财务
		b=financeService.addFinance(finance);
		if(!b){
			throw new CommonRollbackException("财务增加异常");
		}
		//增加积分
		Integral integral =new Integral();
		integral.setAccountId(account.getAccountId());
		integral.setName(accountLevell.get(0).getName());
		if(account.getRoleName().equals("用户")){
			integral.setUpgradeIntegral(accountLevell2.get(0).getUserUpgradeIntegral());//升级积分			
		}else{
			integral.setUpgradeIntegral(accountLevell2.get(0).getSellerUpgradeIntegral());//升级积分			
		}
		b=integralService.addIntegral(integral);
		if(!b){
			throw new CommonRollbackException("积分增加异常");
		}
		Sincerity sincerity=new Sincerity();
		sincerity.setAccountId(account.getAccountId());
		b=sincerityService.addSincerity(sincerity);
		if(!b){
			throw new CommonRollbackException("诚信增加异常");
		}
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
