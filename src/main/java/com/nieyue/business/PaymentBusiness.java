package com.nieyue.business;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.nieyue.bean.Account;
import com.nieyue.bean.AccountLevel;
import com.nieyue.bean.Config;
import com.nieyue.bean.Finance;
import com.nieyue.bean.FinanceRecord;
import com.nieyue.bean.Integral;
import com.nieyue.bean.IntegralDetail;
import com.nieyue.bean.Mer;
import com.nieyue.bean.MerCardCipher;
import com.nieyue.bean.MerOrderCardCipher;
import com.nieyue.bean.Notice;
import com.nieyue.bean.Order;
import com.nieyue.bean.OrderDetail;
import com.nieyue.bean.Payment;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.AccountLevelService;
import com.nieyue.service.AccountService;
import com.nieyue.service.ConfigService;
import com.nieyue.service.FinanceRecordService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.IntegralDetailService;
import com.nieyue.service.IntegralService;
import com.nieyue.service.MerCardCipherService;
import com.nieyue.service.MerOrderCardCipherService;
import com.nieyue.service.MerService;
import com.nieyue.service.NoticeService;
import com.nieyue.service.OrderDetailService;
import com.nieyue.service.OrderService;
import com.nieyue.service.SpreadLinkService;
import com.nieyue.service.SpreadOrderAccountService;
import com.nieyue.service.SpreadRecordService;
import com.nieyue.util.Arith;

/**
 * 支付业务
 */
@Configuration
public class PaymentBusiness {
	@Resource
	AccountLevelService accountLevelService;
	@Resource
	AccountService accountService;
	@Resource
	OrderService orderService;
	@Resource
	OrderDetailService orderDetailService;
	@Resource
	MerService merService;
	@Resource
	NoticeService noticeService;
	@Resource
	NoticeBusiness noticeBusiness;
	@Resource
	MerCardCipherService merCardCipherService;
	@Resource
	MerOrderCardCipherService merOrderCardCipherService;
	@Resource
	IntegralService integralService;
	@Resource
	IntegralDetailService integralDetailService;
	@Resource
	FinanceService financeService;
	@Resource
	FinanceRecordService financeRecordService;
	@Resource
	SpreadOrderAccountService spreadOrderAccountService;
	@Resource
	SpreadRecordService spreadRecordService;
	@Resource
	SpreadLinkService spreadLinkService;
	@Resource
	ConfigService configService;

