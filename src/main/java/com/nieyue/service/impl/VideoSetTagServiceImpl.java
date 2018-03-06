package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.VideoSetTag;
import com.nieyue.dao.VideoSetTagDao;
import com.nieyue.service.VideoSetTagService;
@Service
public class VideoSetTagServiceImpl implements VideoSetTagService{
	@Resource
	VideoSetTagDao videoSetTagDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVideoSetTag(VideoSetTag videoSetTag) {
		videoSetTag.setUpdateDate(new Date());
		boolean b = videoSetTagDao.addVideoSetTag(videoSetTag);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVideoSetTag(Integer videoSetTagId) {
		boolean b = videoSetTagDao.delVideoSetTag(videoSetTagId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVideoSetTag(VideoSetTag videoSetTag) {
		videoSetTag.setUpdateDate(new Date());
		boolean b = videoSetTagDao.updateVideoSetTag(videoSetTag);
		return b;
	}

	@Override
	public VideoSetTag loadVideoSetTag(Integer videoSetTagId) {
		VideoSetTag r = videoSetTagDao.loadVideoSetTag(videoSetTagId);
		return r;
	}

	@Override
	public int countAll(
			Integer videoSetId
			) {
		int c = videoSetTagDao.countAll(videoSetId);
		return c;
	}

	@Override
	public List<VideoSetTag> browsePagingVideoSetTag(
			Integer videoSetId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<VideoSetTag> l = videoSetTagDao.browsePagingVideoSetTag(videoSetId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
