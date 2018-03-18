package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MerCommon;

/**
 * 商品公用逻辑层接口
 * @author yy
 *
 */
public interface MerCommonService {
	/** 新增商品公用 */	
	public boolean addMerCommon(MerCommon merCommon) ;	
	/** 删除商品公用 */	
	public boolean delMerCommon(Integer merCommonId) ;
	/** 更新商品公用*/	
	public boolean updateMerCommon(MerCommon merCommon);
	/** 装载商品公用 */	
	public MerCommon loadMerCommon(Integer merCommonId);	
	/** 商品公用总共数目 */	
	public int countAll();
	/** 分页商品公用信息 */
	public List<MerCommon> browsePagingMerCommon(int pageNum,int pageSize,String orderName,String orderWay) ;
}
