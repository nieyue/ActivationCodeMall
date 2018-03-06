package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Integral;

/**
 * 积分逻辑层接口
 * @author yy
 *
 */
public interface IntegralService {
	/** 新增积分 */	
	public boolean addIntegral(Integral integral) ;	
	/** 删除积分 */	
	public boolean delIntegral(Integer integralId) ;
	/** 更新积分*/	
	public boolean updateIntegral(Integral integral);
	/** 装载积分 */	
	public Integral loadIntegral(Integer integralId);	
	/** 积分总共数目 */	
	public int countAll(
			Integer accountId,
			Date createDate,
			Date updateDate
			);
	/** 分页积分信息 */
	public List<Integral> browsePagingIntegral(
			Integer accountId,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
