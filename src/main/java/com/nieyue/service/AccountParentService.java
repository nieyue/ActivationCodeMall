package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.AccountParent;

/**
 * 账户上级逻辑层接口
 * @author yy
 *
 */
public interface AccountParentService {
	/** 新增账户上级 */	
	public boolean addAccountParent(AccountParent accountParent) ;	
	/** 删除账户上级 */	
	public boolean delAccountParent(Integer accountParentId) ;
	/** 更新账户上级*/	
	public boolean updateAccountParent(AccountParent accountParent);
	/** 装载账户上级 */	
	public AccountParent loadAccountParent(Integer accountParentId);	
	/** 账户上级总共数目 */	
	public int countAll(
			String phone,
			Integer accountLevelId,
			Integer accountId,
			Integer masterId,
			Integer realMasterId,
			Date createDate,
			Date updateDate
			);
	/** 分页账户上级信息 */
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
			String orderWay) ;
}
