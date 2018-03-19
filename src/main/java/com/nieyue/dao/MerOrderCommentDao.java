package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerOrderComment;

/**
 * 商品订单评论数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerOrderCommentDao {
	/** 新增商品订单评论*/	
	public boolean addMerOrderComment(MerOrderComment merOrderComment) ;	
	/** 删除商品订单评论 */	
	public boolean delMerOrderComment(Integer merOrderCommentId) ;
	/** 更新商品订单评论*/	
	public boolean updateMerOrderComment(MerOrderComment merOrderComment);
	/** 装载商品订单评论 */	
	public MerOrderComment loadMerOrderComment(Integer merOrderCommentId);	
	/** 商品订单评论总共数目 */	
	public int countAll(
			@Param("merScore")Double merScore,
			@Param("merId")Integer merId,
			@Param("orderId")Integer orderId,
			@Param("accountId")Integer accountId
			);	
	/** 分页商品订单评论信息 */
	public List<MerOrderComment> browsePagingMerOrderComment(
			@Param("merScore")Double merScore,
			@Param("merId")Integer merId,
			@Param("orderId")Integer orderId,
			@Param("accountId")Integer accountId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
