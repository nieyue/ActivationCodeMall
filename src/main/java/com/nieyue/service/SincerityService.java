package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Sincerity;

/**
 * 诚信逻辑层接口
 * @author yy
 *
 */
public interface SincerityService {
	/** 新增诚信 */	
	public boolean addSincerity(Sincerity sincerity) ;	
	/** 删除诚信 */	
	public boolean delSincerity(Integer sincerityId) ;
	/** 更新诚信*/	
	public boolean updateSincerity(Sincerity sincerity);
	/** 装载诚信 */	
	public Sincerity loadSincerity(Integer sincerityId);	
	/** 诚信总共数目 */	
	public int countAll(
			Integer accountId,
			Date createDate,
			Date updateDate
			);
	/** 分页诚信信息 */
	public List<Sincerity> browsePagingSincerity(
			Integer accountId,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
