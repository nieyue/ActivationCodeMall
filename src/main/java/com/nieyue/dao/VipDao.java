package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Vip;

/**
 * vip数据库接口
 * @author yy
 *
 */
@Mapper
public interface VipDao {
	/** 新增vip*/	
	public boolean addVip(Vip vip) ;	
	/** 删除vip */	
	public boolean delVip(Integer vipId) ;
	/** 更新vip*/	
	public boolean updateVip(Vip vip);
	/** 装载vip */	
	public Vip loadVip(Integer vipId);	
	/** vip总共数目 */	
	public int countAll(@Param("accountId")Integer accountId,@Param("level")Integer level,@Param("createDate")Date createDate);	
	/** 分页vip信息 */
	public List<Vip> browsePagingVip(@Param("accountId")Integer accountId,@Param("level")Integer level,@Param("createDate")Date createDate,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
