package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Video;

/**
 * 视频逻辑层接口
 * @author yy
 *
 */
public interface VideoService {
	/** 新增视频 */	
	public boolean addVideo(Video video) ;	
	/** 删除视频 */	
	public boolean delVideo(Integer videoId) ;
	/** 更新视频*/	
	public boolean updateVideo(Video video);
	/** 装载视频 */	
	public Video loadVideo(Integer videoId);	
	/** 观看视频 */	
	public boolean watchVideo(Integer videoId,Integer accountId,Integer type);	
	/** 视频总共数目 */	
	public int countAll(
			Integer videoSetId,
			Date createDate,
			Date updateDate,
			Integer status
			);
	/** 分页视频信息 */
	public List<Video> browsePagingVideo(
			Integer videoSetId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
