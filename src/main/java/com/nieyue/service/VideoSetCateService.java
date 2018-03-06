package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.VideoSetCate;

/**
 * 视频集类型逻辑层接口
 * @author yy
 *
 */
public interface VideoSetCateService {
	/** 新增视频集类型 */	
	public boolean addVideoSetCate(VideoSetCate videoSetCate) ;	
	/** 删除视频集类型 */	
	public boolean delVideoSetCate(Integer videoSetCateId) ;
	/** 更新视频集类型*/	
	public boolean updateVideoSetCate(VideoSetCate videoSetCate);
	/** 装载视频集类型 */	
	public VideoSetCate loadVideoSetCate(Integer videoSetCateId);	
	/** 视频集类型总共数目 */	
	public int countAll(
			);
	/** 分页视频集类型信息 */
	public List<VideoSetCate> browsePagingVideoSetCate(
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
