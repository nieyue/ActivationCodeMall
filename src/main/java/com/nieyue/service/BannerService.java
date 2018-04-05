package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Banner;


/**
 * banner逻辑层接口
 * @author yy
 *
 */
public interface BannerService {
	/** 新增banner */	
	public boolean addBanner(Banner banner) ;	
	/** 删除banner */	
	public boolean delBanner(Integer bannerId) ;
	/** 更新banner*/	
	public boolean updateBanner(Banner banner);
	/** 装载banner */	
	public Banner loadBanner(Integer bannerId);	
	/** banner总共数目 */	
	public int countAll(Integer type,Integer status);
	/** 分页banner信息 */
	public List<Banner> browsePagingBanner(Integer type,Integer status,int pageNum,int pageSize,String orderName,String orderWay) ;
}
