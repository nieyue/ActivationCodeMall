package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.ReceiptInfo;

/**
 * 商品逻辑层接口
 * @author yy
 *
 */
public interface ReceiptInfoService {
	/** 新增商品 */	
	public boolean addReceiptInfo(ReceiptInfo receiptInfo) ;	
	/** 删除商品 */	
	public boolean delReceiptInfo(Integer receiptInfoId) ;
	/** 更新商品*/	
	public boolean updateReceiptInfo(ReceiptInfo receiptInfo);
	/** 装载商品 */	
	public ReceiptInfo loadReceiptInfo(Integer receiptInfoId);	
	/** 商品总共数目 */	
	public int countAll(
			Integer accountId,
			Integer isDefault,
			Date createDate,
			Date updateDate);
	/** 分页商品信息 */
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
