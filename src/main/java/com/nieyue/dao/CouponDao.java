package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Coupon;

/**
 * 优惠劵数据库接口
 * @author yy
 *
 */
@Mapper
public interface CouponDao {
	/** 新增优惠劵*/	
	public boolean addCoupon(Coupon coupon) ;	
	/** 删除优惠劵 */	
	public boolean delCoupon(Integer couponId) ;
	/** 更新优惠劵*/	
	public boolean updateCoupon(Coupon coupon);
	/** 装载优惠劵 */	
	public Coupon loadCoupon(Integer couponId);	
	/** 优惠劵总共数目 */	
	public int countAll(
			@Param("code")String code,
			@Param("merCateId")Integer merCateId,
			@Param("couponTermId")Integer couponTermId,
			@Param("accountId")Integer accountId,
			@Param("startDate")Date startDate,
			@Param("endDate")Date endDate,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页优惠劵信息 */
	public List<Coupon> browsePagingCoupon(
			@Param("code")String code,
			@Param("merCateId")Integer merCateId,
			@Param("couponTermId")Integer couponTermId,
			@Param("accountId")Integer accountId,
			@Param("startDate")Date startDate,
			@Param("endDate")Date endDate,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
