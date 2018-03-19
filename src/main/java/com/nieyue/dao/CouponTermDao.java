package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.CouponTerm;

/**
 * 优惠劵项数据库接口
 * @author yy
 *
 */
@Mapper
public interface CouponTermDao {
	/** 新增优惠劵项*/	
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
	public List<CouponTerm> browsePagingCouponTerm(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
