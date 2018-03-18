package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerCate;

/**
 * 商品类型数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerCateDao {
	/** 新增商品类型*/	
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
	public List<MerCate> browsePagingMerCate(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
