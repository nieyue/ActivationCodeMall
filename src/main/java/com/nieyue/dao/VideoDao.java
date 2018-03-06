package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Video;

/**
 * 视频数据库接口
 * @author yy
 *
 */
@Mapper
public interface VideoDao {
	/** 新增视频*/	
	public boolean addVideo(Video video) ;	
	/** 删除视频 */	
	public boolean delVideo(Integer videoId) ;
	/** 更新视频*/	
	public boolean updateVideo(Video video);
	/** 装载视频 */	
	public Video loadVideo(Integer videoId);	
	/** 观看视频 */	
	public boolean watchVideo(@Param("videoId")Integer videoId);	
	/** 视频总共数目 */	
	public int countAll(
			@Param("videoSetId")Integer videoSetId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页视频信息 */
	public List<Video> browsePagingVideo(
			@Param("videoSetId")Integer videoSetId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
