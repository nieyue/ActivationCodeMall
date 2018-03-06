package com.nieyue.business;

import java.util.Random;

import org.springframework.context.annotation.Configuration;

import com.nieyue.util.DateUtil;

/**
 * 订单业务
 * @author 聂跃
 * @date 2018年3月2日
 */
@Configuration
public class OrderBusiness {
  
	/**
	 * 订单号生产
	 */
	public String getOrderNumber(Integer accountId){
		String orderNumber=null;
		orderNumber=accountId+DateUtil.getOrdersTime()+(new Random().nextInt(9000)+1000);
		return orderNumber;
	}
	public static void main(String[] args) {
		Integer accountId=90001;
		accountId=accountId>=90000?accountId:(accountId+10000);
		System.out.println(new Random().nextInt(9000));
	}
}
