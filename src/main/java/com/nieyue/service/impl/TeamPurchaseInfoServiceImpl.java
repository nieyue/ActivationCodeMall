package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.TeamPurchaseInfo;
import com.nieyue.dao.TeamPurchaseInfoDao;
import com.nieyue.service.TeamPurchaseInfoService;
@Service
public class TeamPurchaseInfoServiceImpl implements TeamPurchaseInfoService{
	@Resource
	TeamPurchaseInfoDao teamPurchaseInfoDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addTeamPurchaseInfo(TeamPurchaseInfo teamPurchaseInfo) {
		teamPurchaseInfo.setCreateDate(new Date());
		teamPurchaseInfo.setUpdateDate(new Date());
		boolean b = teamPurchaseInfoDao.addTeamPurchaseInfo(teamPurchaseInfo);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delTeamPurchaseInfo(Integer teamPurchaseInfoId) {
		boolean b = teamPurchaseInfoDao.delTeamPurchaseInfo(teamPurchaseInfoId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateTeamPurchaseInfo(TeamPurchaseInfo teamPurchaseInfo) {
		teamPurchaseInfo.setUpdateDate(new Date());
		boolean b = teamPurchaseInfoDao.updateTeamPurchaseInfo(teamPurchaseInfo);
		return b;
	}

	@Override
	public TeamPurchaseInfo loadTeamPurchaseInfo(Integer teamPurchaseInfoId) {
		TeamPurchaseInfo r = teamPurchaseInfoDao.loadTeamPurchaseInfo(teamPurchaseInfoId);
		return r;
	}

	@Override
	public int countAll(
			Integer accountId,
			Date createDate,
			Date updateDate) {
		int c = teamPurchaseInfoDao.countAll(
				accountId,createDate,updateDate);
		return c;
	}

	@Override
	public List<TeamPurchaseInfo> browsePagingTeamPurchaseInfo(
			Integer accountId,
			Date createDate,
			Date updateDate,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<TeamPurchaseInfo> l = teamPurchaseInfoDao.browsePagingTeamPurchaseInfo(
				accountId,
				createDate,
				updateDate,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}

	
}
