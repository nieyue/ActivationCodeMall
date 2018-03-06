package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Config;

/**
 * 配置数据库接口
 * @author yy
 *
 */
@Mapper
public interface ConfigDao {
	/** 新增配置*/	
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
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
