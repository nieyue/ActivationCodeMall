package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.MerImg;
import com.nieyue.dao.MerImgDao;
import com.nieyue.service.MerImgService;
@Service
public class MerImgServiceImpl implements MerImgService{
	@Resource
	MerImgDao merImgDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerImg(MerImg merImg) {
		merImg.setCreateDate(new Date());
		merImg.setUpdateDate(new Date());
		boolean b = merImgDao.addMerImg(merImg);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerImg(Integer merImgId) {
		boolean b = merImgDao.delMerImg(merImgId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerImg(MerImg merImg) {
		merImg.setUpdateDate(new Date());
		boolean b = merImgDao.updateMerImg(merImg);
		return b;
	}

	@Override
	public MerImg loadMerImg(Integer merImgId) {
		MerImg r = merImgDao.loadMerImg(merImgId);
		return r;
	}

	@Override
	public int countAll(
			Integer merId) {
		int c = merImgDao.countAll(merId);
		return c;
	}

	@Override
	public List<MerImg> browsePagingMerImg(
			Integer merId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MerImg> l = merImgDao.browsePagingMerImg(merId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
