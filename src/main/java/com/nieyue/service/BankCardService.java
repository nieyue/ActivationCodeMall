package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.BankCard;

/**
 * 银行卡逻辑层接口
 * @author yy
 *
 */
public interface BankCardService {
	/** 新增银行卡 */	
	public boolean addBankCard(BankCard BankCard) ;	
	/** 删除银行卡 */	
	public boolean delBankCard(Integer BankCardId) ;
	/** 更新银行卡*/	
	public boolean updateBankCard(BankCard BankCard);
	/** 装载银行卡 */	
	public BankCard loadBankCard(Integer BankCardId);	
	/** 银行卡总共数目 */	
	public int countAll(Integer accountId);
	/** 分页银行卡信息 */
	public List<BankCard> browsePagingBankCard(Integer accountId,int pageNum,int pageSize,String orderName,String orderWay) ;
}
