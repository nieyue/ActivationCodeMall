package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Sincerity;

/**
 * 诚信数据库接口
 * @author yy
 *
 */
@Mapper
public interface SincerityDao {
	/** 新增诚信*/	
	public boolean addSincerity(Sincerity sincerity) ;	
	/** 删除诚信 */	
	public boolean delSincerity(Integer sincerityId) ;
	/** 更新诚信*/	
	public boolean updateSincerity(Sincerity sincerity);
	/** 装载诚信 */	
	public Sincerity loadSincerity(Integer sincerityId);	
	/** 诚信总共数目 */	
	public int countAll(
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页诚信信息 */
	public List<Sincerity> browsePagingSincerity(
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
