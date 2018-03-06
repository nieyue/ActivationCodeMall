package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.VideoSetCollect;

/**
 * 视频集收藏数据库接口
 * @author yy
 *
 */
@Mapper
public interface VideoSetCollectDao {
	/** 新增视频集收藏*/	
	public boolean addVideoSetCollect(VideoSetCollect videoSetCollect) ;	
	/** 删除视频集收藏 */	
	public boolean delVideoSetCollect(Integer videoSetCollectId) ;
	/** 更新视频集收藏*/	
	public boolean updateVideoSetCollect(VideoSetCollect videoSetCollect);
	/** 装载视频集收藏 */	
	public VideoSetCollect loadVideoSetCollect(Integer videoSetCollectId);	
	/** 视频集收藏总共数目 */	
	public int countAll(
			@Param("videoSetId")Integer videoSetId,
			@Param("accountId")Integer accountId
			);	
	/** 分页视频集收藏信息 */
	public List<VideoSetCollect> browsePagingVideoSetCollect(
			@Param("videoSetId")Integer videoSetId,
			@Param("accountId")Integer accountId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
