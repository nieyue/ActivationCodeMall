package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.AccountLevel;
import com.nieyue.bean.AccountParent;
import com.nieyue.bean.Finance;
import com.nieyue.bean.FinanceRecord;
import com.nieyue.bean.Order;
import com.nieyue.bean.Split;
import com.nieyue.bean.TeamPurchaseInfo;
import com.nieyue.bean.Vip;
import com.nieyue.bean.VipGrowthRecord;
import com.nieyue.bean.VipNumber;
import com.nieyue.business.AccountLevelBusiness;
import com.nieyue.business.FinanceBusiness;
import com.nieyue.dao.SplitDao;
import com.nieyue.exception.PayException;
import com.nieyue.service.AccountParentService;
import com.nieyue.service.AccountService;
import com.nieyue.service.FinanceRecordService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.OrderService;
import com.nieyue.service.SplitService;
import com.nieyue.service.TeamPurchaseInfoService;
import com.nieyue.service.VipGrowthRecordService;
import com.nieyue.service.VipNumberService;
import com.nieyue.service.VipService;
import com.nieyue.util.DateUtil;
@Service
public class SplitServiceImpl implements SplitService{
	@Resource
	SplitDao splitDao;
	@Resource
	private VipNumberService vipNumberService;
	@Resource
	private AccountParentService accountParentService;
	@Resource
	private FinanceService financeService;
	@Resource
	private TeamPurchaseInfoService teamPurchaseInfoService;
	@Resource
	private FinanceBusiness financeBusiness;
	@Resource
	private FinanceRecordService financeRecordService;
	@Resource
	private AccountLevelBusiness accountLevelBusiness;
	@Resource
	private VipService vipService;
	@Resource
	private OrderService orderService;
	@Resource
	private VipGrowthRecordService vipGrowthRecordService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addSplit(Split split) {
		split.setCreateDate(new Date());
		split.setUpdateDate(new Date());
		boolean b = splitDao.addSplit(split);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delSplit(Integer splitId) {
		boolean b =splitDao.delSplit(splitId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateSplit(Split split) {
		split.setUpdateDate(new Date());
		boolean b = splitDao.updateSplit(split);
		return b;
	}

	@Override
	public Split loadSplit(Integer splitId) {
		Split r = splitDao.loadSplit(splitId);
		return r;
	}

	@Override
	public int countAll(
			Integer recommendAccountId,
			Integer accountId,
			Integer buyAccountId,
			Date applyDate,
			Date splitDate,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = splitDao.countAll(
				recommendAccountId,
				accountId,buyAccountId,applyDate,splitDate,createDate,updateDate,status);
		return c;
	}

	@Override
	public List<Split> browsePagingSplit(
			Integer recommendAccountId,
			Integer accountId,
			Integer buyAccountId,
			Date applyDate,
			Date splitDate,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Split> l = splitDao.browsePagingSplit(
				recommendAccountId,
				accountId,
				buyAccountId,
				applyDate,
				splitDate,
				createDate,
				updateDate,
				status,
				pageNum-1,
				pageSize,
				orderName,
				orderWay);
		return l;
	}
	@Override
	public boolean recommendParent(Integer splitId, Integer accountId) {
		boolean b=false;
		Split split =splitDao.loadSplit(splitId);
		if(!split.getAccountId().equals(accountId)){
			return b;
		}
		//每次推荐，增加次数	
		List<VipNumber> vnl = vipNumberService.browsePagingVipNumber(null, split.getBuyAccountId(), split.getAccountId(), null, null, null, 1, 1, "vip_number_id", "desc");
				VipNumber vn = vnl.get(0);
			if(vnl.size()<=0){
				VipNumber vipNumber=new VipNumber();
				vipNumber.setNumber(1);
				vipNumber.setAccountId( split.getBuyAccountId());
				vipNumber.setRealMasterId(split.getAccountId());
				vipNumber.setStatus(1);//待处理
				vipNumberService.addVipNumber(vipNumber);
			}else{
				vn.setNumber(vn.getNumber()+1);
				if(vn.getNumber()>=3){
					vn.setStatus(3);//已超次
				}
				vipNumberService.updateVipNumber(vn);
			}
		//上级的拆分状态改为 已推荐给上级
			split.setStatus(4);
			b=splitDao.updateSplit(split);
		//新增上上级别的
			Split nsplit=new Split();
			nsplit.setRecommendAccountId(split.getAccountId());//上级变为推荐人
			List<AccountParent> apl = accountParentService.browsePagingAccountParent(null, null, split.getAccountId(), null, null, null, null, 1, 1, "account_parent_id", "asc");
			if(apl.size()!=1){
				throw new PayException();
			}
			AccountParent ap = apl.get(0);
			nsplit.setOrderId(split.getOrderId());
			nsplit.setAccountId(ap.getRealMasterId());//设置当前上级的真实上级id
			nsplit.setApplyDate(split.getApplyDate());
			nsplit.setBuyAccountId(split.getBuyAccountId());
			nsplit.setCreateDate(new Date());
			nsplit.setNumber(split.getNumber());
			nsplit.setPrice(split.getPrice());
			nsplit.setNickname(split.getNickname());
			nsplit.setPhone(split.getPhone());
			nsplit.setContactPhone(split.getContactPhone());
			nsplit.setSplitDate(null);
			nsplit.setStatus(0);//0是已申请
			nsplit.setUpdateDate(new Date());
			nsplit.setRemark("无");
			b = splitDao.addSplit(nsplit);//推荐过来的团购
			if(!b){
				throw new PayException();
			}
			//推荐人的团购信息修改
			List<TeamPurchaseInfo> tpil = teamPurchaseInfoService.browsePagingTeamPurchaseInfo(split.getAccountId(), null, null, 1, 1, "team_purchase_info_id", "asc");
			TeamPurchaseInfo tpi = tpil.get(0);
			tpi.setUpdateDate(new Date());//更新时间
			tpi.setAlreadyRecommend(tpi.getAlreadyRecommend()+split.getNumber());//已推荐给上级张数
			tpi.setAlreadyRecommendPrice(tpi.getAlreadyRecommendPrice()+split.getPrice());//已推荐个上级总额
			tpi.setAlreadyRecommendUpdateDate(new Date());//已推荐给上级更新时间
			b=teamPurchaseInfoService.updateTeamPurchaseInfo(tpi);
			if(!b){
				throw new PayException();
			}
			//团购人真实上级自动变为被推荐者
			List<AccountParent> tapl = accountParentService.browsePagingAccountParent(null, null, split.getBuyAccountId(), null, null, null, null, 1, 1, "account_parent_id", "asc");
			if(tapl.size()!=1){
				throw new PayException();
			}
			AccountParent tap = tapl.get(0);
			tap.setRealMasterId(ap.getRealMasterId());
			b=accountParentService.updateAccountParent(tap);
		return b;
	}
	@Override
	public boolean immediatelySplit(Integer splitId, Integer accountId) {
		boolean b=false;
		//获取拆分表
		Split split =splitDao.loadSplit(splitId);
		if(!split.getAccountId().equals(accountId)){
			return b;
		}
				//拆分人的团购信息
				List<TeamPurchaseInfo> stpil = teamPurchaseInfoService.browsePagingTeamPurchaseInfo(accountId, null, null, 1, 1, "team_purchase_info_id", "asc");
					//拆分人团购信息表
					TeamPurchaseInfo stpi = stpil.get(0);
					if(stpi.getTeamPurchaseCardAllowance()<10){//小于10张报警
						b=financeBusiness.buzuNotice(accountId,stpi.getTeamPurchaseCardAllowance());
					}
					//团购卡小于拆分数，直接失败，记录次数
					if(stpi.getTeamPurchaseCardAllowance()<split.getNumber()){
						List<VipNumber> vnl = vipNumberService.browsePagingVipNumber(null, split.getBuyAccountId(), accountId, null, null, null, 1, 1, "vip_number_id", "desc");
							VipNumber vn = vnl.get(0);
						if(vnl.size()<=0){
							VipNumber vipNumber=new VipNumber();
							vipNumber.setNumber(1);
							vipNumber.setAccountId(split.getBuyAccountId());
							vipNumber.setRealMasterId(accountId);
							vipNumber.setStatus(1);//待处理
							vipNumberService.addVipNumber(vipNumber);
						}else{
							vn.setNumber(vn.getNumber()+1);
							if(vn.getNumber()>=3){
								vn.setStatus(3);//已超次
							}
							vipNumberService.updateVipNumber(vn);
						}
						return false;//让部分执行
					}
		//获取订单
		Order order = orderService.loadOrder(split.getOrderId());
		Integer businessId=order.getOrderDetailList().get(0).getBusinessId();
		Integer type=order.getType();
		Integer payType=order.getPayType();
		AccountLevel accountLevel = accountLevelBusiness.getAccountLevel(businessId);
		/**
		 * 成功之后，团购人
		 */
		//vip等级修改上升
		List<Vip> vipl = vipService.browsePagingVip(split.getBuyAccountId(), null, null, 1, 1, "vip_id", "asc");
		Vip vip = vipl.get(0);
		vip.setExpireDate(DateUtil.nextYear(new Date(), 1));//一年
		vip.setLevel(accountLevel.getLevel());
		vip.setName(accountLevel.getName());
		vip.setStatus(1);//没到期
		b=vipService.updateVip(vip);
		if(!b){
			throw new PayException();
		}
		//账户上级的等级升高
		List<AccountParent> buyapl = accountParentService.browsePagingAccountParent(null, null, split.getBuyAccountId(), null, null, null, null, 1, 1, "account_parent_id", "asc");
		AccountParent buyap = buyapl.get(0);
		buyap.setAccountLevelId(accountLevel.getAccountLevelId());
		buyap.setName(accountLevel.getName());
		buyap.setRealMasterId(accountId);//真实上级改为拆分的
		b=accountParentService.updateAccountParent(buyap);
		if(!b){
			throw new PayException();
		}
		//vipgroup增加记录
		VipGrowthRecord vipGrowthRecord=new VipGrowthRecord();
		vipGrowthRecord.setName(accountLevel.getName());
		vipGrowthRecord.setLevel(accountLevel.getLevel());
		vipGrowthRecord.setCreateDate(new Date());
		vipGrowthRecord.setAccountId(split.getBuyAccountId());
		vipGrowthRecord.setPrice(accountLevel.getTeamPurchasePrice());
		b=vipGrowthRecordService.addVipGrowthRecord(vipGrowthRecord);
		if(!b){
			throw new PayException();
		}
		//团购人的团购信息修改
		 List<TeamPurchaseInfo> tpil = teamPurchaseInfoService.browsePagingTeamPurchaseInfo(split.getBuyAccountId(), null, null, 1, 1, "team_purchase_info_id", "asc");
			if(tpil.size()!=1){
				throw new PayException();
			}
			TeamPurchaseInfo tpi = tpil.get(0);
			tpi.setUpdateDate(new Date());//更新时间
			tpi.setTeamPurchaseCardAllowance(tpi.getTeamPurchaseCardAllowance()+accountLevel.getNumber());//团购卡余量增加
			tpi.setWaitDispose(tpi.getWaitDispose()-accountLevel.getNumber());//待处理（张）
			tpi.setWaitDisposePrice(tpi.getWaitDisposePrice()-accountLevel.getTeamPurchasePrice());//待处理总额
			tpi.setWaitDisposeUpdateDate(new Date());//待处理更新时间
			tpi.setTeamPurchaseSuccess(tpi.getTeamPurchaseSuccess()+accountLevel.getNumber());//团购成功张数
			tpi.setTeamPurchaseSuccessPrice(tpi.getTeamPurchaseSuccessPrice()+accountLevel.getTeamPurchasePrice());//团购成功总额
			tpi.setTeamPurchaseSuccessUpdateDate(new Date());
			b=teamPurchaseInfoService.updateTeamPurchaseInfo(tpi);
	/**
	 * 推荐人	
	 */
	if(split.getRecommendAccountId()!=null&&!split.getRecommendAccountId().equals("")){
		//推荐人的财务信息
		List<Finance> xfl = financeService.browsePagingFinance(null, split.getRecommendAccountId(), 1, 1, "finance_id", "asc");
		Finance xf = xfl.get(0);
		//推荐人推荐佣金
		Double recommendCommission = accountLevel.getRecommendCommission();
		xf.setMoney(xf.getMoney()+recommendCommission);//余额
		xf.setRecommendCommission(xf.getRecommendCommission()+recommendCommission);//推荐佣金
		b=financeService.updateFinance(xf);
		if(!b){
			throw new PayException();
		}
		//推荐人财务记录
		FinanceRecord xfr=new FinanceRecord();
		xfr.setAccountId(xf.getAccountId());//账户id
		xfr.setMethod(payType);//支付方式
		xfr.setType(4);//推荐佣金
		String xstransactionNumber=((int) (Math.random()*9000)+1000)+DateUtil.getOrdersTime()+((int)(Math.random()*9000)+10000);
		xfr.setTransactionNumber(xstransactionNumber);//交易单号
		xfr.setStatus(2);//1是待处理，2成功，3已拒绝
		xfr.setMoney(recommendCommission);
		b=financeRecordService.addFinanceRecord(xfr);
		if(!b){
			throw new PayException();
		}
		//团购人的团购信息修改，推荐的时候已经修改过了，所以不动。		
	}
	/**
	 * 拆分人
	 */
	//拆分人的财务信息
	List<Finance> sfl = financeService.browsePagingFinance(null, split.getAccountId(), 1, 1, "finance_id", "asc");
	Finance sf = sfl.get(0);
	//拆分人拆分金额
	Double splitReward = accountLevel.getSplitReward();
	sf.setMoney(sf.getMoney()+splitReward);//余额
	sf.setSplitReward(sf.getSplitReward()+splitReward);//拆分金额
	b=financeService.updateFinance(sf);
	if(!b){
		throw new PayException();
	}
	//拆分人财务记录
	FinanceRecord sfr=new FinanceRecord();
	sfr.setAccountId(sf.getAccountId());//账户id
	sfr.setMethod(payType);//支付方式
	sfr.setType(6);//拆分账单
	String sstransactionNumber=((int) (Math.random()*9000)+1000)+DateUtil.getOrdersTime()+((int)(Math.random()*9000)+10000);
	sfr.setTransactionNumber(sstransactionNumber);//交易单号
	sfr.setStatus(2);//1是待处理，2成功，3已拒绝
	sfr.setMoney(splitReward);
	b=financeRecordService.addFinanceRecord(sfr);
	if(!b){
		throw new PayException();
	}
	//拆分人的团购信息修改
	stpi.setUpdateDate(new Date());//更新时间
	stpi.setTeamPurchaseCardAllowance(stpi.getTeamPurchaseCardAllowance()-accountLevel.getNumber());//团购卡余量减少
	stpi.setAlreadySplit(stpi.getAlreadySplit()+accountLevel.getNumber());//拆分张数
	stpi.setAlreadySplitPrice(stpi.getAlreadySplitPrice()+accountLevel.getTeamPurchasePrice());//已拆分总额
	stpi.setAlreadySplitUpdateDate(new Date());//已拆分更新时间
	b=teamPurchaseInfoService.updateTeamPurchaseInfo(stpi);
	if(!b){
		throw new PayException();
	}
	/**
	 * 拆分人上级
	 */
	//拆分人账户上级表
	List<AccountParent> ssapl = accountParentService.browsePagingAccountParent(null, null, accountId, null, null, null, null, 1, 1, "account_parent_id", "asc");
	AccountParent ssap = ssapl.get(0);
	Integer ssacountId = ssap.getRealMasterId();//真实上级id	
	if(ssacountId!=null&&!ssacountId.equals("")){//
	//拆分人上级的财务信息
	List<Finance> ssfl = financeService.browsePagingFinance(null, ssacountId, 1, 1, "finance_id", "asc");
	Finance ssf = ssfl.get(0);
	//拆分人上级的拆分上级金额
	Double splitParentReward = accountLevel.getSplitParentReward();
	ssf.setMoney(ssf.getMoney()+splitParentReward);//余额
	sf.setSplitParentReward(sf.getSplitParentReward()+splitParentReward);//拆分上级金额
	b=financeService.updateFinance(sf);
	if(!b){
		throw new PayException();
	}
	//拆分人上级的财务记录
	FinanceRecord ssfr=new FinanceRecord();
	ssfr.setAccountId(ssf.getAccountId());//账户id
	ssfr.setMethod(payType);//支付方式
	ssfr.setType(7);//二级团购奖励
	String ssstransactionNumber=((int) (Math.random()*9000)+1000)+DateUtil.getOrdersTime()+((int)(Math.random()*9000)+10000);
	ssfr.setTransactionNumber(ssstransactionNumber);//交易单号
	ssfr.setStatus(2);//1是待处理，2成功，3已拒绝
	ssfr.setMoney(splitParentReward);
	b=financeRecordService.addFinanceRecord(ssfr);
	if(!b){
		throw new PayException();
	}
	}
	//拆分人上级的团购信息无需修改
	/**
	 * 拆分表单
	 */
	split.setSplitDate(new Date());//拆分时间
	split.setStatus(1);//已拆分
	b=splitDao.updateSplit(split);
	if(!b){
		throw new PayException();
	}
	/**
	 * 订单修改
	 */
	order.setStatus(2);//已完成
	order.setUpdateDate(new Date());
	b=orderService.updateOrder(order);
	if(!b){
		throw new PayException();
	}
	return b;
	}

	
}
