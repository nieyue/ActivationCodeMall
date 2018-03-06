package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.IntegralDetail;

/**
 * 积分详情数据库接口
 * @author yy
 *
 */
@Mapper
public interface IntegralDetailDao {
	/** 新增积分详情*/	
	public boolean addIntegralDetail(IntegralDetail integralDetail) ;	
	/** 删除积分详情 */	
	public boolean delIntegralDetail(Integer integralDetailId) ;
	/** 更新积分详情*/	
	public boolean updateIntegralDetail(IntegralDetail integralDetail);
	/** 装载积分详情 */	
	public IntegralDetail loadIntegralDetail(Integer integralDetailId);	
	/** 积分详情总共数目 */	
	public int countAll(
			@Param("type")Integer type,
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页积分详情信息 */
	public List<IntegralDetail> browsePagingIntegralDetail(
			@Param("type")Integer type,
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
