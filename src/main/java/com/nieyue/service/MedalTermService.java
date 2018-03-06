package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MedalTerm;

/**
 * 勋章项逻辑层接口
 * @author yy
 *
 */
public interface MedalTermService {
	/** 新增勋章项 */	
	public boolean addMedalTerm(MedalTerm medalTerm) ;	
	/** 删除勋章项 */	
	public boolean delMedalTerm(Integer medalTermId) ;
	/** 更新勋章项*/	
	public boolean updateMedalTerm(MedalTerm medalTerm);
	/** 装载勋章项 */	
	public MedalTerm loadMedalTerm(Integer medalTermId);	
	/** 勋章项总共数目 */	
	public int countAll(
			);
	/** 分页勋章项信息 */
	public List<MedalTerm> browsePagingMedalTerm(
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
