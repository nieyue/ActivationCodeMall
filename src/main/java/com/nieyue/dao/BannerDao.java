package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Banner;


/**
 * banner数据库接口
 * @author yy
 *
 */
@Mapper
public interface BannerDao {
	/** 新增banner*/	
	public boolean addBanner(Banner banner) ;	
	/** 删除banner */	
	public boolean delBanner(Integer bannerId) ;
	/** 更新banner*/	
	public boolean updateBanner(Banner banner);
	/** 装载banner */	
	public Banner loadBanner(Integer bannerId);	
	/** banner总共数目 */	
	public int countAll(@Param("type")Integer type,@Param("status")Integer status);	
	/** 分页banner信息 */
	public List<Banner> browsePagingBanner(@Param("type")Integer type,@Param("status")Integer status,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
