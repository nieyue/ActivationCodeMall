package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.Notice;
import com.nieyue.business.NoticeBusiness;
import com.nieyue.dao.NoticeDao;
import com.nieyue.service.AccountService;
import com.nieyue.service.NoticeService;
@Service
public class NoticeServiceImpl implements NoticeService{
	@Resource
	NoticeDao noticeDao;
	@Resource
	AccountService accountService;
	@Resource
	NoticeBusiness noticeBusiness;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addNotice(Notice notice) {
		boolean b=false;
		notice.setCreateDate(new Date());
		notice.setUpdateDate(new Date());
		notice.setRegion(2);//默认都是个人
		if(notice.getType().equals(1)){
			notice.setRegion(1);//系统通知为全局
		}
		notice.setIsMerDynamic(noticeBusiness.getIsMerDynamicByType(notice.getType()));
		notice.setContent(noticeBusiness.getContentByType(notice));//json数据
		b = noticeDao.addNotice(notice);
		 if(b&&notice.getType().equals(1)){//系统通知
				//通知到所有人
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						List<Account> al = accountService.browsePagingAccount(null, null, null, null, null, null, null, null, null, 1, Integer.MAX_VALUE, "account_id", "asc");
						al.forEach((account)->{
							Integer aid = account.getAccountId();
							Notice n=new Notice();
							n.setContent(notice.getContent());
							n.setCreateDate(notice.getCreateDate());
							n.setUpdateDate(notice.getUpdateDate());
							n.setImgAddress(notice.getImgAddress());
							n.setRegion(2);//个人
							n.setType(1);//系统通知
							n.setStatus(0);//系统消息为0
							n.setIsMerDynamic(0);//0不是商品动态
							n.setContent(notice.getContent());//json数据
							n.setTitle(notice.getTitle());
							n.setAccountId(aid);//系统消息需要两个都有
							n.setReceiveAccountId(aid);//系统消息需要两个都有
							n.setBusinessId(notice.getNoticeId());//业务id为父id
							noticeDao.addNotice(n);
						});
					}
				}).start();
			}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delNotice(Integer noticeId) {
		Notice notice = noticeDao.loadNotice(noticeId);
		boolean b = noticeDao.delNotice(noticeId);
		if(b&&notice.getType().equals(1)){
			//通知到所有人
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					List<Account> al = accountService.browsePagingAccount(null, null, null, null, null, null, null, null, null, 1, Integer.MAX_VALUE, "account_id", "asc");
					al.forEach((account)->{
						Integer aid = account.getAccountId();
						List<Notice> nl = browsePagingNotice(2, 1, null, null, aid, aid, notice.getNoticeId(), 1, Integer.MAX_VALUE, "notice_id", "asc");
						if(nl.size()==1){
						Notice n = nl.get(0);
						delNotice(n.getNoticeId());
						}
					});
				}
			}).start();
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateNotice(Notice notice) {
		notice.setUpdateDate(new Date());
		boolean b = noticeDao.updateNotice(notice);
		if(b&&notice.getType().equals(1)){
			//通知到所有人
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					List<Account> al = accountService.browsePagingAccount(null, null, null, null, null, null, null, null, null, 1, Integer.MAX_VALUE, "account_id", "asc");
					al.forEach((account)->{
						Integer aid = account.getAccountId();
						List<Notice> nl = browsePagingNotice(2, 1, null, null, aid, aid, notice.getNoticeId(), 1, Integer.MAX_VALUE, "notice_id", "asc");
						if(nl.size()==1){
						Notice n = nl.get(0);
						n.setContent(notice.getContent());
						n.setUpdateDate(notice.getUpdateDate());
						n.setImgAddress(notice.getImgAddress());
						n.setRegion(2);//个人
						noticeDao.updateNotice(n);
						}
					});
				}
			}).start();
		}
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
			Integer isMerDynamic,
			Integer status,
			Integer accountId,
			Integer receiveAccountId,
			Integer businessId) {
		int c = noticeDao.countAll(
				region, 
				type,
				isMerDynamic,
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
			Integer isMerDynamic,
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
				isMerDynamic,
				status,
				accountId,
				receiveAccountId,
				businessId,
				pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
