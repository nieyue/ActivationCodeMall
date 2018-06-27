package com.nieyue.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.nieyue.bean.Account;
import com.nieyue.bean.CartMer;
import com.nieyue.bean.Coupon;
import com.nieyue.bean.Mer;
import com.nieyue.bean.Order;
import com.nieyue.bean.OrderDetail;
import com.nieyue.bean.OrderReceiptInfo;
import com.nieyue.bean.ReceiptInfo;
import com.nieyue.dao.CartMerDao;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.AccountService;
import com.nieyue.service.CartMerService;
import com.nieyue.service.CouponService;
import com.nieyue.service.MerService;
import com.nieyue.service.OrderDetailService;
import com.nieyue.service.OrderReceiptInfoService;
import com.nieyue.service.OrderService;
import com.nieyue.service.ReceiptInfoService;
import com.nieyue.util.Arith;
import com.nieyue.util.SnowflakeIdWorker;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class CartMerServiceImpl implements CartMerService{
	@Resource
	CartMerDao cartMerDao;
	@Resource
	AccountService accountService;
	@Resource
	MerService merService;
	@Resource
	OrderService orderService;
	@Resource
	OrderDetailService orderDetailService;
	@Resource
	CouponService couponService;
	@Resource
	ReceiptInfoService receiptInfoService;
	@Resource
	OrderReceiptInfoService orderReceiptInfoService;
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
		if(mer.getStatus()==0){
			throw new CommonRollbackException("商品"+mer.getName()+"已下架");
		}
		
		List<CartMer> cartMerList = cartMerDao.browsePagingCartMer(null, cartMer.getMerId(), cartMer.getAccountId(), 0, 20, "cart_mer_id", "asc");
		Integer number=cartMer.getNumber();
		//如果已经存在
		if(cartMerList.size()==1){
			if(cartMerList.size()==10){
				throw new CommonRollbackException("购物车商品最多10个");
			}
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
	@SuppressWarnings({ "static-access", "rawtypes" })
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean batchCartMerTurnOrder(String cartMerList, Integer couponId, Integer payType, Integer receiptInfoId,
			Integer accountId) {
		boolean dm=false;
		ReceiptInfo receiptInfo = receiptInfoService.loadReceiptInfo(receiptInfoId);
		if(ObjectUtils.isEmpty(receiptInfo)){
			throw new CommonRollbackException("缺少收货地址");
		}
		
		if(payType<1||payType>5){
			throw new CommonRollbackException("支付类型错误");
		}
		Coupon coupon=null;
		if(couponId!=null){
			coupon = couponService.loadCoupon(couponId);
			//状态，默认1可用，2已用，3已失效
			if(coupon==null){
				throw new CommonRollbackException("优惠劵不存在");
			}else if(coupon.getStatus()==2){
				throw new CommonRollbackException("优惠劵已经用过");
			}else if(coupon.getStatus()==3){
				throw new CommonRollbackException("优惠劵已经失效");
			}
		}
		JSONArray jsonarray = JSONArray.fromObject(cartMerList);
		List<CartMer> list=new ArrayList<>();
		for (Iterator iterator = jsonarray.iterator(); iterator.hasNext();) {
			JSONObject json = (JSONObject) iterator.next();
			if(json.getInt("accountId")!=accountId){
				throw new CommonRollbackException("非法访问");
			}
			list.add((CartMer)json.toBean(json, CartMer.class));
		}
		for (int i = 0; i < list.size(); i++) {
				CartMer e = list.get(i);
				Mer mer = merService.loadMer(e.getMerId());
				if(mer==null){
					throw new CommonRollbackException("商品不存在");
				}
				if(mer.getStatus()==0){
					throw new CommonRollbackException("商品"+mer.getName()+"已下架");
				}
				
				e.setMer(mer);
				//订单
				Order order = new Order();
				order.setAccountId(accountId);
				order.setCreateDate(new Date());
				order.setOrderNumber(SnowflakeIdWorker.getId().toString());
				order.setPayType(payType);
				//订单状态，1冻结单，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
				order.setStatus(2);
				//子状态，1(1冻结单)，2（1待支付），3（1已支付），4（1等待发货），5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），6（1已取消），7（1已删除）
				order.setSubstatus(1);
				//类型，1购买商品，2账户提现，3退款，4诚信押金
				order.setType(1);
				order.setUpdateDate(new Date());
				if(e.getSpreadAccountId()!=null){
					Account spreadAccount = accountService.loadAccount(e.getSpreadAccountId());
					if(spreadAccount.getAccountId()!=null){
						order.setSpreadAccountId(spreadAccount.getAccountId());					
					}
				}
				dm = orderService.addOrder(order);
				if(!dm){
					throw new CommonRollbackException("订单异常");
				}
				//订单详情
				OrderDetail orderDetail=new OrderDetail();
				orderDetail.setCreateDate(new Date());
				orderDetail.setImgAddress(e.getMer().getImgAddress());
				orderDetail.setMerCateName(e.getMer().getMerCate().getName());
				orderDetail.setMerId(e.getMerId());
				orderDetail.setName(e.getMer().getName());
				orderDetail.setNumber(e.getNumber());
				orderDetail.setOrderId(order.getOrderId());
				orderDetail.setUnitPrice(e.getMer().getUnitPrice());
				orderDetail.setUpdateDate(new Date());
				Double tp=Arith.mul(e.getNumber(), e.getMer().getUnitPrice());
				if(coupon!=null){
					orderDetail.setCouponId(couponId);
					tp=Arith.mul(tp, coupon.getDiscount());
				}
				orderDetail.setTotalPrice(tp);
				dm=orderDetailService.addOrderDetail(orderDetail);
				if(!dm){
					throw new CommonRollbackException("订单异常");
				}
				//库存不足
				if(mer.getStockNumber()-orderDetail.getNumber()<0){
					throw new CommonRollbackException("商品名："+mer.getName()+"库存不足");
				}
				//收货地址
				OrderReceiptInfo orderReceiptInfo=new OrderReceiptInfo();
				orderReceiptInfo.setAccountId(accountId);
				orderReceiptInfo.setAddress(receiptInfo.getAddress());
				orderReceiptInfo.setArea(receiptInfo.getArea());
				orderReceiptInfo.setCity(receiptInfo.getCity());
				orderReceiptInfo.setCountry(receiptInfo.getCountry());
				orderReceiptInfo.setCreateDate(new Date());
				orderReceiptInfo.setIsDefault(receiptInfo.getIsDefault());
				orderReceiptInfo.setName(receiptInfo.getName());
				orderReceiptInfo.setOrderId(order.getOrderId());
				orderReceiptInfo.setPhone(receiptInfo.getPhone());
				orderReceiptInfo.setPostcode(receiptInfo.getPostcode());
				orderReceiptInfo.setProvince(receiptInfo.getProvince());
				orderReceiptInfo.setTelephone(receiptInfo.getTelephone());
				orderReceiptInfo.setTelephoneArea(receiptInfo.getTelephoneArea());
				orderReceiptInfo.setTelephoneExtension(receiptInfo.getTelephoneExtension());
				orderReceiptInfo.setUpdateDate(new Date());
				
				orderReceiptInfoService.addOrderReceiptInfo(orderReceiptInfo);
				dm=cartMerDao.delCartMer(e.getCartMerId());
				if(!dm){
					throw new CommonRollbackException("订单异常");
				}
		}
		return dm;
	}
}
