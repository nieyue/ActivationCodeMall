package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.CartMer;

/**
 * 购物车商品逻辑层接口
 * @author yy
 *
 */
public interface CartMerService {
	/** 新增购物车商品 */	
	public boolean addCartMer(CartMer cartMer) ;	
	/** 删除购物车商品 */	
	public boolean delCartMer(Integer cartMerId) ;
	/** 更新购物车商品*/	
	public boolean updateCartMer(CartMer cartMer);
	/** 装载购物车商品 */	
	public CartMer loadCartMer(Integer cartMerId);	
	/** 购物车商品批量转未支付订单商品 */	
	public boolean batchCartMerTurnOrder(String cartMerList,Integer couponId,Integer payType,Integer receiptInfoId,Integer accountId);	
	/** 购物车商品总共数目 */	
	public int countAll(
			Integer number,
			Integer merId,
			Integer accountId
			);
	/** 分页购物车商品信息 */
	public List<CartMer> browsePagingCartMer(
			Integer number,
			Integer merId,
			Integer accountId,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
