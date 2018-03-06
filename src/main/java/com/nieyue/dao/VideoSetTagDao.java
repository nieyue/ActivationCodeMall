package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.VideoSetTag;

/**
 * 视频集标签数据库接口
 * @author yy
 *
 */
@Mapper
public interface VideoSetTagDao {
	/** 新增视频集标签*/	
	public boolean addVideoSetTag(VideoSetTag videoSetTag) ;	
	/** 删除视频集标签 */	
	public boolean delVideoSetTag(Integer videoSetTagId) ;
	/** 更新视频集标签*/	
	public boolean updateVideoSetTag(VideoSetTag videoSetTag);
	/** 装载视频集标签 */	
	public VideoSetTag loadVideoSetTag(Integer videoSetTagId);	
	/** 视频集标签总共数目 */	
	public int countAll(
			@Param("videoSetId")Integer videoSetId
			);	
	/** 分页视频集标签信息 */
	public List<VideoSetTag> browsePagingVideoSetTag(
			@Param("videoSetId")Integer videoSetId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
