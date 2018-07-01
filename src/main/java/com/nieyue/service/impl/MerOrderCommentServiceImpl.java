package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.Mer;
import com.nieyue.bean.MerOrderComment;
import com.nieyue.dao.MerOrderCommentDao;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.AccountService;
import com.nieyue.service.MerOrderCommentService;
import com.nieyue.service.MerService;
import com.nieyue.util.Arith;
@Service
public class MerOrderCommentServiceImpl implements MerOrderCommentService{
	@Resource
	AccountService accountService;
	@Resource
	MerService merService;
	@Resource
	MerOrderCommentDao merOrderCommentDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerOrderComment(MerOrderComment merOrderComment) {
		int count = this.countAll(null, null, merOrderComment.getOrderId(), merOrderComment.getAccountId());
		if(count>0){
			throw new CommonRollbackException("该商品订单已经评价过了!");
		}
		merOrderComment.setCreateDate(new Date());
		boolean b = merOrderCommentDao.addMerOrderComment(merOrderComment);
		if(b){
			Mer mer = merService.loadMer(merOrderComment.getMerId());
			if(mer==null){
				throw new CommonRollbackException("该商品不存在!");
			}
			if(mer.getMerScore().equals(0.0)){
				mer.setMerScore(merOrderComment.getMerScore());					
			}else{
				mer.setMerScore(Arith.div(Arith.add(merOrderComment.getMerScore(), mer.getMerScore()),2));					
			}
			mer.setMerCommentNumber(count+1);
			merService.updateMer(mer);
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerOrderComment(Integer merOrderCommentId) {
		MerOrderComment r = merOrderCommentDao.loadMerOrderComment(merOrderCommentId);
		boolean b = merOrderCommentDao.delMerOrderComment(merOrderCommentId);
		if(b){
			Mer mer = merService.loadMer(r.getMerId());
			if(mer==null){
				throw new CommonRollbackException("该商品不存在!");
			}
			if(mer.getMerScore().equals(0.0)){
				mer.setMerScore(r.getMerScore());					
			}else{
				mer.setMerScore(Arith.div(Arith.add(r.getMerScore(), mer.getMerScore()),2));					
			}
			mer.setMerCommentNumber(mer.getMerCommentNumber()-1);
			merService.updateMer(mer);
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerOrderComment(MerOrderComment merOrderComment) {
		boolean b = merOrderCommentDao.updateMerOrderComment(merOrderComment);
		if(b){
			Mer mer = merService.loadMer(merOrderComment.getMerId());
			if(mer==null){
				throw new CommonRollbackException("该商品不存在!");
			}
			if(mer.getMerScore().equals(0.0)){
				mer.setMerScore(merOrderComment.getMerScore());					
			}else{
				mer.setMerScore(Arith.div(Arith.add(merOrderComment.getMerScore(), mer.getMerScore()),2));					
			}
			merService.updateMer(mer);
		}
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
