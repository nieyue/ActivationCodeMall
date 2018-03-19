package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MerRelation;

/**
 * 商品关系逻辑层接口
 * @author yy
 *
 */
public interface MerRelationService {
	/** 新增商品关系 */	
	public boolean addMerRelation(MerRelation merRelation) ;	
	/** 删除商品关系 */	
	public boolean delMerRelation(Integer merRelationId) ;
	/** 更新商品关系*/	
	public boolean updateMerRelation(MerRelation merRelation);
	/** 装载商品关系 */	
	public MerRelation loadMerRelation(Integer merRelationId);	
	/** 商品关系总共数目 */	
	public int countAll(
			Integer platformMerId,
			Integer sellerMerId,
			Integer sellerAccountId
			);
	/** 分页商品关系信息 */
	public List<MerRelation> browsePagingMerRelation(
			Integer platformMerId,
			Integer sellerMerId,
			Integer sellerAccountId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
