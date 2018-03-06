package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.VideoSet;

/**
 * 视频集数据库接口
 * @author yy
 *
 */
@Mapper
public interface VideoSetDao {
	/** 新增视频集*/	
	public boolean addVideoSet(VideoSet videoSet) ;	
	/** 删除视频集 */	
	public boolean delVideoSet(Integer videoSetId) ;
	/** 更新视频集*/	
	public boolean updateVideoSet(VideoSet videoSet);
	/** 装载视频集 */	
	public VideoSet loadVideoSet(Integer videoSetId);	
	/** 观看视频集*/	
	public boolean watchVideoSet(@Param("videoSetId")Integer videoSetId);	
	/** 视频集总共数目 */	
	public int countAll(
			@Param("name")String name,
			@Param("recommend")Integer recommend,
			@Param("cost")Integer cost,
			@Param("videoSetCateId")Integer videoSetCateId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页视频集信息 */
	public List<VideoSet> browsePagingVideoSet(
			@Param("name")String name,
			@Param("recommend")Integer recommend,
			@Param("cost")Integer cost,
			@Param("videoSetCateId")Integer videoSetCateId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
