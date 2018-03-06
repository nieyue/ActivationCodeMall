package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.VipNumber;

/**
 * vip购买次数逻辑层接口
 * @author yy
 *
 */
public interface VipNumberService {
	/** 新增vip购买次数 */	
	public boolean addVipNumber(VipNumber vipNumber) ;	
	/** 删除vip购买次数 */	
	public boolean delVipNumber(Integer vipNumberId) ;
	/** 更新vip购买次数*/	
	public boolean updateVipNumber(VipNumber vipNumber);
	/** 装载vip购买次数 */	
	public VipNumber loadVipNumber(Integer vipNumberId);	
	/** vip购买次数总共数目 */	
	public int countAll(
			Integer number,
			Integer  accountId,
			Integer realMasterId,
			Date createDate,
			Date updateDate,
			Integer status
			);
	/** 分页vip购买次数信息 */
	public List<VipNumber> browsePagingVipNumber(
			Integer number,
			Integer  accountId,
			Integer realMasterId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
