package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.TeamPurchaseInfo;

/**
 * 团购信息数据库接口
 * @author yy
 *
 */
@Mapper
public interface TeamPurchaseInfoDao {
	/** 新增团购信息*/	
	public boolean addTeamPurchaseInfo(TeamPurchaseInfo teamPurchaseInfo) ;	
	/** 删除团购信息 */	
	public boolean delTeamPurchaseInfo(Integer teamPurchaseInfoId) ;
	/** 更新团购信息*/	
	public boolean updateTeamPurchaseInfo(TeamPurchaseInfo teamPurchaseInfo);
	/** 装载团购信息 */	
	public TeamPurchaseInfo loadTeamPurchaseInfo(Integer teamPurchaseInfoId);	
	/** 团购信息总共数目 */	
	public int countAll(
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页团购信息信息 */
	public List<TeamPurchaseInfo> browsePagingTeamPurchaseInfo(
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
