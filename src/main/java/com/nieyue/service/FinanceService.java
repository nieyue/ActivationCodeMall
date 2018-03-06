package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Finance;

/**
 * 财务逻辑层接口
 * @author yy
 *
 */
public interface FinanceService {
	/** 新增财务 */	
	public boolean addFinance(Finance finance) ;	
	/** 删除财务 */	
	public boolean delFinance(Integer financeId) ;
	/** 更新财务*/	
	public boolean updateFinance(Finance finance);
	/** 装载财务 */	
	public Finance loadFinance(Integer financeId);	
	/** 财务总共数目 */	
	public int countAll(
			Double money,
			Integer accountId);
	/** 分页财务信息 */
	public List<Finance> browsePagingFinance(Double money,Integer accountId,int pageNum,int pageSize,String orderName,String orderWay) ;
}
