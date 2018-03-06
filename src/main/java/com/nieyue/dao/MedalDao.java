package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Medal;

/**
 * 勋章数据库接口
 * @author yy
 *
 */
@Mapper
public interface MedalDao {
	/** 新增勋章*/	
	public boolean addMedal(Medal medal) ;	
	/** 删除勋章 */	
	public boolean delMedal(Integer medalId) ;
	/** 更新勋章*/	
	public boolean updateMedal(Medal medal);
	/** 装载勋章 */	
	public Medal loadMedal(Integer medalId);	
	/** 勋章总共数目 */	
	public int countAll(
			@Param("medalTermId")Integer medalTermId,
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页勋章信息 */
	public List<Medal> browsePagingMedal(
			@Param("medalTermId")Integer medalTermId,
			@Param("accountId")Integer accountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
