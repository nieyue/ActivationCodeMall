package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.nieyue.bean.CartMer;
import com.nieyue.bean.Mer;
import com.nieyue.dao.CartMerDao;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.CartMerService;
import com.nieyue.service.MerService;
import com.nieyue.util.Arith;
@Service
public class CartMerServiceImpl implements CartMerService{
	@Resource
	CartMerDao cartMerDao;
	@Resource
	MerService merService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addCartMer(CartMer cartMer) {
		boolean b=false;
		Integer merId=cartMer.getMerId();
		if(ObjectUtils.isEmpty(merId)){
			throw new CommonRollbackException("请选择商品");
		}
		Mer mer=merService.loadMer(merId);
		if(ObjectUtils.isEmpty(mer)){
			throw new CommonRollbackException("请选择商品");
		}
		
		List<CartMer> cartMerList = cartMerDao.browsePagingCartMer(null, cartMer.getMerId(), cartMer.getAccountId(), 0, 1, "cart_mer_id", "asc");
		Integer number=cartMer.getNumber();
		//如果已经存在
		if(cartMerList.size()==1){
			number+=cartMerList.get(0).getNumber();
		}
		if(number==null||number<=0){
			throw new CommonRollbackException("商品至少1个");
		}
		if(number>5){
			throw new CommonRollbackException("一个商品最大数5个");
		}
		
		//设置总价
		cartMer.setTotalPrice(Arith.mul(mer.getUnitPrice(), number));
		cartMer.setNumber(number);
		cartMer.setUpdateDate(new Date());
		if(cartMerList.size()==1){
			cartMer.setCartMerId(cartMerList.get(0).getCartMerId());
			b = cartMerDao.updateCartMer(cartMer);
		}else{
			cartMer.setCreateDate(new Date());
			b = cartMerDao.addCartMer(cartMer);			
		}
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
		Mer mer=merService.loadMer(r.getMerId());
		r.setMer(mer);
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
		l.forEach((cm)->{
			Mer mer=merService.loadMer(cm.getMerId());
			cm.setMer(mer);
		});
		return l;
	}

	
}
