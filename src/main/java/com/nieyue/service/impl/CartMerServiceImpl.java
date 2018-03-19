package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.CartMer;
import com.nieyue.dao.CartMerDao;
import com.nieyue.service.CartMerService;
@Service
public class CartMerServiceImpl implements CartMerService{
	@Resource
	CartMerDao cartMerDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addCartMer(CartMer cartMer) {
		cartMer.setCreateDate(new Date());
		cartMer.setUpdateDate(new Date());
		boolean b = cartMerDao.addCartMer(cartMer);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delCartMer(Integer cartMerId) {
		boolean b = cartMerDao.delCartMer(cartMerId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateCartMer(CartMer cartMer) {
		cartMer.setUpdateDate(new Date());
		boolean b = cartMerDao.updateCartMer(cartMer);
		return b;
	}

	@Override
	public CartMer loadCartMer(Integer cartMerId) {
		CartMer r = cartMerDao.loadCartMer(cartMerId);
		return r;
	}

	@Override
	public int countAll(
			Integer number,
			Integer merId,
			Integer accountId) {
		int c = cartMerDao.countAll(number,merId,accountId);
		return c;
	}

	@Override
	public List<CartMer> browsePagingCartMer(
			Integer number,
			Integer merId,
			Integer accountId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<CartMer> l = cartMerDao.browsePagingCartMer(number,merId,accountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
