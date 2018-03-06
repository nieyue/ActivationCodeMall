package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Config;

/**
 * 配置逻辑层接口
 * @author yy
 *
 */
public interface ConfigService {
	/** 新增配置 */	
	public boolean addConfig(Config config) ;	
	/** 删除配置 */	
	public boolean delConfig(Integer configId) ;
	/** 更新配置*/	
	public boolean updateConfig(Config config);
	/** 装载配置 */	
	public Config loadConfig(Integer configId);	
	/** 配置总共数目 */	
	public int countAll(
			);
	/** 分页配置信息 */
	public List<Config> browsePagingConfig(
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
