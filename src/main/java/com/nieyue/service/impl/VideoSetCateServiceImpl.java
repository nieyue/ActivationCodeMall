package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.VideoSetCate;
import com.nieyue.dao.VideoSetCateDao;
import com.nieyue.service.VideoSetCateService;
@Service
public class VideoSetCateServiceImpl implements VideoSetCateService{
	@Resource
	VideoSetCateDao videoSetCateDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVideoSetCate(VideoSetCate videoSetCate) {
		videoSetCate.setUpdateDate(new Date());
		videoSetCate.setPlayNumber(0);
		boolean b = videoSetCateDao.addVideoSetCate(videoSetCate);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVideoSetCate(Integer videoSetCateId) {
		boolean b = videoSetCateDao.delVideoSetCate(videoSetCateId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVideoSetCate(VideoSetCate videoSetCate) {
		videoSetCate.setUpdateDate(new Date());
		boolean b = videoSetCateDao.updateVideoSetCate(videoSetCate);
		return b;
	}

	@Override
	public VideoSetCate loadVideoSetCate(Integer videoSetCateId) {
		VideoSetCate r = videoSetCateDao.loadVideoSetCate(videoSetCateId);
		return r;
	}

	@Override
	public int countAll(
			) {
		int c = videoSetCateDao.countAll();
		return c;
	}

	@Override
	public List<VideoSetCate> browsePagingVideoSetCate(
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<VideoSetCate> l = videoSetCateDao.browsePagingVideoSetCate(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
