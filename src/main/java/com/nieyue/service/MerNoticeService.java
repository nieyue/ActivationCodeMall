package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MerNotice;

/**
 * 商品公告逻辑层接口
 * @author yy
 *
 */
public interface MerNoticeService {
	/** 新增商品公告 */	
	public boolean addMerNotice(MerNotice merNotice) ;	
	/** 删除商品公告 */	
	public boolean delMerNotice(Integer merNoticeId) ;
	/** 更新商品公告*/	
	public boolean updateMerNotice(MerNotice merNotice);
	/** 装载商品公告 */	
	public MerNotice loadMerNotice(Integer merNoticeId);	
	/** 商品公告总共数目 */	
	public int countAll(
			Integer merId
			);
	/** 分页商品公告信息 */
	public List<MerNotice> browsePagingMerNotice(
			Integer merId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
