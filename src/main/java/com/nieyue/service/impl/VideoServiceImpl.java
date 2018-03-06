package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.AccountParent;
import com.nieyue.bean.Integral;
import com.nieyue.bean.IntegralBoard;
import com.nieyue.bean.IntegralDetail;
import com.nieyue.bean.Video;
import com.nieyue.bean.VideoSet;
import com.nieyue.bean.VideoSetCate;
import com.nieyue.dao.VideoDao;
import com.nieyue.service.AccountParentService;
import com.nieyue.service.AccountService;
import com.nieyue.service.IntegralBoardService;
import com.nieyue.service.IntegralDetailService;
import com.nieyue.service.IntegralService;
import com.nieyue.service.VideoService;
import com.nieyue.service.VideoSetCateService;
import com.nieyue.service.VideoSetService;
import com.nieyue.util.DateUtil;
@Service
public class VideoServiceImpl implements VideoService{
	@Resource
	VideoDao videoDao;
	@Resource
	VideoSetService videoSetService;
	@Resource
	VideoSetCateService videoSetCateService;
	@Resource
	IntegralService integralService;
	@Resource
	IntegralDetailService integralDetailService;
	@Resource
	IntegralBoardService integralBoardService;
	@Resource
	AccountParentService accountParentService;
	@Resource
	AccountService accountService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addVideo(Video video) {
		video.setCreateDate(new Date());
		video.setUpdateDate(new Date());
		if(video.getStatus()==null){
			video.setStatus(1);
		}
		video.setPlayNumber(0);
		boolean b = videoDao.addVideo(video);
		if(video.getVideoSetId()!=null){
			VideoSet videoSet = videoSetService.loadVideoSet(video.getVideoSetId());
			int vc = videoDao.countAll(video.getVideoSetId(), null, null, null);
			videoSet.setNumber(vc);
			b=videoSetService.updateVideoSet(videoSet);
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delVideo(Integer videoId) {
		Video video = videoDao.loadVideo(videoId);
		boolean b = videoDao.delVideo(videoId);
		if(b==true &&video.getVideoSetId()!=null){
			VideoSet videoSet = videoSetService.loadVideoSet(video.getVideoSetId());
			int vc = videoDao.countAll(video.getVideoSetId(), null, null, null);
			videoSet.setNumber(vc);
			b=videoSetService.updateVideoSet(videoSet);
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateVideo(Video video) {
		video.setUpdateDate(new Date());
		boolean b = videoDao.updateVideo(video);
		return b;
	}

	@Override
	public Video loadVideo(Integer videoId) {
		Video r = videoDao.loadVideo(videoId);
		return r;
	}

	@Override
	public int countAll(
			Integer videoSetId,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = videoDao.countAll(
				videoSetId,createDate,updateDate,status);
		return c;
	}

	@Override
	public List<Video> browsePagingVideo(
			Integer videoSetId,
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
		List<Video> l = videoDao.browsePagingVideo(
				videoSetId,
				createDate,
				updateDate,
				status,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}
	@Override
	public boolean watchVideo(Integer videoId, Integer accountId,Integer type) {
		boolean b=false;
		Video video = videoDao.loadVideo(videoId);
		if(type==1){//开始播放
		//增加视频播放次数
		b = videoDao.watchVideo(videoId);
		if(b){
		//增加视频集播放次数	
		b=videoSetService.watchVideoSet(video.getVideoSetId(), accountId);
		//增加视频集类型播放次数
		VideoSet videoSet = videoSetService.loadVideoSet(video.getVideoSetId());
		VideoSetCate videoSetCate = videoSetCateService.loadVideoSetCate(videoSet.getVideoSetCateId());
		videoSetCate.setPlayNumber(videoSetCate.getPlayNumber()+1);
		videoSetCateService.updateVideoSetCate(videoSetCate);
		}
		}else if(type==2){//播放中
			b=true;
		}
		
		if(b==true &&video.getVideoSetId()!=null){
			Double iiii=1.0;//获得积分
			//积分增加
			List<Integral> integrall = integralService.browsePagingIntegral(accountId, null, null, 1, 1, "integral_id", "asc");
			Integral integral = integrall.get(0);
			integral.setIntegral(integral.getIntegral()+iiii);
			b=integralService.updateIntegral(integral);
			//积分详情增加
			IntegralDetail integralDetail=new IntegralDetail();
			integralDetail.setAccountId(accountId);
			integralDetail.setIntegral(iiii);
			integralDetail.setType(1);//获得
			b=integralDetailService.addIntegralDetail(integralDetail);
			//获取个人信息
			Account a = accountService.loadAccount(accountId);
			//个人积分榜 周    格式（每周第一天）：2018-2-5 0:00:00
			IntegralBoard zib=new IntegralBoard();
			zib.setType(1);//1是个人
			zib.setTimeType(1);//1是周
			zib.setRealname(a.getRealname());
			zib.setIcon(a.getIcon());
			//zib.setIntegral(iiii);
			zib.setRecordTime(DateUtil.getStartTime(DateUtil.getFirstDayOfWeek()));
			zib.setCreateDate(new Date());
			zib.setUpdateDate(new Date());
			zib.setAccountId(accountId);
			b=integralBoardService.saveOrUpdateIntegralBoard(zib, iiii);
			
			//个人积分榜 月  格式（每月第一天）：2018-2-1 0:00:00
			IntegralBoard yib=new IntegralBoard();
			yib.setType(1);//1是个人
			yib.setTimeType(2);//2是月
			yib.setRealname(a.getRealname());
			yib.setIcon(a.getIcon());
			//yib.setIntegral(iiii);
			yib.setRecordTime(DateUtil.getStartTime(DateUtil.getFirstDayOfMonth()));
			yib.setCreateDate(new Date());
			yib.setUpdateDate(new Date());
			yib.setAccountId(accountId);
			b=integralBoardService.saveOrUpdateIntegralBoard(yib, iiii);
			
			//个人积分榜 总榜 记录时间为2018-1-1 0:0:0
			IntegralBoard gzib=new IntegralBoard();
			gzib.setType(1);//1是个人
			gzib.setTimeType(3);//3是总
			gzib.setRealname(a.getRealname());
			gzib.setIcon(a.getIcon());
			//gzib.setIntegral(iiii);
			gzib.setRecordTime(DateUtil.getStartTime(new Date("2018/1/1 0:0:0")));
			gzib.setCreateDate(new Date());
			gzib.setUpdateDate(new Date());
			gzib.setAccountId(accountId);
			b=integralBoardService.saveOrUpdateIntegralBoard(gzib, iiii);
			
			//团队总榜，只记录自身和直接下级的，记录时间为2018-1-1 0:0:0
			//自身的增加，
			IntegralBoard tzib=new IntegralBoard();
			tzib.setType(2);//1是团队
			tzib.setTimeType(3);//3是总
			tzib.setRealname(a.getRealname());
			tzib.setIcon(a.getIcon());
			//tzib.setIntegral(iiii);
			tzib.setRecordTime(DateUtil.getStartTime(new Date("2018/1/1 0:0:0")));
			tzib.setCreateDate(new Date());
			tzib.setUpdateDate(new Date());
			tzib.setAccountId(accountId);
			b=integralBoardService.saveOrUpdateIntegralBoard(tzib, iiii);
			//上级的增加
			List<AccountParent> accountParentl = accountParentService.browsePagingAccountParent(null, null, accountId, null, null, null, null, 1, 1, "account_parent_id", "asc");
			AccountParent accountParent = accountParentl.get(0);
			Account master= accountService.loadAccount(accountParent.getMasterId());//直接上级有积分
			IntegralBoard stzib=new IntegralBoard();
			stzib.setType(2);//1是团队
			stzib.setTimeType(2);//3是总
			stzib.setRealname(a.getRealname());
			stzib.setIcon(a.getIcon());
			//tzib.setIntegral(iiii);
			stzib.setRecordTime(DateUtil.getStartTime(new Date("2018/1/1 0:0:0")));
			stzib.setCreateDate(new Date());
			stzib.setUpdateDate(new Date());
			stzib.setAccountId(master.getAccountId());
			b=integralBoardService.saveOrUpdateIntegralBoard(stzib, iiii);
		}
		return b;
	}

	
}
