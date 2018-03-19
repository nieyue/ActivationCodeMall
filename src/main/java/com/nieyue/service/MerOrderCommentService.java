package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MerOrderComment;

/**
 * 商品订单评论逻辑层接口
 * @author yy
 *
 */
public interface MerOrderCommentService {
	/** 新增商品订单评论 */	
	public boolean addMerOrderComment(MerOrderComment merOrderComment) ;	
	/** 删除商品订单评论 */	
	public boolean delMerOrderComment(Integer merOrderCommentId) ;
	/** 更新商品订单评论*/	
	public boolean updateMerOrderComment(MerOrderComment merOrderComment);
	/** 装载商品订单评论 */	
	public MerOrderComment loadMerOrderComment(Integer merOrderCommentId);	
	/** 商品订单评论总共数目 */	
	public int countAll(
			Double merScore,
			Integer merId,
			Integer orderId,
			Integer accountId
			);
	/** 分页商品订单评论信息 */
	public List<MerOrderComment> browsePagingMerOrderComment(
			Double merScore,
			Integer merId,
			Integer orderId,
			Integer accountId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
