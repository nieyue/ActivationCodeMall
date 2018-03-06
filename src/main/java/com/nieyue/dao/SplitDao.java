package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Split;

/**
 * 拆分数据库接口
 * @author yy
 *
 */
@Mapper
public interface SplitDao {
	/** 新增拆分*/	
	public boolean addSplit(Split split) ;	
	/** 删除拆分 */	
	public boolean delSplit(Integer splitId) ;
	/** 更新拆分*/	
	public boolean updateSplit(Split split);
	/** 装载拆分 */	
	public Split loadSplit(Integer splitId);	
	/** 拆分总共数目 */	
	public int countAll(
			@Param("recommendAccountId")Integer recommendAccountId,
			@Param("accountId")Integer accountId,
			@Param("buyAccountId")Integer buyAccountId,
			@Param("applyDate")Date applyDate,
			@Param("splitDate")Date splitDate,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页拆分信息 */
	public List<Split> browsePagingSplit(
			@Param("recommendAccountId")Integer recommendAccountId,
			@Param("accountId")Integer accountId,
			@Param("buyAccountId")Integer buyAccountId,
			@Param("applyDate")Date applyDate,
			@Param("splitDate")Date splitDate,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
