package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.SpreadLinkTerm;

/**
 * 推广链接项逻辑层接口
 * @author yy
 *
 */
public interface SpreadLinkTermService {
	/** 新增推广链接项 */	
	public boolean addSpreadLinkTerm(SpreadLinkTerm spreadLinkTerm) ;	
	/** 删除推广链接项 */	
	public boolean delSpreadLinkTerm(Integer spreadLinkTermId) ;
	/** 更新推广链接项*/	
	public boolean updateSpreadLinkTerm(SpreadLinkTerm spreadLinkTerm);
	/** 装载推广链接项 */	
	public SpreadLinkTerm loadSpreadLinkTerm(Integer spreadLinkTermId);	
	/** 推广链接项总共数目 */	
	public int countAll(Integer merId);
	/** 分页推广链接项信息 */
	public List<SpreadLinkTerm> browsePagingSpreadLinkTerm(Integer merId,int pageNum,int pageSize,String orderName,String orderWay) ;
}
