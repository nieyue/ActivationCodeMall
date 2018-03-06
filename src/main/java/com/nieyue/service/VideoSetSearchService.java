package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.VideoSetSearch;

/**
 * 视频集搜索逻辑层接口
 * @author yy
 *
 */
public interface VideoSetSearchService {
	/** 新增视频集搜索 */	
	public boolean addVideoSetSearch(VideoSetSearch videoSetSearch) ;	
	/** 删除视频集搜索 */	
	public boolean delVideoSetSearch(Integer videoSetSearchId) ;
	/** 更新视频集搜索*/	
	public boolean updateVideoSetSearch(VideoSetSearch videoSetSearch);
	/** 装载视频集搜索 */	
	public VideoSetSearch loadVideoSetSearch(Integer videoSetSearchId);	
	/** 视频集搜索总共数目 */	
	public int countAll(
			String name,
			Integer number
			);
	/** 分页视频集搜索信息 */
	public List<VideoSetSearch> browsePagingVideoSetSearch(
			String name,
			Integer number,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
