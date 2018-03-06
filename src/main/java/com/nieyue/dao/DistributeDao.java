package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Distribute;

/**
 * 分发数据库接口
 * @author yy
 *
 */
@Mapper
public interface DistributeDao {
	/** 新增分发*/	
	public boolean addDistribute(Distribute distribute) ;	
	/** 删除分发 */	
	public boolean delDistribute(Integer distributeId) ;
	/** 更新分发*/	
	public boolean updateDistribute(Distribute distribute);
	/** 装载分发 */	
	public Distribute loadDistribute(Integer distributeId);	
	/** 分发总共数目 */	
	public int countAll(
			@Param("accountId")Integer accountId,
			@Param("buyAccountId")Integer buyAccountId,
			@Param("distributeDate")Date distributeDate,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页分发信息 */
	public List<Distribute> browsePagingDistribute(
			@Param("accountId")Integer accountId,
			@Param("buyAccountId")Integer buyAccountId,
			@Param("distributeDate")Date distributeDate,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
