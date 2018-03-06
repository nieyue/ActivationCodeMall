package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AccountLevel;

/**
 * 等级数据库接口
 * @author yy
 *
 */
@Mapper
public interface AccountLevelDao {
	/** 新增等级*/	
	public boolean addAccountLevel(AccountLevel accountLevel) ;	
	/** 删除等级 */	
	public boolean delAccountLevel(Integer accountLevelId) ;
	/** 更新等级*/	
	public boolean updateAccountLevel(AccountLevel accountLevel);
	/** 装载等级 */	
	public AccountLevel loadAccountLevel(Integer accountLevelId);	
	/** 等级总共数目 */	
	public int countAll(@Param("level")Integer level,@Param("teamPurchasePrice")Double teamPurchasePrice);	
	/** 分页等级信息 */
	public List<AccountLevel> browsePagingAccountLevel(@Param("level")Integer level,@Param("teamPurchasePrice")Double teamPurchasePrice,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
