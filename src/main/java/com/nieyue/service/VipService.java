package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Vip;

/**
 * Vip逻辑层接口
 * @author yy
 *
 */
public interface VipService {
	/** 新增Vip */	
	public boolean addVip(Vip vip) ;	
	/** 删除Vip */	
	public boolean delVip(Integer vipId) ;
	/** 更新Vip*/	
	public boolean updateVip(Vip vip);
	/** 装载Vip */	
	public Vip loadVip(Integer vipId);	
	/** Vip总共数目 */	
	public int countAll(Integer accountId,Integer level,Date createDate);
	/** 分页Vip信息 */
	public List<Vip> browsePagingVip(Integer accountId,Integer level,Date createDate,int pageNum,int pageSize,String orderName,String orderWay) ;
}
