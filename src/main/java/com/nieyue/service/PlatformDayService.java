package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.PlatformDay;

/**
 * 平台日逻辑层接口
 * @author yy
 *
 */
public interface PlatformDayService {
	/** 新增平台日 */	
	public boolean addPlatformDay(PlatformDay platformDay) ;	
	/** 删除平台日 */	
	public boolean delPlatformDay(Integer platformDayId) ;
	/** 更新平台日*/	
	public boolean updatePlatformDay(PlatformDay platformDay);
	/** 装载平台日 */	
	public PlatformDay loadPlatformDay(Integer platformDayId);	
	/** 平台日总共数目 */	
	public int countAll(Date createDate);
	/** 分页平台日信息 */
	public List<PlatformDay> browsePagingPlatformDay(Date createDate,int pageNum,int pageSize,String orderName,String orderWay) ;
}
