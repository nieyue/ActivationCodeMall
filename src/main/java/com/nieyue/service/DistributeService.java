package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Distribute;

/**
 * 分发逻辑层接口
 * @author yy
 *
 */
public interface DistributeService {
	/** 新增分发 */	
	public boolean addDistribute(Distribute distribute) ;	
	/** 删除分发 */	
	public boolean delDistribute(Integer distributeId) ;
	/** 更新分发*/	
	public boolean updateDistribute(Distribute distribute);
	/** 装载分发 */	
	public Distribute loadDistribute(Integer distributeId);	
	/** 分发总共数目 */	
	public int countAll(
			Integer accountId,
			Integer buyAccountId,
			Date distributeDate,
			Date createDate,
			Date updateDate,
			Integer status
			);
	/** 分页分发信息 */
	public List<Distribute> browsePagingDistribute(
			Integer accountId,
			Integer buyAccountId,
			Date distributeDate,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
