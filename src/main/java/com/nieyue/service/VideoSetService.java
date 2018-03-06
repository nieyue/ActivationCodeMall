package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.VideoSet;

/**
 * 视频集逻辑层接口
 * @author yy
 *
 */
public interface VideoSetService {
	/** 新增视频集 */	
	public boolean addVideoSet(VideoSet videoSet) ;	
	/** 删除视频集 */	
	public boolean delVideoSet(Integer videoSetId) ;
	/** 更新视频集*/	
	public boolean updateVideoSet(VideoSet videoSet);
	/** 装载视频集 */	
	public VideoSet loadVideoSet(Integer videoSetId);
	/** 观看视频集 */	
	public boolean watchVideoSet(Integer videoSetId,Integer accountId);
	/** 视频集总共数目 */	
	public int countAll(
			String name,
			Integer recommend,
			Integer cost,
			Integer videoSetCateId,
			Date createDate,
			Date updateDate,
			Integer status
			);
	/** 分页视频集信息 */
	public List<VideoSet> browsePagingVideoSet(
			String name,
			Integer recommend,
			Integer cost,
			Integer videoSetCateId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
