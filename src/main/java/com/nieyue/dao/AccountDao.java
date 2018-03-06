package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Account;

/**
 * 账户数据库接口
 * @author yy
 *
 */
@Mapper
public interface AccountDao {
	/** 新增账户*/	
	public boolean addAccount(Account account) ;	
	/** 删除账户 */	
	public boolean delAccount(Integer accountId) ;
	/** 更新账户*/	
	public boolean updateAccount(Account account);
	/** 装载账户 */	
	public Account loadAccount(Integer accountId);	
	/** 登录管理员 */	
	public Account loginAccount(
			@Param("adminName")String adminName,
			@Param("password")String password,
			@Param("accountId")Integer accountId);
	/** 账户总共数目 */	
	public int countAll(
			@Param("accountId")Integer accountId,
			@Param("auth")Integer auth,
			@Param("phone")String phone,
			@Param("realname")String realname,
			@Param("roleId")Integer roleId,
			@Param("status")Integer status,
			@Param("createDate")Date createDate,
			@Param("loginDate")Date loginDate
			);	
	/** 分页账户信息 */
	public List<Account> browsePagingAccount(
			@Param("accountId")Integer accountId,
			@Param("auth")Integer auth,
			@Param("phone")String phone,
			@Param("realname")String realname,
			@Param("roleId")Integer roleId,
			@Param("status")Integer status,
			@Param("createDate")Date createDate,
			@Param("loginDate")Date loginDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