	/**
	 * 支付回调，订单进入冻结期
	 * payType 支付方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
	 * accountId 下单人id外键
	 * ids 订单id列表如:“101,3,44”
	 */
	public String getPaymentNotify(
			Payment payment){
		boolean b=false;
		String orderIds=payment.getBusinessNotifyUrl();//存放订单id集合
		String orderNumber=payment.getOrderNumber();//支付单号
		Integer accountId=payment.getAccountId();//用户id
		Integer payType=payment.getType();//方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
		Integer type=payment.getBusinessType();//类型，1购买商品，2账户提现，3退款，4诚信押金
		Double totalPrice=payment.getMoney();//订单总价
		
		String[] ids = orderIds.replace(" ","").split(",");
		List<Order> listorder=new ArrayList<>();//临时存储订单
		List<Mer> listmer=new ArrayList<>();//临时存储商品
		boolean isHaveStockNumber=true;//是否有库存，默认有
		for (int i = 0; i < ids.length; i++) {
			//1.判读库存是否足够
			Order order = orderService.loadOrder(new Integer(ids[i]));
			if(order==null){
				//只要一个订单不存在.取消
				isHaveStockNumber=false;
				break;
			}
			listorder.add(i, order);//存入内存
			OrderDetail orderDetail = order.getOrderDetailList().get(0);
			Mer mer = merService.loadMer(orderDetail.getMerId());
			listmer.add(i,mer);
			if(mer==null||mer.getStockNumber()-orderDetail.getNumber()<0){
				//只要一个不够订单取消
				isHaveStockNumber=false;
				break;
			}
		}
		//调退款接口
		if(!isHaveStockNumber){
			//退款
			//订单取消
			for (int i = 0; i < ids.length; i++) {
			Order order = listorder.get(i);
			order.setStatus(6);//已取消
			order.setSubstatus(2);//订单商品库存不够取消
			order.setUpdateDate(new Date());
			order.setPaymentDate(new Date());
			b =orderService.updateOrder(order);
			}
			return "success";
		}
		
		//所有账户等级
		List<AccountLevel> all = accountLevelService.browsePagingAccountLevel(null, 1, Integer.MAX_VALUE, "account_level_id", "asc");
		//配置类
		List<Config> configlist = configService.browsePagingConfig(1, 1, "config_id", "asc");
		Config config = configlist.get(0);
		//用户积分
		List<Integral> ail=integralService.browsePagingIntegral(accountId, null, null, 1, 1, "integral_id", "asc");
		if(ail.size()>0){
			Integral ai = ail.get(0);
			//剩余积分=现有+总金额*用户每消费一元钱获得积分
			ai.setIntegral(Arith.add(ai.getIntegral(),Arith.mul(totalPrice,config.getUserIntegralPer())));
			//循环一遍
			for (int j = 0; j < all.size(); j++) {
			AccountLevel al = all.get(j);
			//判断现有积分在哪个等级
			if(ai.getIntegral()>=al.getUserUpgradeIntegral()){
				ai.setName(al.getName());//改名称
				ai.setLevel(al.getLevel());//改等级
			}
			}
			//找到升级积分
			for (int j = 0; j < all.size(); j++) {
				AccountLevel al = all.get(j);
				//当前等级+1的等级积分为升级积分
				if(ai.getLevel()+1==al.getLevel()){
					ai.setUpgradeIntegral(al.getUserUpgradeIntegral());
				}
			}
			b=integralService.updateIntegral(ai);//更新用户积分
		}
		//用户金额
		List<Finance> afl=financeService.browsePagingFinance(null, accountId, 1, 1, "finance_id", "asc");
		if(afl.size()>0){
			Finance af = afl.get(0);
			af.setConsume(Arith.add(af.getConsume(), totalPrice));//增加消费金额
			b=financeService.updateFinance(af);
		}
		//循环订单
		for (int i = 0; i < ids.length; i++) {
			//2.订单进入冻结期
			Order order = listorder.get(i);//从内存中取
			//Order order = orderService.loadOrder(new Integer(ids[i]));
			
			order.setStatus(3);//已支付
			order.setSubstatus(1);//冻结单
			order.setUpdateDate(new Date());
			order.setPaymentDate(new Date());
			b =orderService.updateOrder(order);
			
			//3.获取订单详情
			OrderDetail orderDetail = order.getOrderDetailList().get(0);
			//4.获取当前商品
			Mer lm = listmer.get(i);
			//5.获取用户
			Account account = accountService.loadAccount(accountId);
			//6.获取商户
			Account merchantAccount=null;
			if(order.getMerchantAccountId()!=null){
				merchantAccount = accountService.loadAccount(order.getMerchantAccountId());
			}
			//7.获取推广户
			Account spreadAccount=null;
			if(order.getSpreadAccountId()!=null){
				spreadAccount = accountService.loadAccount(order.getSpreadAccountId());
			}
			//8.生产订单卡密
			List<MerCardCipher> mccl = merCardCipherService.browsePagingMerCardCipher(lm.getMerId(), 1, Integer.MAX_VALUE, "create_date", "asc");
			for (int j = 0; j < mccl.size(); j++) {
				MerOrderCardCipher mocc=new MerOrderCardCipher();
				MerCardCipher mcc = mccl.get(j);
				mocc.setCode(mcc.getCode());
				mocc.setCreateDate(new Date());
				mocc.setImgAddress(mcc.getImgAddress());
				mocc.setOrderId(order.getOrderId());
				merOrderCardCipherService.addMerOrderCardCipher(mocc);
			}
			//9.删除商品卡密,自动减库存
			for (int j = 0; j < mccl.size(); j++) {
				MerCardCipher mcc = mccl.get(j);
				merCardCipherService.delMerCardCipher(mcc.getMerCardCipherId());
			}
			//10.发送通知给购买人
			Notice notice=new Notice();
			notice.setBusinessId(order.getOrderId());
			notice.setTitle("订单商品动态");
			notice.setCreateDate(new Date());
			notice.setUpdateDate(new Date());
			notice.setImgAddress(order.getOrderDetailList().get(0).getImgAddress());
			notice.setAccountId(order.getAccountId());
			notice.setRegion(2);//1全局，2是个人
			notice.setStatus(0);//1审核中，2申请成功，3申请失败,个人为0，代表正常
			notice.setType(7);
			//类型，1系统消息，2申请新产品销售，3新增商品类型，4商品申请自营，5提现申请，6问题单反馈,7订单商品动态
			notice.setIsMerDynamic(notice.getType());
			notice.setContent(noticeBusiness.getContentByType(notice));
			noticeService.addNotice(notice);
			
			//11.用户积分记录
			IntegralDetail aid = new IntegralDetail();
			//剩余积分=现有+一个订单金额*用户每消费一元钱获得积分
			aid.setIntegral(Arith.add(aid.getIntegral(), Arith.mul(orderDetail.getTotalPrice(), config.getUserIntegralPer())));
			aid.setType(0);//0增长积分
			aid.setAccountId(accountId);
			b=integralDetailService.addIntegralDetail(aid);//新增用户积分记录
			
			//12.用户财务记录，购买商品
			FinanceRecord fr=new FinanceRecord();
			fr.setAccountId(accountId);
			fr.setMethod(payType);//方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
			fr.setType(type);//类型，1购买商品，2提现记录，3退款记录（用户），4诚信押金，5进账记录，6被退款记录（商户）'
			fr.setTransactionNumber(orderNumber);//交易单号与支付单号相同
			fr.setBrokerage(0.0);//提现才有手续费
			if(!StringUtils.isEmpty(account.getRealname())){
				fr.setRealname(account.getRealname());				
			}else{
				fr.setRealname(account.getNickname());								
			}
			fr.setMoney(orderDetail.getTotalPrice());
			fr.setStatus(2);//状态，默认1待处理，2成功，3已拒绝
			//不存在商户，就相同
			if(merchantAccount==null){				
				fr.setRealMoney(orderDetail.getTotalPrice());
			}else{
				//实际金额=总金额-总金额*当前商品平台分成比例
				fr.setRealMoney(Arith.sub(orderDetail.getTotalPrice(),Arith.mul(orderDetail.getTotalPrice(), lm.getPlatformProportion())));
			}
			b=financeRecordService.addFinanceRecord(fr);//新增财务记录
			//如果存在商户
			if(merchantAccount!=null){				
			//13.商户积分记录
			IntegralDetail maid = new IntegralDetail();
			//剩余积分=现有+一个订单金额*商户每消费一元钱获得积分
			maid.setIntegral(Arith.add(maid.getIntegral(), Arith.mul(orderDetail.getTotalPrice(), config.getSellerIntegralPer())));
			maid.setType(0);//0增长积分
			maid.setAccountId(merchantAccount.getAccountId());
			b=integralDetailService.addIntegralDetail(maid);//新增商户积分记录
			
			//14.商户财务记录，进账记录
			FinanceRecord mafr=new FinanceRecord();
			mafr.setAccountId(merchantAccount.getAccountId());
			mafr.setMethod(payType);//方式，1支付宝，2微信,3百度钱包,4Paypal,5网银
			mafr.setType(5);//类型，1购买商品，2提现记录，3退款记录（用户），4诚信押金，5进账记录，6被退款记录（商户）'
			mafr.setTransactionNumber(orderNumber);//交易单号与支付单号相同
			mafr.setBrokerage(0.0);//提现才有手续费
			if(!StringUtils.isEmpty(account.getRealname())){
				mafr.setRealname(account.getRealname());				
			}else{
				mafr.setRealname(account.getNickname());								
			}
			mafr.setMoney(orderDetail.getTotalPrice());
			mafr.setStatus(2);//状态，默认1待处理，2成功，3已拒绝
			//实际金额=总金额-总金额*当前商品平台分成比例
			mafr.setRealMoney(Arith.sub(orderDetail.getTotalPrice(),Arith.mul(orderDetail.getTotalPrice(), lm.getPlatformProportion())));
			b=financeRecordService.addFinanceRecord(mafr);//新增财务记录
			}
			//如果存在推广户（推广户没有积分记录，没有财务记录）
			if(spreadAccount!=null){	
			//15.推广链接推广次数增加 ,推广链接是由domainurl/home/gooddetail.html?goodid=1  ,其中goodid为商品id即merId
			//spreadLinkService.browsePagingSpreadLink(accountId, 1, Integer.MAX_VALUE, orderName, orderWay)
			}
			
		}
	
		return "success";
	}
}
