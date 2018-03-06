package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.VideoSetCate;

/**
 * 视频集类型数据库接口
 * @author yy
 *
 */
@Mapper
public interface VideoSetCateDao {
	/** 新增视频集类型*/	
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
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
