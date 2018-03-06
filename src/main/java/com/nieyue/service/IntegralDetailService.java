package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.IntegralDetail;

/**
 * 积分详情逻辑层接口
 * @author yy
 *
 */
public interface IntegralDetailService {
	/** 新增积分详情 */	
	public boolean addIntegralDetail(IntegralDetail integralDetail) ;	
	/** 删除积分详情 */	
	public boolean delIntegralDetail(Integer integralDetailId) ;
	/** 更新积分详情*/	
	public boolean updateIntegralDetail(IntegralDetail integralDetail);
	/** 装载积分详情 */	
	public IntegralDetail loadIntegralDetail(Integer integralDetailId);	
	/** 积分详情总共数目 */	
	public int countAll(
			Integer type,
			Integer accountId,
			Date createDate,
			Date updateDate
			);
	/** 分页积分详情信息 */
	public List<IntegralDetail> browsePagingIntegralDetail(
			Integer type,
			Integer accountId,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
