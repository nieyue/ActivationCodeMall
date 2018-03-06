package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.VideoPlayRecord;

/**
 * 视频播放记录逻辑层接口
 * @author yy
 *
 */
public interface VideoPlayRecordService {
	/** 新增视频播放记录 */	
	public boolean addVideoPlayRecord(VideoPlayRecord videoPlayRecord) ;	
	/** 删除视频播放记录 */	
	public boolean delVideoPlayRecord(Integer videoPlayRecordId) ;
	/** 更新视频播放记录*/	
	public boolean updateVideoPlayRecord(VideoPlayRecord videoPlayRecord);
	/** 装载视频播放记录 */	
	public VideoPlayRecord loadVideoPlayRecord(Integer videoPlayRecordId);	
	/** 视频播放记录总共数目 */	
	public int countAll(
			Integer videoId,
			Integer accountId
			);
	/** 分页视频播放记录信息 */
	public List<VideoPlayRecord> browsePagingVideoPlayRecord(
			Integer videoId,
			Integer accountId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
