package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.IntegralBoard;

/**
 * 积分榜逻辑层接口
 * @author yy
 *
 */
public interface IntegralBoardService {
	/** 新增积分榜 */	
	public boolean addIntegralBoard(IntegralBoard integralBoard) ;	
	/** 删除积分榜 */	
	public boolean delIntegralBoard(Integer integralBoardId) ;
	/** 更新积分榜*/	
	public boolean updateIntegralBoard(IntegralBoard integralBoard);
	/** 更新或者增加积分榜*/	
	public boolean saveOrUpdateIntegralBoard(IntegralBoard integralBoard,Double integral);
	/** 装载积分榜 */	
	public IntegralBoard loadIntegralBoard(Integer integralBoardId);
	/** 或者账户排行等级 */	
	public Integer getLevel(
			Integer type,
			Integer timeType,
			Integer accountId,
			Date recordTime);	
	/** 积分榜总共数目 */	
	public int countAll(
			Integer type,
			Integer timeType,
			Integer accountId,
			Date recordTime,
			Date createDate,
			Date updateDate
			);
	/** 分页积分榜信息 */
	public List<IntegralBoard> browsePagingIntegralBoard(
			Integer type,
			Integer timeType,
			Integer accountId,
			Date recordTime,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
