package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Mer;

/**
 * 商品数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerDao {
	/** 新增商品*/	
	public boolean addMer(Mer mer) ;	
	/** 删除商品 */	
	public boolean delMer(Integer merId) ;
	/** 更新商品*/	
	public boolean updateMer(Mer mer);
	/** 装载商品 */	
	public Mer loadMer(Integer merId);	
	/** 商品总共数目 */	
	public int countAll(
			@Param("region")Integer region,
			@Param("type")Integer type,
			@Param("name")String name,
			@Param("recommend")Integer recommend,
			@Param("unitPrice")Double unitPrice,
			@Param("saleNumber")Integer saleNumber,
			@Param("merScore")Double merScore,
			@Param("merCateId")Integer merCateId,
			@Param("sellerAccountId")Integer sellerAccountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页商品信息 */
	public List<Mer> browsePagingMer(
			@Param("region")Integer region,
			@Param("type")Integer type,
			@Param("name")String name,
			@Param("recommend")Integer recommend,
			@Param("unitPrice")Double unitPrice,
			@Param("saleNumber")Integer saleNumber,
			@Param("merScore")Double merScore,
			@Param("merCateId")Integer merCateId,
			@Param("sellerAccountId")Integer sellerAccountId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
