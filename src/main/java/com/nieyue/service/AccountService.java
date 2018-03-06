package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Account;

/**
 * 账户逻辑层接口
 * @author yy
 *
 */
public interface AccountService {
	/** 新增账户 */	
	public boolean addAccount(Account account) ;	
	/** 删除账户 */	
	public boolean delAccount(Integer accountId) ;
	/** 更新账户*/	
	public boolean updateAccount(Account account);
	/** 装载账户 */	
	public Account loadAccount(Integer accountId);
	/** 登录管理员 */	
	public Account loginAccount(String adminName,String password,Integer accountId);
	/** 账户总共数目 */	
	public int countAll(
			Integer accountId,
			Integer auth,
			String phone,
			String realname,
			Integer roleId,
			Integer status,
			Date createDate,
			Date loginDate
			);
	/** 分页账户信息 */
	public List<Account> browsePagingAccount(
			Integer accountId,
			Integer auth,
			String phone,
			String realname,
			Integer roleId,
			Integer status,
			Date createDate,
			Date loginDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
