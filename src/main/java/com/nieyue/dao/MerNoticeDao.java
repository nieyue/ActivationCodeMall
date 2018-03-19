package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerNotice;

/**
 * 商品公告数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerNoticeDao {
	/** 新增商品公告*/	
	public boolean addMerNotice(MerNotice merNotice) ;	
	/** 删除商品公告 */	
	public boolean delMerNotice(Integer merNoticeId) ;
	/** 更新商品公告*/	
	public boolean updateMerNotice(MerNotice merNotice);
	/** 装载商品公告 */	
	public MerNotice loadMerNotice(Integer merNoticeId);	
	/** 商品公告总共数目 */	
	public int countAll(
			@Param("merId")Integer merId
			);	
	/** 分页商品公告信息 */
	public List<MerNotice> browsePagingMerNotice(
			@Param("merId")Integer merId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
