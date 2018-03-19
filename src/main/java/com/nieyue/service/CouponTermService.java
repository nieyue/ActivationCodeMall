package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.CouponTerm;

/**
 * 优惠劵项逻辑层接口
 * @author yy
 *
 */
public interface CouponTermService {
	/** 新增优惠劵项 */	
	public boolean addCouponTerm(CouponTerm couponTerm) ;	
	/** 删除优惠劵项 */	
	public boolean delCouponTerm(Integer couponTermId) ;
	/** 更新优惠劵项*/	
	public boolean updateCouponTerm(CouponTerm couponTerm);
	/** 装载优惠劵项 */	
	public CouponTerm loadCouponTerm(Integer couponTermId);	
	/** 优惠劵项总共数目 */	
	public int countAll();
	/** 分页优惠劵项信息 */
	public List<CouponTerm> browsePagingCouponTerm(int pageNum,int pageSize,String orderName,String orderWay) ;
}
