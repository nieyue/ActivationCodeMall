package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.SpreadOrderAccount;

/**
 * 推广订单账户逻辑层接口
 * @author yy
 *
 */
public interface SpreadOrderAccountService {
	/** 新增推广订单账户 */	
	public boolean addSpreadOrderAccount(SpreadOrderAccount spreadOrderAccount) ;	
	/** 删除推广订单账户 */	
	public boolean delSpreadOrderAccount(Integer spreadOrderAccountId) ;
	/** 更新推广订单账户*/	
	public boolean updateSpreadOrderAccount(SpreadOrderAccount spreadOrderAccount);
	/** 装载推广订单账户 */	
	public SpreadOrderAccount loadSpreadOrderAccount(Integer spreadOrderAccountId);	
	/** 推广订单账户总共数目 */	
	public int countAll(Integer accountId);
	/** 分页推广订单账户信息 */
	public List<SpreadOrderAccount> browsePagingSpreadOrderAccount(Integer accountId,int pageNum,int pageSize,String orderName,String orderWay) ;
}
