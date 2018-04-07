package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.SpreadRecord;

/**
 * 推广记录数据库接口
 * @author yy
 *
 */
@Mapper
public interface SpreadRecordDao {
	/** 新增推广记录*/	
	public boolean addSpreadRecord(SpreadRecord spreadRecord) ;	
	/** 删除推广记录 */	
	public boolean delSpreadRecord(Integer spreadRecordId) ;
	/** 更新推广记录*/	
	public boolean updateSpreadRecord(SpreadRecord spreadRecord);
	/** 装载推广记录 */	
	public SpreadRecord loadSpreadRecord(Integer spreadRecordId);	
	/** 推广记录总共数目 */	
	public int countAll(@Param("accountId") Integer accountId);	
	/** 分页推广记录信息 */
	public List<SpreadRecord> browsePagingSpreadRecord(
			@Param("accountId") Integer accountId,
			@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
