package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MerSearch;

/**
 * 商品搜索逻辑层接口
 * @author yy
 *
 */
public interface MerSearchService {
	/** 新增商品搜索 */	
	public boolean addMerSearch(MerSearch merSearch) ;	
	/** 删除商品搜索 */	
	public boolean delMerSearch(Integer merSearchId) ;
	/** 更新商品搜索*/	
	public boolean updateMerSearch(MerSearch merSearch);
	/** 装载商品搜索 */	
	public MerSearch loadMerSearch(Integer merSearchId);	
	/** 商品搜索总共数目 */	
	public int countAll();
	/** 分页商品搜索信息 */
	public List<MerSearch> browsePagingMerSearch(int pageNum,int pageSize,String orderName,String orderWay) ;
}
