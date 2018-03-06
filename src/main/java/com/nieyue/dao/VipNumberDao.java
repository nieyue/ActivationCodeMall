package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.VipNumber;

/**
 * vip购买次数数据库接口
 * @author yy
 *
 */
@Mapper
public interface VipNumberDao {
	/** 新增vip购买次数*/	
	public boolean addVipNumber(VipNumber vipNumber) ;	
	/** 删除vip购买次数 */	
	public boolean delVipNumber(Integer vipNumberId) ;
	/** 更新vip购买次数*/	
	public boolean updateVipNumber(VipNumber vipNumber);
	/** 装载vip购买次数 */	
	public VipNumber loadVipNumber(Integer vipNumberId);	
	/** vip购买次数总共数目 */	
	public int countAll(
			@Param("number")Integer number,
			@Param("accountId")Integer accountId,
			@Param("realMasterId")Integer realMasterId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页vip购买次数信息 */
	public List<VipNumber> browsePagingVipNumber(
			@Param("number")Integer number,
			@Param("accountId")Integer accountId,
			@Param("realMasterId")Integer realMasterId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
