package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.SpreadLink;
import com.nieyue.bean.SpreadLinkTerm;
import com.nieyue.dao.SpreadLinkTermDao;
import com.nieyue.service.AccountService;
import com.nieyue.service.SpreadLinkService;
import com.nieyue.service.SpreadLinkTermService;
@Service
public class SpreadLinkTermServiceImpl implements SpreadLinkTermService{
	@Resource
	SpreadLinkTermDao spreadLinkTermDao;
	@Resource
	SpreadLinkService spreadLinkService;
	@Resource
	AccountService accountService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addSpreadLinkTerm(SpreadLinkTerm spreadLinkTerm) {
		boolean b=false;
		spreadLinkTerm.setCreateDate(new Date());
		b = spreadLinkTermDao.addSpreadLinkTerm(spreadLinkTerm);
		if(b){//系统通知
			//通知到所有人
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					List<Account> al = accountService.browsePagingAccount(null, null, null, null, null, null, null, null, null, 1, Integer.MAX_VALUE, "account_id", "asc");
					al.forEach((account)->{
						Integer aid = account.getAccountId();
						SpreadLink sl=new SpreadLink();
						String link=spreadLinkTerm.getLink();
						if(link.indexOf("?")>0||
								link.indexOf("？")>0){
							link+="&accountId="+aid;
						}else{
							link+="?accountId="+aid;
						}
						sl.setLink(link);
						sl.setSpreadNumber(0);
						sl.setAccountId(aid);
						spreadLinkService.addSpreadLink(sl);
					});
				}
			}).start();
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delSpreadLinkTerm(Integer spreadLinkTermId) {
		boolean b = spreadLinkTermDao.delSpreadLinkTerm(spreadLinkTermId);
		if(b){
			//通知到所有人
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					List<Account> al = accountService.browsePagingAccount(null, null, null, null, null, null, null, null, null, 1, Integer.MAX_VALUE, "account_id", "asc");
					al.forEach((account)->{
						Integer aid = account.getAccountId();
						List<SpreadLink> sll = spreadLinkService.browsePagingSpreadLink(aid, 1, Integer.MAX_VALUE, "spread_link_id", "asc");
						if(sll.size()==1){
						SpreadLink spreadLink=sll.get(0);
						spreadLinkService.delSpreadLink(spreadLink.getSpreadLinkId());
						}
					});
				}
			}).start();
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateSpreadLinkTerm(SpreadLinkTerm spreadLinkTerm) {
		boolean b = spreadLinkTermDao.updateSpreadLinkTerm(spreadLinkTerm);
		if(b){
			//通知到所有人
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					List<Account> al = accountService.browsePagingAccount(null, null, null, null, null, null, null, null, null, 1, Integer.MAX_VALUE, "account_id", "asc");
					al.forEach((account)->{
						Integer aid = account.getAccountId();
						List<SpreadLink> sll = spreadLinkService.browsePagingSpreadLink(aid, 1, Integer.MAX_VALUE, "spread_link_id", "asc");
						if(sll.size()==1){
							SpreadLink sl=sll.get(0);
							String link=spreadLinkTerm.getLink();
							if(link.indexOf("?")>0||
									link.indexOf("？")>0){
								link+="&accountId="+aid;
							}else{
								link+="?accountId="+aid;
							}
							sl.setLink(link);
							sl.setSpreadNumber(sl.getSpreadNumber());
							sl.setAccountId(aid);
							spreadLinkService.updateSpreadLink(sl);
						}
					});
				}
			}).start();
		}
		return b;
	}

	@Override
	public SpreadLinkTerm loadSpreadLinkTerm(Integer spreadLinkTermId) {
		SpreadLinkTerm r = spreadLinkTermDao.loadSpreadLinkTerm(spreadLinkTermId);
		return r;
	}

	@Override
	public int countAll() {
		int c = spreadLinkTermDao.countAll();
		return c;
	}

	@Override
	public List<SpreadLinkTerm> browsePagingSpreadLinkTerm(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<SpreadLinkTerm> l = spreadLinkTermDao.browsePagingSpreadLinkTerm(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
