package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.ReceiptInfo;

/**
 * 收货信息数据库接口
 * @author yy
 *
 */
@Mapper
public interface ReceiptInfoDao {
	/** 新增收货信息*/	
	public boolean addReceiptInfo(ReceiptInfo receiptInfo) ;	
	/** 删除收货信息 */	
	public boolean delReceiptInfo(Integer receiptInfoId) ;
	/** 更新收货信息*/	
	public boolean updateReceiptInfo(ReceiptInfo receiptInfo);
	/** 装载收货信息 */	
	public ReceiptInfo loadReceiptInfo(Integer receiptInfoId);	
	/** 收货信息总共数目 */	
	public int countAll(
			@Param("accountId")Integer accountId,
			@Param("isDefault")Integer isDefault,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页收货信息信息 */
	public List<ReceiptInfo> browsePagingReceiptInfo(
			@Param("accountId")Integer accountId,
			@Param("isDefault")Integer isDefault,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
