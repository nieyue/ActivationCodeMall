package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerCommon;

/**
 * 商品公用数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerCommonDao {
	/** 新增商品公用*/	
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
	public List<MerCommon> browsePagingMerCommon(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
