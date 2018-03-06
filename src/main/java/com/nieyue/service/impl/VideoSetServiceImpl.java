package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Video;
import com.nieyue.bean.VideoSet;
import com.nieyue.bean.VideoSetTag;
import com.nieyue.dao.VideoSetDao;
import com.nieyue.service.VideoService;
import com.nieyue.service.VideoSetService;
import com.nieyue.service.VideoSetTagService;
@Service
public class VideoSetServiceImpl implements VideoSetService{
	@Resource
	VideoSetDao videoSetDao;
	@Resource
	VideoService videoService;
	@Resource
	VideoSetTagService videoSetTagService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVideoSet(VideoSet videoSet) {
		videoSet.setCreateDate(new Date());
		videoSet.setUpdateDate(new Date());
		if(videoSet.getRecommend()==null){
			videoSet.setRecommend(0);
		}
		if(videoSet.getCost()==null){
			videoSet.setCost(0);
		}
			videoSet.setNumber(0);
			if(videoSet.getStatus()==null){
				videoSet.setStatus(1);
			}
			if(videoSet.getPlayNumber()==null){
				videoSet.setPlayNumber(0);
			}
			if(videoSet.getTotalPrice()==null){
				videoSet.setTotalPrice(0.0);
			}
		boolean b = videoSetDao.addVideoSet(videoSet);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVideoSet(Integer videoSetId) {
		boolean b = videoSetDao.delVideoSet(videoSetId);
		List<Video> videolist = videoService.browsePagingVideo(videoSetId, null, null, null, 1, Integer.MAX_VALUE, "video_id", "asc");
		videolist.forEach((video)->{
			videoService.delVideo(video.getVideoId());
		});
		List<VideoSetTag> vstl = videoSetTagService.browsePagingVideoSetTag(videoSetId,1, Integer.MAX_VALUE, "video_set_tag_id", "asc");
		vstl.forEach((videoSetTag)->{
			videoSetTagService.delVideoSetTag(videoSetTag.getVideoSetTagId());
		});
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVideoSet(VideoSet videoSet) {
		videoSet.setUpdateDate(new Date());
		boolean b = videoSetDao.updateVideoSet(videoSet);
		return b;
	}

	@Override
	public VideoSet loadVideoSet(Integer videoSetId) {
		VideoSet r = videoSetDao.loadVideoSet(videoSetId);
		List<Video> videolist = videoService.browsePagingVideo(videoSetId, null, null, null, 1, Integer.MAX_VALUE, "video_id", "asc");
		r.setVideoList(videolist);
		List<VideoSetTag> vstl = videoSetTagService.browsePagingVideoSetTag(videoSetId,1, Integer.MAX_VALUE, "video_set_tag_id", "asc");
		r.setVideoSetTagList(vstl);
		return r;
	}

	@Override
	public int countAll(
			String name,
			Integer recommend,
			Integer cost,
			Integer videoSetCateId,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = videoSetDao.countAll(
				name,
				recommend,cost,videoSetCateId,createDate,updateDate,status);
		return c;
	}

	@Override
	public List<VideoSet> browsePagingVideoSet(
			String name,
			Integer recommend,
			Integer cost,
			Integer videoSetCateId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<VideoSet> l = videoSetDao.browsePagingVideoSet(
				 name,
				recommend,
				cost,
				videoSetCateId,
				createDate,
				updateDate,
				status,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		for (int i = 0; i < l.size(); i++) {
			List<VideoSetTag> vstl = videoSetTagService.browsePagingVideoSetTag(l.get(i).getVideoSetId(),1, Integer.MAX_VALUE, "video_set_tag_id", "asc");
			l.get(i).setVideoSetTagList(vstl);
		}
		return l;
	}
	@Override
	public boolean watchVideoSet(Integer videoSetId, Integer accountId) {
		boolean b = videoSetDao.watchVideoSet(videoSetId);
		return b;
	}

	
}
