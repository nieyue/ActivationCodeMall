package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.VideoSetTag;

/**
 * 视频集标签逻辑层接口
 * @author yy
 *
 */
public interface VideoSetTagService {
	/** 新增视频集标签 */	
	public boolean addVideoSetTag(VideoSetTag videoSetTag) ;	
	/** 删除视频集标签 */	
	public boolean delVideoSetTag(Integer videoSetTagId) ;
	/** 更新视频集标签*/	
	public boolean updateVideoSetTag(VideoSetTag videoSetTag);
	/** 装载视频集标签 */	
	public VideoSetTag loadVideoSetTag(Integer videoSetTagId);	
	/** 视频集标签总共数目 */	
	public int countAll(
			Integer videoSetId
			);
	/** 分页视频集标签信息 */
	public List<VideoSetTag> browsePagingVideoSetTag(
			Integer videoSetId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
