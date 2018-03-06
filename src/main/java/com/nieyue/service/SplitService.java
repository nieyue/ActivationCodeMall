package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Split;

/**
 * 拆分逻辑层接口
 * @author yy
 *
 */
public interface SplitService {
	/** 新增拆分 */	
	public boolean addSplit(Split split) ;	
	/** 删除拆分 */	
	public boolean delSplit(Integer splitId) ;
	/** 更新拆分*/	
	public boolean updateSplit(Split split);
	/** 装载拆分 */	
	public Split loadSplit(Integer splitId);	
	/** 推荐给上级 */	
	public boolean recommendParent(Integer splitId,Integer accountId);	
	/** 立即拆分 */	
	public boolean immediatelySplit(Integer splitId,Integer accountId);	
	/** 拆分总共数目 */	
	public int countAll(
			Integer recommendAccountId,
			Integer accountId,
			Integer buyAccountId,
			Date applyDate,
			Date splitDate,
			Date createDate,
			Date updateDate,
			Integer status
			);
	/** 分页拆分信息 */
	public List<Split> browsePagingSplit(
			Integer recommendAccountId,
			Integer accountId,
			Integer buyAccountId,
			Date splitDate,
			Date SplitDate,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
