package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.SpreadLinkTerm;
import com.nieyue.dao.SpreadLinkTermDao;
import com.nieyue.service.SpreadLinkTermService;
@Service
public class SpreadLinkTermServiceImpl implements SpreadLinkTermService{
	@Resource
	SpreadLinkTermDao spreadLinkTermDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addSpreadLinkTerm(SpreadLinkTerm spreadLinkTerm) {
		spreadLinkTerm.setCreateDate(new Date());
		boolean b = spreadLinkTermDao.addSpreadLinkTerm(spreadLinkTerm);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delSpreadLinkTerm(Integer spreadLinkTermId) {
		boolean b = spreadLinkTermDao.delSpreadLinkTerm(spreadLinkTermId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateSpreadLinkTerm(SpreadLinkTerm spreadLinkTerm) {
		boolean b = spreadLinkTermDao.updateSpreadLinkTerm(spreadLinkTerm);
		return b;
	}

	@Override
	public SpreadLinkTerm loadSpreadLinkTerm(Integer spreadLinkTermId) {
		SpreadLinkTerm r = spreadLinkTermDao.loadSpreadLinkTerm(spreadLinkTermId);
		return r;
	}

	@Override
	public int countAll() {
		int c = spreadLinkTermDao.countAll();
		return c;
	}

	@Override
	public List<SpreadLinkTerm> browsePagingSpreadLinkTerm(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<SpreadLinkTerm> l = spreadLinkTermDao.browsePagingSpreadLinkTerm(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
