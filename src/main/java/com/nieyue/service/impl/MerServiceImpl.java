package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Mer;
import com.nieyue.dao.MerDao;
import com.nieyue.service.MerService;
@Service
public class MerServiceImpl implements MerService{
	@Resource
	MerDao merDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMer(Mer mer) {
		mer.setCreateDate(new Date());
		mer.setUpdateDate(new Date());
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
		return l;
	}

	
}
