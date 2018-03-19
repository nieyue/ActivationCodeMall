package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerRelation;

/**
 * 商品关系数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerRelationDao {
	/** 新增商品关系*/	
	public boolean addMerRelation(MerRelation merRelation) ;	
	/** 删除商品关系 */	
	public boolean delMerRelation(Integer merRelationId) ;
	/** 更新商品关系*/	
	public boolean updateMerRelation(MerRelation merRelation);
	/** 装载商品关系 */	
	public MerRelation loadMerRelation(Integer merRelationId);	
	/** 商品关系总共数目 */	
	public int countAll(
			@Param("platformMerId")Integer platformMerId,
			@Param("sellerMerId")Integer sellerMerId,
			@Param("sellerAccountId")Integer sellerAccountId
			);	
	/** 分页商品关系信息 */
	public List<MerRelation> browsePagingMerRelation(
			@Param("platformMerId")Integer platformMerId,
			@Param("sellerMerId")Integer sellerMerId,
			@Param("sellerAccountId")Integer sellerAccountId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
