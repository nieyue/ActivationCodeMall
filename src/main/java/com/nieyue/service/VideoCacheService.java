package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.VideoCache;

/**
 * 视频缓存逻辑层接口
 * @author yy
 *
 */
public interface VideoCacheService {
	/** 新增视频缓存 */	
	public boolean addVideoCache(VideoCache videoCache) ;	
	/** 删除视频缓存 */	
	public boolean delVideoCache(Integer videoCacheId) ;
	/** 更新视频缓存*/	
	public boolean updateVideoCache(VideoCache videoCache);
	/** 装载视频缓存 */	
	public VideoCache loadVideoCache(Integer videoCacheId);	
	/** 视频缓存总共数目 */	
	public int countAll(
			Integer videoId,
			Integer accountId
			);
	/** 分页视频缓存信息 */
	public List<VideoCache> browsePagingVideoCache(
			Integer videoId,
			Integer accountId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
