package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.SpreadLink;

/**
 * 推广连接数据库接口
 * @author yy
 *
 */
@Mapper
public interface SpreadLinkDao {
	/** 新增推广连接*/	
	public boolean addSpreadLink(SpreadLink spreadLink) ;	
	/** 删除推广连接 */	
	public boolean delSpreadLink(Integer spreadLinkId) ;
	/** 更新推广连接*/	
	public boolean updateSpreadLink(SpreadLink spreadLink);
	/** 装载推广连接 */	
	public SpreadLink loadSpreadLink(Integer spreadLinkId);	
	/** 推广连接总共数目 */	
	public int countAll(@Param("merId")Integer merId,@Param("accountId") Integer accountId);	
	/** 分页推广连接信息 */
	public List<SpreadLink> browsePagingSpreadLink(
			@Param("merId")Integer merId,
			@Param("accountId") Integer accountId,
			@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
