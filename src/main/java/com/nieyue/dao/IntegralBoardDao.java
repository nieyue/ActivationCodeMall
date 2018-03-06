package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.IntegralBoard;

/**
 * 积分榜数据库接口
 * @author yy
 *
 */
@Mapper
public interface IntegralBoardDao {
	/** 新增积分榜*/	
	public boolean addIntegralBoard(IntegralBoard integralBoard) ;	
	/** 删除积分榜 */	
	public boolean delIntegralBoard(Integer integralBoardId) ;
	/** 更新积分榜*/	
	public boolean updateIntegralBoard(IntegralBoard integralBoard);
	/** 更新或者增加积分榜*/	
	public boolean saveOrUpdateIntegralBoard(@Param("IntegralBoard")IntegralBoard integralBoard,@Param("integral")Double integral);
	/** 装载积分榜 */	
	public IntegralBoard loadIntegralBoard(Integer integralBoardId);	
	/** 或者账户排行等级 */	
	public Integer getLevel(
			@Param("type")Integer type,
			@Param("timeType")Integer timeType,
			@Param("accountId")Integer accountId,
			@Param("recordTime")Date recordTime);	
	/** 积分榜总共数目 */	
	public int countAll(
			@Param("type")Integer type,
			@Param("timeType")Integer timeType,
			@Param("accountId")Integer accountId,
			@Param("recordTime")Date recordTime,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页积分榜信息 */
	public List<IntegralBoard> browsePagingIntegralBoard(
			@Param("type")Integer type,
			@Param("timeType")Integer timeType,
			@Param("accountId")Integer accountId,
			@Param("recordTime")Date recordTime,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
