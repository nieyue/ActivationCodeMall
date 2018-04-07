package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.SpreadRecord;

/**
 * 推广记录逻辑层接口
 * @author yy
 *
 */
public interface SpreadRecordService {
	/** 新增推广记录 */	
	public boolean addSpreadRecord(SpreadRecord spreadRecord) ;	
	/** 删除推广记录 */	
	public boolean delSpreadRecord(Integer spreadRecordId) ;
	/** 更新推广记录*/	
	public boolean updateSpreadRecord(SpreadRecord spreadRecord);
	/** 装载推广记录 */	
	public SpreadRecord loadSpreadRecord(Integer spreadRecordId);	
	/** 推广记录总共数目 */	
	public int countAll(Integer accountId);
	/** 分页推广记录信息 */
	public List<SpreadRecord> browsePagingSpreadRecord(Integer accountId,int pageNum,int pageSize,String orderName,String orderWay) ;
}
