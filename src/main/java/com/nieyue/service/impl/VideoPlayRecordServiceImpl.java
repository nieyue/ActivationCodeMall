package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.VideoPlayRecord;
import com.nieyue.dao.VideoPlayRecordDao;
import com.nieyue.service.VideoPlayRecordService;
@Service
public class VideoPlayRecordServiceImpl implements VideoPlayRecordService{
	@Resource
	VideoPlayRecordDao videoPlayRecordDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVideoPlayRecord(VideoPlayRecord videoPlayRecord) {
		videoPlayRecord.setCreateDate(new Date());
		List<VideoPlayRecord> vprl = videoPlayRecordDao.browsePagingVideoPlayRecord(videoPlayRecord.getVideoId(), videoPlayRecord.getAccountId(), 0, 1, "video_play_record_id", "asc");
		boolean b=false;
		if(vprl.size()==0){
			b = videoPlayRecordDao.addVideoPlayRecord(videoPlayRecord);			
		}else{
			b=videoPlayRecordDao.updateVideoPlayRecord(vprl.get(0));
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVideoPlayRecord(Integer videoPlayRecordId) {
		boolean b = videoPlayRecordDao.delVideoPlayRecord(videoPlayRecordId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVideoPlayRecord(VideoPlayRecord videoPlayRecord) {
		boolean b = videoPlayRecordDao.updateVideoPlayRecord(videoPlayRecord);
		return b;
	}

	@Override
	public VideoPlayRecord loadVideoPlayRecord(Integer videoPlayRecordId) {
		VideoPlayRecord r = videoPlayRecordDao.loadVideoPlayRecord(videoPlayRecordId);
		return r;
	}

	@Override
	public int countAll(
			Integer videoId,
			Integer accountId) {
		int c = videoPlayRecordDao.countAll(
				videoId,accountId);
		return c;
	}

	@Override
	public List<VideoPlayRecord> browsePagingVideoPlayRecord(
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
		List<VideoPlayRecord> l = videoPlayRecordDao.browsePagingVideoPlayRecord(
				videoId,
				accountId,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}

	
}
