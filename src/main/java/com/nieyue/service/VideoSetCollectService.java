package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.VideoSetCollect;

/**
 * 视频集收藏逻辑层接口
 * @author yy
 *
 */
public interface VideoSetCollectService {
	/** 新增视频集收藏 */	
	public boolean addVideoSetCollect(VideoSetCollect videoSetCollect) ;	
	/** 删除视频集收藏 */	
	public boolean delVideoSetCollect(Integer videoSetCollectId) ;
	/** 更新视频集收藏*/	
	public boolean updateVideoSetCollect(VideoSetCollect videoSetCollect);
	/** 装载视频集收藏 */	
	public VideoSetCollect loadVideoSetCollect(Integer videoSetCollectId);	
	/** 视频集收藏总共数目 */	
	public int countAll(
			Integer videoSetId,
			Integer accountId
			);
	/** 分页视频集收藏信息 */
	public List<VideoSetCollect> browsePagingVideoSetCollect(
			Integer videoSetId,
			Integer accountId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
