package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Notice;
import com.nieyue.dao.NoticeDao;
import com.nieyue.service.NoticeService;
@Service
public class NoticeServiceImpl implements NoticeService{
	@Resource
	NoticeDao noticeDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addNotice(Notice notice) {
		notice.setCreateDate(new Date());
		notice.setUpdateDate(new Date());
		boolean b = noticeDao.addNotice(notice);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delNotice(Integer noticeId) {
		boolean b = noticeDao.delNotice(noticeId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateNotice(Notice notice) {
		notice.setUpdateDate(new Date());
		boolean b = noticeDao.updateNotice(notice);
		return b;
	}

	@Override
	public Notice loadNotice(Integer noticeId) {
		Notice r = noticeDao.loadNotice(noticeId);
		return r;
	}

	@Override
	public int countAll(
			Integer region,
			Integer type,
			Integer status,
			Integer accountId,
			Integer receiveAccountId,
			Integer businessId) {
		int c = noticeDao.countAll(
				region, 
				type,
				status,
				accountId,
				receiveAccountId,
				businessId);
		return c;
	}

	@Override
	public List<Notice> browsePagingNotice(
			Integer region,
			Integer type,
			Integer status,
			Integer accountId,
			Integer receiveAccountId,
			Integer businessId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Notice> l = noticeDao.browsePagingNotice(
				region, 
				type,
				status,
				accountId,
				receiveAccountId,
				businessId,
				pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
