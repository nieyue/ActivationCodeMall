package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.AccountLevel;

/**
 * 等级逻辑层接口
 * @author yy
 *
 */
public interface AccountLevelService {
	/** 新增等级 */	
	public boolean addAccountLevel(AccountLevel accountLevel) ;	
	/** 删除等级 */	
	public boolean delAccountLevel(Integer accountLevelId) ;
	/** 更新等级*/	
	public boolean updateAccountLevel(AccountLevel accountLevel);
	/** 装载等级 */	
	public AccountLevel loadAccountLevel(Integer accountLevelId);	
	/** 等级总共数目 */	
	public int countAll(Integer level,Double teamPurchasePrice);
	/** 分页等级信息 */
	public List<AccountLevel> browsePagingAccountLevel(Integer level,Double teamPurchasePric,int pageNum,int pageSize,String orderName,String orderWay) ;
}
