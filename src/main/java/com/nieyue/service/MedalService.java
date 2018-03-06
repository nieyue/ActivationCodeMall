package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Medal;

/**
 * 勋章逻辑层接口
 * @author yy
 *
 */
public interface MedalService {
	/** 新增勋章 */	
	public boolean addMedal(Medal medal) ;	
	/** 删除勋章 */	
	public boolean delMedal(Integer medalId) ;
	/** 更新勋章*/	
	public boolean updateMedal(Medal medal);
	/** 装载勋章 */	
	public Medal loadMedal(Integer medalId);	
	/** 勋章总共数目 */	
	public int countAll(
			Integer medalTermId,
			Integer accountId,
			Date createDate,
			Date updateDate
			);
	/** 分页勋章信息 */
	public List<Medal> browsePagingMedal(
			Integer medalTermId,
			Integer accountId,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
