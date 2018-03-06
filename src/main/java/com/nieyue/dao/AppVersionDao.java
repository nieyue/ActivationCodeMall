package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AppVersion;


/**
 * App版本数据库接口
 * @author yy
 *
 */
@Mapper
public interface AppVersionDao {
	/** 新增App版本*/	
	public boolean addAppVersion(AppVersion appVersion) ;	
	/** 删除App版本 */	
	public boolean delAppVersion(Integer appVersionId) ;
	/** 更新App版本*/	
	public boolean updateAppVersion(AppVersion appVersion);
	/** 装载App版本 */	
	public AppVersion loadAppVersion(Integer appVersionId);	
	/** App版本总共数目 */	
	public int countAll(@Param("platform")Integer platform,@Param("status")Integer status);	
	/** 分页App版本信息 */
	public List<AppVersion> browsePagingAppVersion(@Param("platform")Integer platform,@Param("status")Integer status,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
