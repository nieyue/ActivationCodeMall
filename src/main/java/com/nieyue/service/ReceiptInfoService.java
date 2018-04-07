package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.ReceiptInfo;

/**
 * 收货信息逻辑层接口
 * @author yy
 *
 */
public interface ReceiptInfoService {
	/** 新增收货信息 */	
	public boolean addReceiptInfo(ReceiptInfo receiptInfo) ;	
	/** 删除收货信息 */	
	public boolean delReceiptInfo(Integer receiptInfoId) ;
	/** 更新收货信息*/	
	public boolean updateReceiptInfo(ReceiptInfo receiptInfo);
	/** 装载收货信息 */	
	public ReceiptInfo loadReceiptInfo(Integer receiptInfoId);	
	/** 收货信息总共数目 */	
	public int countAll(
			Integer accountId,
			Integer isDefault,
			Date createDate,
			Date updateDate);
	/** 分页收货信息信息 */
	public List<ReceiptInfo> browsePagingReceiptInfo(
			Integer accountId,
			Integer isDefault,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
