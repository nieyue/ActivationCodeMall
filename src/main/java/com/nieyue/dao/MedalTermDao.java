package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MedalTerm;

/**
 * 勋章项数据库接口
 * @author yy
 *
 */
@Mapper
public interface MedalTermDao {
	/** 新增勋章项*/	
	public boolean addMedalTerm(MedalTerm medalTerm) ;	
	/** 删除勋章项 */	
	public boolean delMedalTerm(Integer medalTermId) ;
	/** 更新勋章项*/	
	public boolean updateMedalTerm(MedalTerm medalTerm);
	/** 装载勋章项 */	
	public MedalTerm loadMedalTerm(Integer medalTermId);	
	/** 勋章项总共数目 */	
	public int countAll(
			);	
	/** 分页勋章项信息 */
	public List<MedalTerm> browsePagingMedalTerm(
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
