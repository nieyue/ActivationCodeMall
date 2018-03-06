package com.nieyue.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.nieyue.bean.AccountLevel;
import com.nieyue.bean.AccountParent;
import com.nieyue.service.AccountLevelService;
import com.nieyue.service.AccountParentService;
/**
 * 团购等级业务
 * @author 聂跃
 * @date 2017年8月19日
 */
@Configuration
public class AccountLevelBusiness {
	@Resource
	AccountParentService accountParentService;
	@Resource
	AccountLevelService accountLevelService;
	/**
	 * 根据accountId获取 团购等级列表
	 * @return
	 */
	public List<AccountLevel> getTeamAccountLevelListByAccountId(Integer accountId){
		List<AccountLevel> alist=new ArrayList<>();
		List<AccountParent> apl = accountParentService.browsePagingAccountParent(null, null, accountId, null, null, null, null, 1, 1, "account_parent_id", "asc");
		if(apl.size()==1){
			AccountParent ap = apl.get(0);//自身的
			Integer rmid = ap.getRealMasterId();//真实上级id
			AccountLevel selfal = accountLevelService.loadAccountLevel(ap.getAccountLevelId());
			Integer selfallevel=selfal.getLevel();//自身等级
			List<AccountParent> rmpl = accountParentService.browsePagingAccountParent(null, null, rmid, null, null, null, null, 1, 1, "account_parent_id", "asc");
			if(rmpl.size()==1){
				AccountParent rmp = rmpl.get(0);//真实上级的
			AccountLevel rmal = accountLevelService.loadAccountLevel(rmp.getAccountLevelId());
			Integer rmalevel = rmal.getLevel();//真实上级等级
			List<AccountLevel> all = accountLevelService.browsePagingAccountLevel(null, null, 1, Integer.MAX_VALUE, "account_level_id", "asc");
			for (int i = 0; i < all.size(); i++) {
				AccountLevel al = all.get(i);
				if(al.getLevel()>selfallevel && al.getLevel()<=rmalevel){//大于自身等级，小于等于真实上级等级
					alist.add(al);
				}
			}
			}
		}
		return alist;
	}
	/**
	 * 此businessId是否包含在此accountId账户返回的 团购等级列表范围内
	 * @return false 不包含，  true 包含
	 */
	public boolean isContain(Integer accountId,Integer businessId){
		boolean b=false;
		List<AccountLevel> tall = getTeamAccountLevelListByAccountId(accountId);
		if(tall.size()<=0){
			return b;
		}
		for (int i = 0; i < tall.size(); i++) {
			AccountLevel tal = tall.get(i);
			if(tal.getAccountLevelId().equals(businessId)){
				b=true;
				break;
			}
		}
		return b;
	}
	/**
	 * 根据businessId获取账户等级
	 * @return false 不包含，  true 包含
	 */
	public AccountLevel getAccountLevel(Integer businessId){
		AccountLevel al = accountLevelService.loadAccountLevel(businessId);
		return al;
	}
	/**
	 * 获取当前账户id的账户等级id
	 * @return false 不包含，  true 包含
	 */
	public AccountLevel getAccountLevelByAccountId(Integer accountId){
		List<AccountParent> apl = accountParentService.browsePagingAccountParent(null, null, accountId, null, null, null, null, 1, 1, "account_parent_id", "asc");
		AccountLevel al=null;
		if(apl.size()==1){
			AccountParent ap = apl.get(0);//真实上级的
		 al = accountLevelService.loadAccountLevel(ap.getAccountLevelId());
		}
		return al;
	}
	
}
