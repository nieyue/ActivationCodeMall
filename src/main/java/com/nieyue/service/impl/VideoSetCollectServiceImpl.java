package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.VideoSetCollect;
import com.nieyue.dao.VideoSetCollectDao;
import com.nieyue.service.VideoSetCollectService;
@Service
public class VideoSetCollectServiceImpl implements VideoSetCollectService{
	@Resource
	VideoSetCollectDao videoSetCollectDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVideoSetCollect(VideoSetCollect videoSetCollect) {
		videoSetCollect.setCreateDate(new Date());
		boolean b = videoSetCollectDao.addVideoSetCollect(videoSetCollect);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVideoSetCollect(Integer videoSetCollectId) {
		boolean b = videoSetCollectDao.delVideoSetCollect(videoSetCollectId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVideoSetCollect(VideoSetCollect videoSetCollect) {
		boolean b = videoSetCollectDao.updateVideoSetCollect(videoSetCollect);
		return b;
	}

	@Override
	public VideoSetCollect loadVideoSetCollect(Integer videoSetCollectId) {
		VideoSetCollect r = videoSetCollectDao.loadVideoSetCollect(videoSetCollectId);
		return r;
	}

	@Override
	public int countAll(
			Integer videoSetId,
			Integer accountId) {
		int c = videoSetCollectDao.countAll(
				videoSetId,accountId);
		return c;
	}

	@Override
	public List<VideoSetCollect> browsePagingVideoSetCollect(
			Integer videoSetId,
			Integer accountId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<VideoSetCollect> l = videoSetCollectDao.browsePagingVideoSetCollect(
				videoSetId,accountId,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}

	
}
