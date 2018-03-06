package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.VideoCache;

/**
 * 视频缓存数据库接口
 * @author yy
 *
 */
@Mapper
public interface VideoCacheDao {
	/** 新增视频缓存*/	
	public boolean addVideoCache(VideoCache videoCache) ;	
	/** 删除视频缓存 */	
	public boolean delVideoCache(Integer videoCacheId) ;
	/** 更新视频缓存*/	
	public boolean updateVideoCache(VideoCache videoCache);
	/** 装载视频缓存 */	
	public VideoCache loadVideoCache(Integer videoCacheId);	
	/** 视频缓存总共数目 */	
	public int countAll(
			@Param("videoId")Integer videoId,
			@Param("accountId")Integer accountId
			);	
	/** 分页视频缓存信息 */
	public List<VideoCache> browsePagingVideoCache(
			@Param("videoId")Integer videoId,
			@Param("accountId")Integer accountId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
