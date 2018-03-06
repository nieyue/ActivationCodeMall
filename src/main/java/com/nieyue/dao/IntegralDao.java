package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Integral;

/**
 * 积分数据库接口
 * @author yy
 *
 */
@Mapper
public interface IntegralDao {
	/** 新增积分*/	
	public boolean addIntegral(Integral integral) ;	
	/** 删除积分 */	
	public boolean delIntegral(Integer integralId) ;
	/** 更新积分*/	
	public boolean updateIntegral(Integral integral);
	/** 装载积分 */	
	public Integral loadIntegral(Integer integralId);	
	/** 积分总共数目 */	
	public int countAll(
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页积分信息 */
	public List<Integral> browsePagingIntegral(
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
