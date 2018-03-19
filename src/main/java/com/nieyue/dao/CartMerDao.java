package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.CartMer;

/**
 * 购物车商品数据库接口
 * @author yy
 *
 */
@Mapper
public interface CartMerDao {
	/** 新增购物车商品*/	
	public boolean addCartMer(CartMer cartMer) ;	
	/** 删除购物车商品 */	
	public boolean delCartMer(Integer cartMerId) ;
	/** 更新购物车商品*/	
	public boolean updateCartMer(CartMer cartMer);
	/** 装载购物车商品 */	
	public CartMer loadCartMer(Integer cartMerId);	
	/** 购物车商品总共数目 */	
	public int countAll(
			@Param("number")Integer number,
			@Param("merId")Integer merId,
			@Param("accountId")Integer accountId
			);	
	/** 分页购物车商品信息 */
	public List<CartMer> browsePagingCartMer(
			@Param("number")Integer number,
			@Param("merId")Integer merId,
			@Param("accountId")Integer accountId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
