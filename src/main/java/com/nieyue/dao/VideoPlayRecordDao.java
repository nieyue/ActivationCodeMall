package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.VideoPlayRecord;

/**
 * 视频播放记录数据库接口
 * @author yy
 *
 */
@Mapper
public interface VideoPlayRecordDao {
	/** 新增视频播放记录*/	
	public boolean addVideoPlayRecord(VideoPlayRecord videoPlayRecord) ;	
	/** 删除视频播放记录 */	
	public boolean delVideoPlayRecord(Integer videoPlayRecordId) ;
	/** 更新视频播放记录*/	
	public boolean updateVideoPlayRecord(VideoPlayRecord videoPlayRecord);
	/** 装载视频播放记录 */	
	public VideoPlayRecord loadVideoPlayRecord(Integer videoPlayRecordId);	
	/** 视频播放记录总共数目 */	
	public int countAll(
			@Param("videoId")Integer videoId,
			@Param("accountId")Integer accountId
			);	
	/** 分页视频播放记录信息 */
	public List<VideoPlayRecord> browsePagingVideoPlayRecord(
			@Param("videoId")Integer videoId,
			@Param("accountId")Integer accountId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
