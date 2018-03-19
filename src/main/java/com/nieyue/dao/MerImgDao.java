package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerImg;

/**
 * 商品图片数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerImgDao {
	/** 新增商品图片*/	
	public boolean addMerImg(MerImg merImg) ;	
	/** 删除商品图片 */	
	public boolean delMerImg(Integer merImgId) ;
	/** 更新商品图片*/	
	public boolean updateMerImg(MerImg merImg);
	/** 装载商品图片 */	
	public MerImg loadMerImg(Integer merImgId);	
	/** 商品图片总共数目 */	
	public int countAll(
			@Param("merId")Integer merId
			);	
	/** 分页商品图片信息 */
	public List<MerImg> browsePagingMerImg(
			@Param("merId")Integer merId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
