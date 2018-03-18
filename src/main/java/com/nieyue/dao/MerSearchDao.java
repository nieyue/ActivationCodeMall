package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerSearch;

/**
 * 商品搜索数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerSearchDao {
	/** 新增商品搜索*/	
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
	public List<MerSearch> browsePagingMerSearch(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
