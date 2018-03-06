package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.PlatformDay;

/**
 * 平台日数据库接口
 * @author yy
 *
 */
@Mapper
public interface PlatformDayDao {
	/** 新增平台日*/	
	public boolean addPlatformDay(PlatformDay platformDay) ;	
	/** 删除平台日 */	
	public boolean delPlatformDay(Integer platformDayId) ;
	/** 更新平台日*/	
	public boolean updatePlatformDay(PlatformDay platformDay);
	/** 装载平台日 */	
	public PlatformDay loadPlatformDay(Integer platformDayId);	
	/** 平台日总共数目 */	
	public int countAll(@Param("createDate")Date createDate);	
	/** 分页平台日信息 */
	public List<PlatformDay> browsePagingPlatformDay(@Param("createDate")Date createDate,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
