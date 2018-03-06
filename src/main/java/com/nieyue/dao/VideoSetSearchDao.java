package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.VideoSetSearch;

/**
 * 视频集搜索数据库接口
 * @author yy
 *
 */
@Mapper
public interface VideoSetSearchDao {
	/** 新增视频集搜索*/	
	public boolean addVideoSetSearch(VideoSetSearch videoSetSearch) ;	
	/** 删除视频集搜索 */	
	public boolean delVideoSetSearch(Integer videoSetSearchId) ;
	/** 更新视频集搜索*/	
	public boolean updateVideoSetSearch(VideoSetSearch videoSetSearch);
	/** 装载视频集搜索 */	
	public VideoSetSearch loadVideoSetSearch(Integer videoSetSearchId);	
	/** 视频集搜索总共数目 */	
	public int countAll(
			@Param("name")String name,
			@Param("number")Integer number
			);	
	/** 分页视频集搜索信息 */
	public List<VideoSetSearch> browsePagingVideoSetSearch(
			@Param("name")String name,
			@Param("number")Integer number,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
