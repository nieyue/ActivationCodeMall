package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.AppVersion;


/**
 * App版本逻辑层接口
 * @author yy
 *
 */
public interface AppVersionService {
	/** 新增App版本 */	
	public boolean addAppVersion(AppVersion appVersion) ;	
	/** 删除App版本 */	
	public boolean delAppVersion(Integer appVersionId) ;
	/** 更新App版本*/	
	public boolean updateAppVersion(AppVersion appVersion);
	/** 装载App版本 */	
	public AppVersion loadAppVersion(Integer appVersionId);	
	/** App版本总共数目 */	
	public int countAll(Integer platform,Integer status);
	/** 分页App版本信息 */
	public List<AppVersion> browsePagingAppVersion(Integer platform,Integer status,int pageNum,int pageSize,String orderName,String orderWay) ;
}
