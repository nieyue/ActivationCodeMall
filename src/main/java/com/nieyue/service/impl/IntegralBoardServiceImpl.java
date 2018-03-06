package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.IntegralBoard;
import com.nieyue.dao.IntegralBoardDao;
import com.nieyue.service.IntegralBoardService;
@Service
public class IntegralBoardServiceImpl implements IntegralBoardService{
	@Resource
	IntegralBoardDao integralBoardDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addIntegralBoard(IntegralBoard integralBoard) {
		integralBoard.setCreateDate(new Date());
		integralBoard.setUpdateDate(new Date());
		boolean b = integralBoardDao.addIntegralBoard(integralBoard);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delIntegralBoard(Integer integralBoardId) {
		boolean b = integralBoardDao.delIntegralBoard(integralBoardId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateIntegralBoard(IntegralBoard integralBoard) {
		integralBoard.setUpdateDate(new Date());
		boolean b = integralBoardDao.updateIntegralBoard(integralBoard);
		return b;
	}

	@Override
	public IntegralBoard loadIntegralBoard(Integer integralBoardId) {
		IntegralBoard r = integralBoardDao.loadIntegralBoard(integralBoardId);
		return r;
	}

	@Override
	public int countAll(
			Integer type,
			Integer timeType,
			Integer accountId,
			Date  recordTime,
			Date createDate,
			Date updateDate) {
		int c = integralBoardDao.countAll(type,timeType,accountId, recordTime,createDate,updateDate);
		return c;
	}

	@Override
	public List<IntegralBoard> browsePagingIntegralBoard(
			Integer type,
			Integer timeType,
			Integer accountId,
			Date recordTime,
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
		List<IntegralBoard> l = integralBoardDao.browsePagingIntegralBoard(type,timeType,accountId,recordTime,createDate,updateDate,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}
	@Override
	public boolean saveOrUpdateIntegralBoard(IntegralBoard integralBoard, Double integral) {
		boolean b = integralBoardDao.saveOrUpdateIntegralBoard(integralBoard, integral);
		return b;
	}
	@Override
	public Integer getLevel(Integer type, Integer timeType, Integer accountId, Date recordTime) {
		Integer ib = integralBoardDao.getLevel(type, timeType, accountId, recordTime);
		return ib;
	}

	
}
