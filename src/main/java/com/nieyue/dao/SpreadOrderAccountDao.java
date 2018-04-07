package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.SpreadOrderAccount;

/**
 * 推广订单账户数据库接口
 * @author yy
 *
 */
@Mapper
public interface SpreadOrderAccountDao {
	/** 新增推广订单账户*/	
	public boolean addSpreadOrderAccount(SpreadOrderAccount spreadOrderAccount) ;	
	/** 删除推广订单账户 */	
	public boolean delSpreadOrderAccount(Integer spreadOrderAccountId) ;
	/** 更新推广订单账户*/	
	public boolean updateSpreadOrderAccount(SpreadOrderAccount spreadOrderAccount);
	/** 装载推广订单账户 */	
	public SpreadOrderAccount loadSpreadOrderAccount(Integer spreadOrderAccountId);	
	/** 推广订单账户总共数目 */	
	public int countAll(@Param("accountId") Integer accountId);	
	/** 分页推广订单账户信息 */
	public List<SpreadOrderAccount> browsePagingSpreadOrderAccount(
			@Param("accountId") Integer accountId,
			@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
