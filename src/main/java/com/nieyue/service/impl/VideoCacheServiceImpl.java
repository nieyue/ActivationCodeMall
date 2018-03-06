package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.VideoCache;
import com.nieyue.dao.VideoCacheDao;
import com.nieyue.service.VideoCacheService;
@Service
public class VideoCacheServiceImpl implements VideoCacheService{
	@Resource
	VideoCacheDao videoCacheDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVideoCache(VideoCache videoCache) {
		videoCache.setCreateDate(new Date());
		boolean b = videoCacheDao.addVideoCache(videoCache);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVideoCache(Integer videoCacheId) {
		boolean b = videoCacheDao.delVideoCache(videoCacheId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVideoCache(VideoCache videoCache) {
		boolean b = videoCacheDao.updateVideoCache(videoCache);
		return b;
	}

	@Override
	public VideoCache loadVideoCache(Integer videoCacheId) {
		VideoCache r = videoCacheDao.loadVideoCache(videoCacheId);
		return r;
	}

	@Override
	public int countAll(
			Integer videoId,
			Integer accountId) {
		int c = videoCacheDao.countAll(
				videoId,accountId);
		return c;
	}

	@Override
	public List<VideoCache> browsePagingVideoCache(
			Integer videoId,
			Integer accountId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<VideoCache> l = videoCacheDao.browsePagingVideoCache(
				videoId,
				accountId,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}

	
}
