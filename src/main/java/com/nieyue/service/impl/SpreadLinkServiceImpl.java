package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.SpreadLink;
import com.nieyue.dao.SpreadLinkDao;
import com.nieyue.service.SpreadLinkService;
@Service
public class SpreadLinkServiceImpl implements SpreadLinkService{
	@Resource
	SpreadLinkDao spreadLinkDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addSpreadLink(SpreadLink spreadLink) {
		List<SpreadLink> skl = this.browsePagingSpreadLink(spreadLink.getMerId(), spreadLink.getAccountId(), 1, 1, "spread_link_id", "asc");
		if(skl.size()>0){
			return true;
		}
		spreadLink.setCreateDate(new Date());
		
		boolean b = spreadLinkDao.addSpreadLink(spreadLink);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delSpreadLink(Integer spreadLinkId) {
		boolean b = spreadLinkDao.delSpreadLink(spreadLinkId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateSpreadLink(SpreadLink spreadLink) {
		boolean b = spreadLinkDao.updateSpreadLink(spreadLink);
		return b;
	}

	@Override
	public SpreadLink loadSpreadLink(Integer spreadLinkId) {
		SpreadLink r = spreadLinkDao.loadSpreadLink(spreadLinkId);
		return r;
	}

	@Override
	public int countAll(Integer merId,Integer accountId) {
		int c = spreadLinkDao.countAll( merId,accountId);
		return c;
	}

	@Override
	public List<SpreadLink> browsePagingSpreadLink(Integer merId,Integer accountId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<SpreadLink> l = spreadLinkDao.browsePagingSpreadLink( merId,accountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
