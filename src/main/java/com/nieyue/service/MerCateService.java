package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MerCate;

/**
 * 商品类型逻辑层接口
 * @author yy
 *
 */
public interface MerCateService {
	/** 新增商品类型 */	
	public boolean addMerCate(MerCate merCate) ;	
	/** 删除商品类型 */	
	public boolean delMerCate(Integer merCateId) ;
	/** 更新商品类型*/	
	public boolean updateMerCate(MerCate merCate);
	/** 装载商品类型 */	
	public MerCate loadMerCate(Integer merCateId);	
	/** 商品类型总共数目 */	
	public int countAll();
	/** 分页商品类型信息 */
	public List<MerCate> browsePagingMerCate(int pageNum,int pageSize,String orderName,String orderWay) ;
}
