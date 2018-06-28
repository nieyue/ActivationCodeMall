package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.SpreadLink;

/**
 * 推广链接逻辑层接口
 * @author yy
 *
 */
public interface SpreadLinkService {
	/** 新增推广链接 */	
	public boolean addSpreadLink(SpreadLink spreadLink) ;	
	/** 删除推广链接 */	
	public boolean delSpreadLink(Integer spreadLinkId) ;
	/** 更新推广链接*/	
	public boolean updateSpreadLink(SpreadLink spreadLink);
	/** 装载推广链接 */	
	public SpreadLink loadSpreadLink(Integer spreadLinkId);	
	/** 推广链接总共数目 */	
	public int countAll(Integer merId,Integer accountId);
	/** 分页推广链接信息 */
	public List<SpreadLink> browsePagingSpreadLink(Integer merId,Integer accountId,int pageNum,int pageSize,String orderName,String orderWay) ;
}
