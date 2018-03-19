package com.nieyue.service;

import java.util.Date;
import java.util.List;


import com.nieyue.bean.Coupon;

/**
 * 优惠劵逻辑层接口
 * @author yy
 *
 */
public interface CouponService {
	/** 新增优惠劵 */	
	public boolean addCoupon(Coupon coupon) ;	
	/** 删除优惠劵 */	
	public boolean delCoupon(Integer couponId) ;
	/** 更新优惠劵*/	
	public boolean updateCoupon(Coupon coupon);
	/** 装载优惠劵 */	
	public Coupon loadCoupon(Integer couponId);	
	/** 优惠劵总共数目 */	
	public int countAll(
			String code,
			Integer merCateId,
			Integer couponTermId,
			Integer accountId,
			Date startDate,
			Date endDate,
			Date createDate,
			Date updateDate,
			Integer status);
	/** 分页优惠劵信息 */
	public List<Coupon> browsePagingCoupon(
			String code,
			Integer merCateId,
			Integer couponTermId,
			Integer accountId,
			Date startDate,
			Date endDate,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum,int pageSize,String orderName,String orderWay) ;
}
