package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.AppVersion;
import com.nieyue.dao.AppVersionDao;
import com.nieyue.service.AppVersionService;

@Service
public class AppVersionServiceImpl implements AppVersionService{
	@Resource
	AppVersionDao appVersionDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addAppVersion(AppVersion appVersion) {
		appVersion.setUpdateDate(new Date());
		boolean b = appVersionDao.addAppVersion(appVersion);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delAppVersion(Integer appVersionId) {
		boolean b = appVersionDao.delAppVersion(appVersionId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateAppVersion(AppVersion appVersion) {
		appVersion.setUpdateDate(new Date());
		boolean b = appVersionDao.updateAppVersion(appVersion);
		return b;
	}

	@Override
	public AppVersion loadAppVersion(Integer appVersionId) {
		AppVersion r = appVersionDao.loadAppVersion(appVersionId);
		return r;
	}

	@Override
	public int countAll(Integer platform,Integer status) {
		int c = appVersionDao.countAll( platform, status);
		return c;
	}

	@Override
	public List<AppVersion> browsePagingAppVersion(Integer platform,Integer status,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AppVersion> l = appVersionDao.browsePagingAppVersion( platform, status,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
