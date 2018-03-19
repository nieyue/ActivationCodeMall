package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.MerImg;

/**
 * 商品图片逻辑层接口
 * @author yy
 *
 */
public interface MerImgService {
	/** 新增商品图片 */	
	public boolean addMerImg(MerImg merImg) ;	
	/** 删除商品图片 */	
	public boolean delMerImg(Integer merImgId) ;
	/** 更新商品图片*/	
	public boolean updateMerImg(MerImg merImg);
	/** 装载商品图片 */	
	public MerImg loadMerImg(Integer merImgId);	
	/** 商品图片总共数目 */	
	public int countAll(
			Integer merId
			);
	/** 分页商品图片信息 */
	public List<MerImg> browsePagingMerImg(
			Integer merId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
