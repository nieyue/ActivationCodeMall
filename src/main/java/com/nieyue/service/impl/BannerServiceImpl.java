package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Banner;
import com.nieyue.dao.BannerDao;
import com.nieyue.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService{
	@Resource
	BannerDao bannerDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addBanner(Banner banner) {
		banner.setUpdateDate(new Date());
		boolean b = bannerDao.addBanner(banner);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delBanner(Integer bannerId) {
		boolean b = bannerDao.delBanner(bannerId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateBanner(Banner banner) {
		banner.setUpdateDate(new Date());
		boolean b = bannerDao.updateBanner(banner);
		return b;
	}

	@Override
	public Banner loadBanner(Integer bannerId) {
		Banner r = bannerDao.loadBanner(bannerId);
		return r;
	}

	@Override
	public int countAll(Integer type,Integer status) {
		int c = bannerDao.countAll( type, status);
		return c;
	}

	@Override
	public List<Banner> browsePagingBanner(Integer type,Integer status,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Banner> l = bannerDao.browsePagingBanner( type, status,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
