package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.VipGrowthRecord;

/**
 * Vip成长记录数据库接口
 * @author yy
 *
 */
@Mapper
public interface VipGrowthRecordDao {
	/** 新增Vip成长记录*/	
	public boolean addVipGrowthRecord(VipGrowthRecord vipGrowthRecord) ;	
	/** 删除Vip成长记录 */	
	public boolean delVipGrowthRecord(Integer vipGrowthRecordId) ;
	/** 更新Vip成长记录*/	
	public boolean updateVipGrowthRecord(VipGrowthRecord vipGrowthRecord);
	/** 装载Vip成长记录 */	
	public VipGrowthRecord loadVipGrowthRecord(Integer vipGrowthRecordId);	
	/** Vip成长记录总共数目 */	
	public int countAll(@Param("accountId")Integer accountId,@Param("level")Integer level,@Param("createDate")Date createDate);	
	/** 分页Vip成长记录信息 */
	public List<VipGrowthRecord> browsePagingVipGrowthRecord(@Param("accountId")Integer accountId,@Param("level")Integer level,@Param("createDate")Date createDate,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
