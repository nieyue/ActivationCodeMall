package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AccountParent;

/**
 * 账户上级数据库接口
 * @author yy
 *
 */
@Mapper
public interface AccountParentDao {
	/** 新增账户上级*/	
	public boolean addAccountParent(AccountParent accountParent) ;	
	/** 删除账户上级 */	
	public boolean delAccountParent(Integer accountParentId) ;
	/** 更新账户上级*/	
	public boolean updateAccountParent(AccountParent accountParent);
	/** 装载账户上级 */	
	public AccountParent loadAccountParent(Integer accountParentId);	
	/** 账户上级总共数目 */	
	public int countAll(
			@Param("phone")String phone,
			@Param("accountLevelId")Integer accountLevelId,
			@Param("accountId")Integer accountId,
			@Param("masterId")Integer masterId,
			@Param("realMasterId")Integer realMasterId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页账户上级信息 */
	public List<AccountParent> browsePagingAccountParent(
			@Param("phone")String phone,
			@Param("accountLevelId")Integer accountLevelId,
			@Param("accountId")Integer accountId,
			@Param("masterId")Integer masterId,
			@Param("realMasterId")Integer realMasterId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
