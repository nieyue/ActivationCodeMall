package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.MerOrderComment;
import com.nieyue.dao.MerOrderCommentDao;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.AccountService;
import com.nieyue.service.MerOrderCommentService;
@Service
public class MerOrderCommentServiceImpl implements MerOrderCommentService{
	@Resource
	AccountService accountService;
	@Resource
	MerOrderCommentDao merOrderCommentDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerOrderComment(MerOrderComment merOrderComment) {
		List<MerOrderComment> mocl = this.browsePagingMerOrderComment(null, null, merOrderComment.getOrderId(), merOrderComment.getAccountId(), 1, 1, "create_date", "asc");
		if(mocl.size()>0){
			throw new CommonRollbackException("该商品订单已经评价过了!");
		}
		merOrderComment.setCreateDate(new Date());
		boolean b = merOrderCommentDao.addMerOrderComment(merOrderComment);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerOrderComment(Integer merOrderCommentId) {
		boolean b = merOrderCommentDao.delMerOrderComment(merOrderCommentId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerOrderComment(MerOrderComment merOrderComment) {
		boolean b = merOrderCommentDao.updateMerOrderComment(merOrderComment);
		return b;
	}

	@Override
	public MerOrderComment loadMerOrderComment(Integer merOrderCommentId) {
		MerOrderComment r = merOrderCommentDao.loadMerOrderComment(merOrderCommentId);
		if(r.getAccountId()!=null){
			Account a = accountService.loadAccount(r.getAccountId());	
			r.setAccount(a);
		}
		return r;
	}

	@Override
	public int countAll(
			Double merScore,
			Integer merId,
			Integer orderId,
			Integer accountId
			) {
		int c = merOrderCommentDao.countAll(merScore,merId,orderId,accountId);
		return c;
	}

	@Override
	public List<MerOrderComment> browsePagingMerOrderComment(
			Double merScore,
			Integer merId,
			Integer orderId,
			Integer accountId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MerOrderComment> l = merOrderCommentDao.browsePagingMerOrderComment(merScore,merId,orderId,accountId,pageNum-1, pageSize, orderName, orderWay);
		
		l.forEach((moc)->{
			if(moc.getAccountId()!=null){
				Account a = accountService.loadAccount(moc.getAccountId());	
				moc.setAccount(a);
			}
		});
		return l;
	}

	
}
