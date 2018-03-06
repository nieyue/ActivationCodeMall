package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.FinanceRecord;

/**
 * 财务记录逻辑层接口
 * @author yy
 *
 */
public interface FinanceRecordService {
	/** 新增财务记录 */	
	public boolean addFinanceRecord(FinanceRecord financeRecord) ;	
	/** 删除财务记录 */	
	public boolean delFinanceRecord(Integer financeRecordId) ;
	/** 更新财务记录*/	
	public boolean updateFinanceRecord(FinanceRecord financeRecord);
	/** 装载财务记录 */	
	public FinanceRecord loadFinanceRecord(Integer financeRecordId);	
	/** 财务记录总共数目 */	
	public int countAll(
			Integer accountId,
			Integer status,
			Integer method,
			Integer type,
			String transactionNumber,
			Date createDate,
			Date updateDate
			);
	/** 分页财务记录信息 */
	public List<FinanceRecord> browsePagingFinanceRecord(
			Integer accountId,
			Integer status,
			Integer method,
			Integer type,
			String transactionNumber,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
