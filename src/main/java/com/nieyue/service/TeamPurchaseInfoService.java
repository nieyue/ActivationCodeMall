package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.TeamPurchaseInfo;

/**
 * 团购信息逻辑层接口
 * @author yy
 *
 */
public interface TeamPurchaseInfoService {
	/** 新增团购信息 */	
	public boolean addTeamPurchaseInfo(TeamPurchaseInfo teamPurchaseInfo) ;	
	/** 删除团购信息 */	
	public boolean delTeamPurchaseInfo(Integer teamPurchaseInfoId) ;
	/** 更新团购信息*/	
	public boolean updateTeamPurchaseInfo(TeamPurchaseInfo teamPurchaseInfo);
	/** 装载团购信息 */	
	public TeamPurchaseInfo loadTeamPurchaseInfo(Integer teamPurchaseInfoId);	
	/** 团购信息总共数目 */	
	public int countAll(
			Integer accountId,
			Date createDate,
			Date updateDate
			);
	/** 分页团购信息信息 */
	public List<TeamPurchaseInfo> browsePagingTeamPurchaseInfo(
			Integer accountId,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
