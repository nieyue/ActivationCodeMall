package com.nieyue.schedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.nieyue.bean.Account;
import com.nieyue.bean.Config;
import com.nieyue.bean.Finance;
import com.nieyue.bean.Mer;
import com.nieyue.bean.Order;
import com.nieyue.bean.OrderDetail;
import com.nieyue.service.AccountService;
import com.nieyue.service.ConfigService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.MerService;
import com.nieyue.service.OrderService;
import com.nieyue.util.Arith;
import com.nieyue.util.DateUtil;

/**
 * 订单任务
 * @author 聂跃
 * @date 2018年7月11日
 */
@Configuration
@EnableScheduling
public class OrderTask {
	@Autowired
	ConfigService configService;
	@Autowired
	AccountService accountService;
	@Autowired
	FinanceService financeService;
	@Autowired
	MerService merService;
	@Autowired
	OrderService orderService;
	/**
	 * 转完成订单
	 * 订单状态，2待支付，3已支付,4预购商品，5问题单，6已取消，7已删除
	 * 
	 * 子状态，2（1待支付），3（1冻结单,2已完成），
	 * 4（1等待发货），
	 * 5（1待解决（买家提问后），2解决中（卖家回复后），3申请退款，4已退款，5已解决），
	 * 6（1正常取消,2订单商品库存不够），
	 * 7（1已删除）
	 */
	@Scheduled(cron="0 0 4 * * ?")
	public void turnOrderComplate(){
		//System.out.println(new Date().toLocaleString());
		//1.只有3.1冻结单，5.5问题已解决单需要定时的查询，订单支付时间大于等于冻结天数，才需要
		Date paymentDate=DateUtil.getStartTime(new Date(new Date().getTime()+1000*60*60*24*7));
		List<Order> frozenOrderList = orderService.browsePagingOrder(null, null, null, null, null, null, null, 3, 1, null, null, paymentDate, 1, Integer.MAX_VALUE, "order_id", "asc");
		List<Order> completeOrderList = orderService.browsePagingOrder(null, null, null, null, null, null, null, 5, 5, null, null, paymentDate, 1, Integer.MAX_VALUE, "order_id", "asc");
		completeOrderList.addAll(frozenOrderList);
		int coll=completeOrderList.size();
		boolean b=false;
		//配置类
		List<Config> configlist = configService.browsePagingConfig(1, 1, "config_id", "asc");
		Config config = configlist.get(0);
		for (int i = 0; i < coll; i++) {
			Order completeOrder = completeOrderList.get(i);
			completeOrder.setStatus(3);//已支付
			completeOrder.setStatus(2);//已完成
			b = orderService.updateOrder(completeOrder);
			OrderDetail orderDetail = completeOrder.getOrderDetailList().get(0);
			if(orderDetail==null){
				continue;
			}
			//获取当前商品
			Mer mer = merService.loadMer(completeOrder.getOrderDetailList().get(0).getMerId());
			mer.setSaleNumber(mer.getSaleNumber()+orderDetail.getNumber());
			merService.updateMer(mer);
			//获取商户
			Account merchantAccount=null;
			if(completeOrder.getMerchantAccountId()!=null){
				merchantAccount = accountService.loadAccount(completeOrder.getMerchantAccountId());
			}
			/**
			 * 商户金额
			 */
			List<Finance> mafl=financeService.browsePagingFinance(null, merchantAccount.getAccountId(), 1, 1, "finance_id", "asc");
			if(mafl.size()>0){
				Finance maf = mafl.get(0);
				//冻结金额=已有冻结金额-订单金额
				Double mafrozen=Arith.sub(maf.getMoney(),Arith.sub(orderDetail.getTotalPrice(),Arith.mul(orderDetail.getTotalPrice(), Arith.div(mer.getPlatformProportion(), 100))));
				maf.setFrozen(mafrozen);
				//余额=现有余额+冻结金额
				maf.setMoney(Arith.add(maf.getMoney(), mafrozen));
				b=financeService.updateFinance(maf);
			}
			//获取推广户
			Account spreadAccount=null;
			if(completeOrder.getSpreadAccountId()!=null){
				spreadAccount = accountService.loadAccount(completeOrder.getSpreadAccountId());
			}
			/**
			 * 推广户金额
			 */
			List<Finance> sal=financeService.browsePagingFinance(null, spreadAccount.getAccountId(), 1, 1, "finance_id", "asc");
			if(sal.size()>0){
				Finance sa = sal.get(0);
				//冻结金额=已有冻结金额-推广金额
				Double safrozen=Arith.sub(sa.getMoney(),Arith.mul(orderDetail.getTotalPrice(),Arith.div(config.getSpreadProportion(), 100)));
				sa.setFrozen(safrozen);
				//余额=现有余额+推广金额
				sa.setMoney(Arith.add(sa.getMoney(), safrozen));
				b=financeService.updateFinance(sa);
			}
		}
	}
}
