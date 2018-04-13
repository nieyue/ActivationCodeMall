package com.nieyue.business;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.nieyue.bean.Config;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.service.ConfigService;
import com.nieyue.util.Arith;

/**
 * 配置业务
 */
@Configuration
public class ConfigBusiness {
	@Resource
	ConfigService configService;

	/**
	 * 获取配置对象
	 */
	public Config getConfig(){
		List<Config> cl=configService.browsePagingConfig(1, 1, "config_id", "asc");
		if(cl.size()==1){
			Config config=cl.get(0);
			return config;
		}
		return null;
	}
	/**
	 * 获取提现金额
	 * @param money 提现金额
	 * return realMoney到账金额
	 */
	public Double getWithdrawalsMoney(Double money){
		Config config=getConfig();
		Double realMoney=0.0;
		//如果提现金额小于提现最低额度，则不能提现
		if(money-config.getMinWithdrawals()<0){
			throw new CommonRollbackException("提现金额小于最低额度");
		}
		//只有提现金额大于五手续费最低金额，才无手续费
		else if(money>config.getWithdrawalsMinBrokerage()){
			realMoney=money;
		}else{
			//手续费
			Double brokerage=Arith.mul(money, config.getWithdrawalsProportion());
			realMoney=Arith.sub(money, brokerage);
		}
		return realMoney;
	}
}
