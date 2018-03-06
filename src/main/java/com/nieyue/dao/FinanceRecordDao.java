package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.FinanceRecord;

/**
 * 财务记录数据库接口
 * @author yy
 *
 */
@Mapper
public interface FinanceRecordDao {
	/** 新增财务记录*/	
	public boolean addFinanceRecord(FinanceRecord financeRecord) ;	
	/** 删除财务记录 */	
	public boolean delFinanceRecord(Integer financeRecordId) ;
	/** 更新财务记录*/	
	public boolean updateFinanceRecord(FinanceRecord financeRecord);
	/** 装载财务记录 */	
	public FinanceRecord loadFinanceRecord(Integer financeRecordId);	
	/** 财务记录总共数目 */	
	public int countAll(
			@Param("accountId")Integer accountId,
			@Param("status")Integer status,
			@Param("method")Integer method,
			@Param("type")Integer type,
			@Param("transactionNumber")String transactionNumber,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页财务记录信息 */
	public List<FinanceRecord> browsePagingFinanceRecord(
			@Param("accountId")Integer accountId,
			@Param("status")Integer status,
			@Param("method")Integer method,
			@Param("type")Integer type,
			@Param("transactionNumber")String transactionNumber,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
