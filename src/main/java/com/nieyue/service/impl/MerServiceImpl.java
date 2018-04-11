package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Mer;
import com.nieyue.bean.MerCate;
import com.nieyue.bean.MerImg;
import com.nieyue.dao.MerDao;
import com.nieyue.service.MerCateService;
import com.nieyue.service.MerImgService;
import com.nieyue.service.MerService;
@Service
public class MerServiceImpl implements MerService{
	@Resource
	MerDao merDao;
	@Resource
	MerImgService merImgService;
	@Resource
	MerCateService merCateService;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMer(Mer mer) {
		mer.setCreateDate(new Date());
		mer.setUpdateDate(new Date());
		mer.setSaleNumber(0);
		mer.setStockNumber(0);
		mer.setMerScore(0.0);
		mer.setMerCommentNumber(0);
		boolean b = merDao.addMer(mer);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMer(Integer merId) {
		boolean b = merDao.delMer(merId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMer(Mer mer) {
		mer.setUpdateDate(new Date());
		boolean b = merDao.updateMer(mer);
		return b;
	}

	@Override
	public Mer loadMer(Integer merId) {
		Mer r = merDao.loadMer(merId);
		MerCate merCate=merCateService.loadMerCate(r.getMerCateId());
		r.setMerCate(merCate);
		List<MerImg> mil=merImgService.browsePagingMerImg(merId, 1, Integer.MAX_VALUE, "mer_img_id", "asc");
		r.setMerImgList(mil);
		return r;
	}

	@Override
	public int countAll(
			Integer region,
			Integer type,
			String name,
			Integer recommend,
			Double unitPrice,
			Integer saleNumber,
			Double merScore,
			Integer merCateId,
			Integer sellerAccountId,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = merDao.countAll(
				region,
				type,
				name,
				recommend,
				unitPrice,
				saleNumber,
				merScore,
				merCateId,
				sellerAccountId,
				createDate,
				updateDate,
				status);
		return c;
	}

	@Override
	public List<Mer> browsePagingMer(
			Integer region,
			Integer type,
			String name,
			Integer recommend,
			Double unitPrice,
			Integer saleNumber,
			Double merScore,
			Integer merCateId,
			Integer sellerAccountId,
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
		List<Mer> l = merDao.browsePagingMer(
				region,
				type,
				name,
				recommend,
				unitPrice,
				saleNumber,
				merScore,
				merCateId,
				sellerAccountId,
				createDate,
				updateDate,
				status,
				pageNum-1, pageSize, orderName, orderWay);
		l.forEach((m)->{
			MerCate merCate=merCateService.loadMerCate(m.getMerCateId());
			m.setMerCate(merCate);
			List<MerImg> mil=merImgService.browsePagingMerImg(m.getMerId(), 1, Integer.MAX_VALUE, "mer_img_id", "asc");
			m.setMerImgList(mil);
		});
		return l;
	}

	
}
