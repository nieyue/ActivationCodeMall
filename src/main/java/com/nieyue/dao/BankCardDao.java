package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.BankCard;

/**
 * 银行卡数据库接口
 * @author yy
 *
 */
@Mapper
public interface BankCardDao {
	/** 新增银行卡*/	
	public boolean addBankCard(BankCard bankCard) ;	
	/** 删除银行卡 */	
	public boolean delBankCard(Integer bankCardId) ;
	/** 更新银行卡*/	
	public boolean updateBankCard(BankCard bankCard);
	/** 装载银行卡 */	
	public BankCard loadBankCard(Integer bankCardId);	
	/** 银行卡总共数目 */	
	public int countAll(@Param("accountId")Integer accountId);	
	/** 分页银行卡信息 */
	public List<BankCard> browsePagingBankCard(@Param("accountId")Integer accountId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
