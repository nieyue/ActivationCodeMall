package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Config;
import com.nieyue.dao.ConfigDao;
import com.nieyue.service.ConfigService;
@Service
public class ConfigServiceImpl implements ConfigService{
	@Resource
	ConfigDao configDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addConfig(Config config) {
		config.setCreateDate(new Date());
		config.setUpdateDate(new Date());
		boolean b = configDao.addConfig(config);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delConfig(Integer configId) {
		boolean b = configDao.delConfig(configId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateConfig(Config config) {
		config.setUpdateDate(new Date());
		boolean b = configDao.updateConfig(config);
		return b;
	}

	@Override
	public Config loadConfig(Integer configId) {
		Config r = configDao.loadConfig(configId);
		return r;
	}

	@Override
	public int countAll(
			) {
		int c = configDao.countAll();
		return c;
	}

	@Override
	public List<Config> browsePagingConfig(
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Config> l = configDao.browsePagingConfig(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
