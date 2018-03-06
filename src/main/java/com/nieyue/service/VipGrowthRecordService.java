package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.VipGrowthRecord;

/**
 * Vip成长记录逻辑层接口
 * @author yy
 *
 */
public interface VipGrowthRecordService {
	/** 新增Vip成长记录 */	
	public boolean addVipGrowthRecord(VipGrowthRecord vipGrowthRecord) ;	
	/** 删除Vip成长记录 */	
	public boolean delVipGrowthRecord(Integer vipGrowthRecordId) ;
	/** 更新Vip成长记录*/	
	public boolean updateVipGrowthRecord(VipGrowthRecord vipGrowthRecord);
	/** 装载Vip成长记录 */	
	public VipGrowthRecord loadVipGrowthRecord(Integer vipGrowthRecordId);	
	/** Vip成长记录总共数目 */	
	public int countAll(Integer accountId,Integer level,Date createDate);
	/** 分页Vip成长记录信息 */
	public List<VipGrowthRecord> browsePagingVipGrowthRecord(Integer accountId,Integer level,Date createDate,int pageNum,int pageSize,String orderName,String orderWay) ;
}
