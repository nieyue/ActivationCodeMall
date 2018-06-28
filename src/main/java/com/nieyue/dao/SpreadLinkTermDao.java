package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.SpreadLinkTerm;

/**
 * 推广连接项数据库接口
 * @author yy
 *
 */
@Mapper
public interface SpreadLinkTermDao {
	/** 新增推广连接项*/	
	public boolean addSpreadLinkTerm(SpreadLinkTerm spreadLinkTerm) ;	
	/** 删除推广连接项 */	
	public boolean delSpreadLinkTerm(Integer spreadLinkTermId) ;
	/** 更新推广连接项*/	
	public boolean updateSpreadLinkTerm(SpreadLinkTerm spreadLinkTerm);
	/** 装载推广连接项 */	
	public SpreadLinkTerm loadSpreadLinkTerm(Integer spreadLinkTermId);	
	/** 推广连接项总共数目 */	
	public int countAll(@Param("merId")Integer merId);	
	/** 分页推广连接项信息 */
	public List<SpreadLinkTerm> browsePagingSpreadLinkTerm(@Param("merId")Integer merId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
