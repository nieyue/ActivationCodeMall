package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.VideoSetSearch;
import com.nieyue.dao.VideoSetSearchDao;
import com.nieyue.service.VideoSetSearchService;
@Service
public class VideoSetSearchServiceImpl implements VideoSetSearchService{
	@Resource
	VideoSetSearchDao videoSetSearchDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVideoSetSearch(VideoSetSearch videoSetSearch) {
		videoSetSearch.setUpdateDate(new Date());
		boolean b = videoSetSearchDao.addVideoSetSearch(videoSetSearch);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVideoSetSearch(Integer videoSetSearchId) {
		boolean b = videoSetSearchDao.delVideoSetSearch(videoSetSearchId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVideoSetSearch(VideoSetSearch videoSetSearch) {
		videoSetSearch.setUpdateDate(new Date());
		boolean b = videoSetSearchDao.updateVideoSetSearch(videoSetSearch);
		return b;
	}

	@Override
	public VideoSetSearch loadVideoSetSearch(Integer videoSetSearchId) {
		VideoSetSearch r = videoSetSearchDao.loadVideoSetSearch(videoSetSearchId);
		return r;
	}

	@Override
	public int countAll(
			String name,
			Integer number
			) {
		int c = videoSetSearchDao.countAll(name,number);
		return c;
	}

	@Override
	public List<VideoSetSearch> browsePagingVideoSetSearch(
			String name,
			Integer number,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<VideoSetSearch> l = videoSetSearchDao.browsePagingVideoSetSearch(name,number,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
