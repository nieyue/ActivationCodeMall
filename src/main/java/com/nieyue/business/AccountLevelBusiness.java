package com.nieyue.business;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.nieyue.bean.AccountLevel;
import com.nieyue.service.AccountLevelService;
/**
 * 团购等级业务
 * @author 聂跃
 * @date 2017年8月19日
 */
@Configuration
public class AccountLevelBusiness {
	@Resource
	AccountLevelService accountLevelService;
	/**
	 * 根据businessId获取账户等级
	 * @return false 不包含，  true 包含
	 */
	public AccountLevel getAccountLevel(Integer businessId){
		AccountLevel al = accountLevelService.loadAccountLevel(businessId);
		return al;
	}
	
}
