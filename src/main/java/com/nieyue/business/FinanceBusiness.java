package com.nieyue.business;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.nieyue.bean.Account;
import com.nieyue.bean.AccountLevel;
import com.nieyue.bean.AccountParent;
import com.nieyue.bean.Distribute;
import com.nieyue.bean.Finance;
import com.nieyue.bean.FinanceRecord;
import com.nieyue.bean.Notice;
import com.nieyue.bean.Order;
import com.nieyue.bean.OrderDetail;
import com.nieyue.bean.Payment;
import com.nieyue.bean.Split;
import com.nieyue.bean.TeamPurchaseInfo;
import com.nieyue.bean.Vip;
import com.nieyue.bean.VipGrowthRecord;
import com.nieyue.bean.VipNumber;
import com.nieyue.service.AccountLevelService;
import com.nieyue.service.AccountParentService;
import com.nieyue.service.AccountService;
import com.nieyue.service.DistributeService;
import com.nieyue.service.FinanceRecordService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.NoticeService;
import com.nieyue.service.OrderService;
import com.nieyue.service.SplitService;
import com.nieyue.service.TeamPurchaseInfoService;
import com.nieyue.service.VideoSetService;
import com.nieyue.service.VipGrowthRecordService;
import com.nieyue.service.VipNumberService;
import com.nieyue.service.VipService;
import com.nieyue.util.DateUtil;

/**
 * 财务业务
 */
@Configuration
public class FinanceBusiness {
	@Resource
	VideoSetService videoSetService;
	@Resource
	OrderService orderService;
	@Resource
	AccountService accountService;
	@Resource
	AccountLevelService accountLevelService;
	@Resource
	AccountParentService accountParentService;
	@Resource
	FinanceService financeService;
	@Resource
	FinanceRecordService financeRecordService;
	@Resource
	TeamPurchaseInfoService teamPurchaseInfoService;
	@Resource
	VipNumberService vipNumberService;
	@Resource
	VipService vipService;
	@Resource
	VipGrowthRecordService vipGrowthRecordService;
	@Resource
	DistributeService distributeService;
	@Resource
	NoticeService noticeService;
	@Resource
	NoticeBusiness noticeBusiness;
	@Resource
	AccountLevelBusiness accountLevelBusiness;
	@Resource
	SplitService splitService;
	@Resource
	PaymentBusiness paymentBusiness;
	@Resource
	OrderBusiness orderBusiness;
	/**
	 *  根据订单类型，支付方式、账户id、金额，财务业务执行
	 *  （余额支付，首先执行财务业务，再执行订单与订单详情）
	 *  （第三方支付，首先执行请求第三方接口返回给app的统一下单结果，再执行财务业务，最后订单与订单详情）
	 * @param type 类型，1VIP购买，2团购卡团购，3付费课程
	 * @param payType 支付类型，默认1支付宝支付，2微信支付，3余额支付,4ios内购支付
	 * @param accountId 账户id
	 * @param businessId 业务id
	 * @param nickname 昵称
	 * @param phone 会员账号 手机号
	 * @param contactPhone 联系手机
	 * @param money 金额
	 * @return -1不成功 ，0脱离事物，部分执行，1.成功
	 */
	public int financeExcute(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer businessId,
			String orderNumber,
			String nickname,
			String phone,
			String contactPhone
			){
		boolean b=true;//默认true
		//获取订单详情
		OrderDetail orderDetail = paymentBusiness.getPaymentType(type, payType, accountId, businessId);
		if(orderDetail==null){
			return -1;
		}
			/**
			 * vip购买
			 */
			if(type==1){
				int r = financeExcuteVIP( type,payType,accountId,orderNumber,orderDetail);
				return r;
			/**
			 * 团购卡团购
			 */
			}else if(type==2){
				int t=financeExcuteTeam(type,payType,accountId,businessId,orderNumber,nickname,phone,contactPhone,orderDetail);
				return t;
			/**
			 * 付费课程购买	
			 */
			}else if(type==3){
				Double money=orderDetail.getTotalPrice();
				//获取当前人的财务信息
				List<Finance> fl = financeService.browsePagingFinance(null, accountId, 1, 1, "finance_id", "asc");
				Finance f = fl.get(0);
				//余额支付，修改财务表
				if(payType==3){
					//如果价格小于0 或者 余额小于订单价格，返回失败
					if(money<=0 || (f.getMoney()<money)){
						return -1;
					}
					//自身财务
					f.setMoney(f.getMoney()-money);//余额=原来的-花掉的
					f.setConsume(f.getConsume()+money);//消费余额
					b=financeService.updateFinance(f);
				}
				if(b){
					//自身财务记录
					FinanceRecord fr=new FinanceRecord();
					fr.setAccountId(accountId);//账户id
					fr.setMethod(payType);//支付方式
					fr.setType(11);//付费课程购买
					String transactionNumber=orderBusiness.getOrderNumber(accountId);
					fr.setTransactionNumber(transactionNumber);//交易单号
					fr.setStatus(2);//1是待处理，2成功，3已拒绝
					fr.setMoney(money);
					b=financeRecordService.addFinanceRecord(fr);
					//创建订单
					Order order = paymentBusiness.getOrder(type, payType, accountId,orderNumber, orderDetail);
					if(b && order!=null){
						return 1;
					}
				}
				}
		return -1;
	}
	/**
	 *  vip购买财务业务执行
	 * @param type 类型，1VIP购买，2团购卡团购，3付费课程
	 * @param payType 支付类型，默认1支付宝支付，2微信支付，3余额支付,4ios内购支付
	 * @param accountId 账户id
	 * @param money 金额
	 * @return -1不成功 ，0脱离事物，部分执行，1.成功
	 */
	public int financeExcuteVIP(
			Integer type,
			Integer payType,
			Integer accountId,
			String orderNumber,
			OrderDetail orderDetail
			){
		Double money=orderDetail.getTotalPrice();
		boolean b=true;//默认true
		//获取当前人的财务信息
		List<Finance> fl = financeService.browsePagingFinance(null, accountId, 1, 1, "finance_id", "asc");
			Finance f = fl.get(0);
		//账户上级表
		List<AccountParent> apl = accountParentService.browsePagingAccountParent(null, null, accountId, null, null, null, null, 1, 1, "account_parent_id", "asc");
				AccountParent ap = apl.get(0);
				Integer aprmid = ap.getRealMasterId();//真实上级id
				List<TeamPurchaseInfo> stpil = teamPurchaseInfoService.browsePagingTeamPurchaseInfo(aprmid, null, null, 1, 1, "team_purchase_info_id", "asc");
					//上级团购信息表
					TeamPurchaseInfo stpi = stpil.get(0);
					if(stpi.getTeamPurchaseCardAllowance()<10){//小于10张报警
						b=buzuNotice(aprmid,stpi.getTeamPurchaseCardAllowance());
					}
					//没有团购卡，直接失败，记录次数
					if(stpi.getTeamPurchaseCardAllowance()<1){
						List<VipNumber> vnl = vipNumberService.browsePagingVipNumber(null, accountId, aprmid, null, null, null, 1, 1, "vip_number_id", "desc");
						if(vnl.size()<=0){
							VipNumber vipNumber=new VipNumber();
							vipNumber.setNumber(1);
							vipNumber.setAccountId(accountId);
							vipNumber.setRealMasterId(aprmid);
							vipNumber.setStatus(1);//待处理
							vipNumberService.addVipNumber(vipNumber);
						}else{
							VipNumber vn = vnl.get(0);
							//相同一天，不能执行
							if(DateUtil.isSameDate(vn.getUpdateDate(), new Date())){
								return -1;
							}
							vn.setNumber(vn.getNumber()+1);
							if(vn.getNumber()>=3){
								vn.setStatus(3);//已超次
							}
							vipNumberService.updateVipNumber(vn);
						}
						return 0;//让部分执行
					}
				//升级人的等级	
				AccountLevel al = accountLevelService.loadAccountLevel(ap.getAccountLevelId());
				//升级成功后的等级
				AccountLevel nal = accountLevelService.loadAccountLevel(ap.getAccountLevelId()+1);
				//成功后的accountParent
				ap.setAccountLevelId(nal.getAccountLevelId());
				ap.setRealname(nal.getName());
				b=accountParentService.updateAccountParent(ap);
				if(!b){
					return -1;
				}
				//只有是0级别才能购买
				if(al.getLevel()==0){
					//余额支付，修改财务表
					if(payType==3){
						//如果价格小于0 或者 余额小于订单价格，返回失败
						if(money<=0 || (f.getMoney()<money)){
							return -1;
						}
						//自身财务
						f.setMoney(f.getMoney()-money);//余额=原来的-花掉的
						f.setConsume(f.getConsume()+money);//消费余额
						b=financeService.updateFinance(f);
					}
					if(b){
						//自身财务记录
						FinanceRecord fr=new FinanceRecord();
						fr.setAccountId(accountId);//账户id
						fr.setMethod(payType);//支付方式
						fr.setType(8);//vip购买
						String transactionNumber=orderBusiness.getOrderNumber(accountId);
						fr.setTransactionNumber(transactionNumber);//交易单号
						fr.setStatus(2);//1是待处理，2成功，3已拒绝
						fr.setMoney(money);
						b=financeRecordService.addFinanceRecord(fr);
					}
					if(!b){
						return -1;
					}
					//拆分奖励
					Double splitReward = nal.getSplitReward();
					//上级财务
					List<Finance> sfinancel= financeService.browsePagingFinance(null, aprmid, 1, 1, "finance_id", "asc");
					Finance sfinance = sfinancel.get(0);
					sfinance.setMoney(sfinance.getMoney()+splitReward);//余额
					sfinance.setSplitReward(sfinance.getSplitReward()+splitReward);//拆分奖励
					b=financeService.updateFinance(sfinance);
					if(!b){
						return -1;
					}
					//上级财务记录
					FinanceRecord sfr=new FinanceRecord();
					sfr.setAccountId(sfinance.getAccountId());//上级账户id
					sfr.setMethod(payType);//支付方式
					sfr.setType(9);//分发奖励
					String stransactionNumber=orderBusiness.getOrderNumber(sfinance.getAccountId());
					sfr.setTransactionNumber(stransactionNumber);//交易单号
					sfr.setStatus(2);//1是待处理，2成功，3已拒绝
					sfr.setMoney(splitReward);
					b=financeRecordService.addFinanceRecord(sfr);
					if(!b){
						return -1;
					}
					//拆分分上级奖励
					Double splitParentReward = nal.getSplitParentReward();
					//获取上上级别
					List<AccountParent> sapl = accountParentService.browsePagingAccountParent(null, null, aprmid, null, null, null, null, 1, 1, "account_parent_id", "asc");
					if(sapl.size()==1 &&sapl.get(0)!=null){
					AccountParent sap = sapl.get(0);
					Integer saprmid = sap.getRealMasterId();//上级的真实上级id
					if(saprmid!=null){
					//上上级财务
					List<Finance> ssfinancel= financeService.browsePagingFinance(null, saprmid, 1, 1, "finance_id", "asc");
					Finance ssfinance = ssfinancel.get(0);
					ssfinance.setMoney(ssfinance.getMoney()+splitParentReward);//余额
					ssfinance.setSplitParentReward(ssfinance.getSplitParentReward()+splitParentReward);//拆分上级奖励
					b=financeService.updateFinance(ssfinance);
					if(!b){
						return -1;
					}
					//上上级财务记录
					FinanceRecord ssfr=new FinanceRecord();
					ssfr.setAccountId(ssfinance.getAccountId());//上上级账户id
					ssfr.setMethod(payType);//支付方式
					ssfr.setType(10);//二级购买vip奖励
					String sstransactionNumber=orderBusiness.getOrderNumber(ssfinance.getAccountId());
					ssfr.setTransactionNumber(sstransactionNumber);//交易单号
					ssfr.setStatus(2);//1是待处理，2成功，3已拒绝
					ssfr.setMoney(splitParentReward);
					b=financeRecordService.addFinanceRecord(ssfr);
					if(!b){
						return -1;
					}
					}
					}
					//更新vip表
					List<Vip> vipl = vipService.browsePagingVip(accountId, 0, null, 1, 1, "vip_id", "asc");
					Vip vip = vipl.get(0);
					vip.setCreateDate(new Date());
					vip.setExpireDate(DateUtil.nextYear(new Date(), 1));//一年
					vip.setLevel(nal.getLevel());
					vip.setName(nal.getName());
					vip.setStatus(1);//没到期
					b=vipService.updateVip(vip);
					if(!b){
						return -1;
					}
					//创建vip成长表
					VipGrowthRecord vipGrowthRecord=new VipGrowthRecord();
					vipGrowthRecord.setName(nal.getName());
					vipGrowthRecord.setLevel(nal.getLevel());
					vipGrowthRecord.setCreateDate(new Date());
					vipGrowthRecord.setAccountId(accountId);
					vipGrowthRecord.setPrice(money);
					b=vipGrowthRecordService.addVipGrowthRecord(vipGrowthRecord);
					if(!b){
						return -1;
					}
					//分发表创建
					Distribute distribute=new Distribute();
					Account account = accountService.loadAccount(accountId);
					distribute.setRealname(account.getRealname());
					distribute.setNumber(1);
					distribute.setPrice(money);
					distribute.setDistributeDate(new Date());
					distribute.setCreateDate(new Date());
					distribute.setUpdateDate(new Date());
					distribute.setStatus(1);//默认已分发
					distribute.setAccountId(aprmid);//真实上级id
					distribute.setBuyAccountId(accountId);//购买者id
					b=distributeService.addDistribute(distribute);
					if(!b){
						return -1;
					}
					//自身团购信息表建立
					TeamPurchaseInfo teamPurchaseInfo =new TeamPurchaseInfo();
					teamPurchaseInfo.setAccountId(accountId);//账户id
					teamPurchaseInfo.setAlreadyRecommend(0);//已推荐给上级（张）
					teamPurchaseInfo.setAlreadyRecommendPrice(0.0);//已推荐给上级总额
					teamPurchaseInfo.setAlreadyRecommendUpdateDate(new Date());//已推荐给上级更新时间
					teamPurchaseInfo.setAlreadySplit(0);//已拆分（张）
					teamPurchaseInfo.setAlreadySplitPrice(0.0);//已拆分总额
					teamPurchaseInfo.setAlreadySplitUpdateDate(new Date());//已拆分更新时间
					teamPurchaseInfo.setAlreadyTeamPurchase(0);//已团购（张）
					teamPurchaseInfo.setCreateDate(new Date());//创建时间）
					teamPurchaseInfo.setTeamPurchaseCardAllowance(0);//团购卡余量
					teamPurchaseInfo.setTeamPurchaseSuccess(0);//团购成功（张）
					teamPurchaseInfo.setTeamPurchaseSuccessPrice(0.0);//团购成功总额
					teamPurchaseInfo.setTeamPurchaseSuccessUpdateDate(new Date());//团购成功更新时间
					teamPurchaseInfo.setUpdateDate(new Date());//更新时间
					teamPurchaseInfo.setWaitDispose(0);//待处理（张）
					teamPurchaseInfo.setWaitDisposePrice(0.0);//待处理总额
					teamPurchaseInfo.setWaitDisposeUpdateDate(new Date());//待处理更新时间
					b=teamPurchaseInfoService.addTeamPurchaseInfo(teamPurchaseInfo);
					if(!b){
						return -1;
					}
					//上级团购信息表更新
					stpi.setTeamPurchaseCardAllowance(stpi.getTeamPurchaseCardAllowance()-1);//升级vip就减团购卡
					b=teamPurchaseInfoService.updateTeamPurchaseInfo(stpi);
					//创建订单
					Order order = paymentBusiness.getOrder(type, payType, accountId,orderNumber, orderDetail);
					if(order.getAccountId()==null){
						return -1;
					}
					
					if(b){
						return 1;
					}
				}
				return -1;
		}
	/**
	 *  团购财务业务执行
	 * @param type 类型，1VIP购买，2团购卡团购，3付费课程
	 * @param payType 支付类型，默认1支付宝支付，2微信支付，3余额支付,4ios内购支付
	 * @param accountId 账户id
	 * @param businessId 业务id
	 * @param nickname 昵称
	 * @param phone 会员账号 手机号
	 * @param contactPhone 联系手机
	 * @param OrderDetail 订单详情
	 * @return -1不成功 ，0脱离事物，部分执行，1.成功
	 */
	public int financeExcuteTeam(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer businessId,
			String orderNumber,
			String nickname,
			String phone,
			String contactPhone,
			OrderDetail orderDetail
			){
		if(nickname==null||phone==null||contactPhone==null){
			return -1;//必传参数
		}
		//如果有升级订单还没完成
		List<Order> oool = orderService.browsePagingOrder(type, payType, accountId, 1, null, null, 1, 1, "order_id", "asc");
		if(oool.size()>0){
			return -1;
		}
		Double money=orderDetail.getTotalPrice();
		boolean b=true;//默认true
		//该业务id是否包含在可选中
		b = accountLevelBusiness.isContain(accountId, businessId);
		if(!b){
			return -1;
		}
		//获取当前业务账户等级
		AccountLevel currentAccountLevel = accountLevelBusiness.getAccountLevel(businessId);
		//获取当前人的财务信息
		List<Finance> fl = financeService.browsePagingFinance(null, accountId, 1, 1, "finance_id", "asc");
			Finance f = fl.get(0);
		//账户上级表
		List<AccountParent> apl = accountParentService.browsePagingAccountParent(null, null, accountId, null, null, null, null, 1, 1, "account_parent_id", "asc");
				AccountParent ap = apl.get(0);
				Integer aprmid = ap.getRealMasterId();//真实上级id
				List<TeamPurchaseInfo> stpil = teamPurchaseInfoService.browsePagingTeamPurchaseInfo(aprmid, null, null, 1, 1, "team_purchase_info_id", "asc");
					//上级团购信息表
					TeamPurchaseInfo stpi = stpil.get(0);
					if(stpi.getTeamPurchaseCardAllowance()<10){//小于10张报警
						b=buzuNotice(aprmid,stpi.getTeamPurchaseCardAllowance());
					}
					
					//余额支付，修改财务表
					if(payType==3){
						//如果价格小于0 或者 余额小于订单价格，返回失败
						if(money<=0 || (f.getMoney()<money)){
							return -1;
						}
						//自身财务
						f.setMoney(f.getMoney()-money);//余额=原来的-花掉的
						f.setConsume(f.getConsume()+money);//消费余额
						f.setTeamPurchasePrice(f.getTeamPurchasePrice()+money);//团购账单
						b=financeService.updateFinance(f);
					}
					if(b){
						//自身财务记录
						FinanceRecord fr=new FinanceRecord();
						fr.setAccountId(accountId);//账户id
						fr.setMethod(payType);//支付方式
						fr.setType(5);//团购账单
						String transactionNumber=orderBusiness.getOrderNumber(accountId);
						fr.setTransactionNumber(transactionNumber);//交易单号
						fr.setStatus(2);//1是待处理，2成功，3已拒绝
						fr.setMoney(money);
						b=financeRecordService.addFinanceRecord(fr);
					}
					if(!b){
						return -1;
					}

					//创建订单
					Order order = paymentBusiness.getOrder(type, payType, accountId,orderNumber, orderDetail);
					//拆分表创建
					Split split=new Split();
					split.setOrderId(order.getOrderId());
					split.setRecommendAccountId(null);//创建团购没有推荐人
					split.setAccountId(aprmid);
					split.setApplyDate(new Date());
					split.setBuyAccountId(accountId);
					split.setCreateDate(new Date());
					split.setNumber(currentAccountLevel.getNumber());
					split.setPrice(money);
					split.setNickname(nickname);
					split.setPhone(phone);
					split.setContactPhone(contactPhone);
					split.setSplitDate(null);
					split.setStatus(0);//0是已申请
					split.setUpdateDate(new Date());
					String remark="无";
					AccountLevel sal = accountLevelBusiness.getAccountLevelByAccountId(aprmid);
					if(sal!=null&& currentAccountLevel.equals(sal.getLevel())){
						//平级团购
						remark="平级团购";
					}
					split.setRemark(remark);
					b=splitService.addSplit(split);
					if(!b){
						return -1;
					}
					//自身团购信息表
					 List<TeamPurchaseInfo> tpil = teamPurchaseInfoService.browsePagingTeamPurchaseInfo(accountId, null, null, 1, 1, "team_purchase_info_id", "asc");
					if(tpil.size()!=1){
						return -1;
					}
					TeamPurchaseInfo tpi = tpil.get(0);
					tpi.setAlreadyTeamPurchase(tpi.getAlreadyTeamPurchase()+currentAccountLevel.getNumber());//已团购（张）
					tpi.setUpdateDate(new Date());//更新时间
					tpi.setWaitDispose(tpi.getWaitDispose()+currentAccountLevel.getNumber());//待处理（张）
					tpi.setWaitDisposePrice(tpi.getWaitDisposePrice()+currentAccountLevel.getTeamPurchasePrice());//待处理总额
					tpi.setWaitDisposeUpdateDate(new Date());//待处理更新时间
					b=teamPurchaseInfoService.updateTeamPurchaseInfo(tpi);
					//团购通知  （当前申请人收到）
					b=tuangouNotice(accountId,money);
					//团购申请 （真实上级收到）
					b=shenqingNotice(aprmid, ap.getRealname(), money);
					if(b){
						return 1;
					}
				return -1;
		}
	/**
	 * 验证第三方支付条件是否满足
	 * @param type 类型，1VIP购买，2团购卡团购，3付费课程
	 * @param payType 支付类型，默认1支付宝支付，2微信支付，3余额支付,4ios内购支付
	 * @param accountId 账户id
	 * @param businessId 业务id
	 * @param nickname 昵称
	 * @param phone 会员账号 手机号
	 * @param contactPhone 联系手机
	 * return false 不满足， true满足
	 */
	public boolean canThirdPay(
			Integer type,
			Integer payType,
			Integer accountId,
			Integer businessId,
			String nickname,
			String phone,
			String contactPhone
			){
		boolean b=true;
		//获取订单详情
		OrderDetail orderDetail = paymentBusiness.getPaymentType(type, payType, accountId, businessId);
		if(orderDetail==null){
			b=false;
			return b;
		}
		if(type==1){
		//账户上级表
		List<AccountParent> apl = accountParentService.browsePagingAccountParent(null, null, accountId, null, null, null, null, 1, 1, "account_parent_id", "asc");
				AccountParent ap = apl.get(0);
				Integer aprmid = ap.getRealMasterId();//真实上级id
				List<TeamPurchaseInfo> stpil = teamPurchaseInfoService.browsePagingTeamPurchaseInfo(aprmid, null, null, 1, 1, "team_purchase_info_id", "asc");
					//上级团购信息表
					TeamPurchaseInfo stpi = stpil.get(0);
					if(stpi.getTeamPurchaseCardAllowance()<10){//小于10张报警
						b=buzuNotice(aprmid,stpi.getTeamPurchaseCardAllowance());
					}
					//没有团购卡，直接失败，记录次数
					if(stpi.getTeamPurchaseCardAllowance()<1){
						List<VipNumber> vnl = vipNumberService.browsePagingVipNumber(null, accountId, aprmid, null, null, null, 1, 1, "vip_number_id", "desc");
						if(vnl.size()<=0){
							VipNumber vipNumber=new VipNumber();
							vipNumber.setNumber(1);
							vipNumber.setAccountId(accountId);
							vipNumber.setRealMasterId(aprmid);
							vipNumber.setStatus(1);//待处理
							vipNumberService.addVipNumber(vipNumber);
						}else{
							VipNumber vn = vnl.get(0);
							//相同一天，不能执行
							if(DateUtil.isSameDate(vn.getUpdateDate(), new Date())){
								b=false;
								return b;
							}
							vn.setNumber(vn.getNumber()+1);
							if(vn.getNumber()>=3){
								vn.setStatus(3);//已超次
							}
							vipNumberService.updateVipNumber(vn);
						}
						return false;//让部分执行
					}	
		}else if(type==2){
			if(nickname==null||phone==null||contactPhone==null){
				b=false;
				return b;//必传参数
			}
			//如果有升级订单还没完成
			List<Order> oool = orderService.browsePagingOrder(type, payType, accountId, 1, null, null, 1, 1, "order_id", "asc");
			if(oool.size()>0){
				b=false;
				return b;
			}
			//该业务id是否包含在可选中
			b = accountLevelBusiness.isContain(accountId, businessId);
				return b;
		}
		return b;
	}
	/**
	 * 充值回调财务变更
	 */
	public boolean rechargeFinance(Payment payment){
		boolean b=false;
		FinanceRecord fr=new FinanceRecord();
		fr.setAccountId(payment.getAccountId());
		fr.setMethod(payment.getType());//支付类型
		fr.setMoney(payment.getMoney());//金额
		fr.setTransactionNumber(payment.getOrderNumber());//订单号
		fr.setType(1);//1是账户充值
		fr.setStatus(2);//充值直接成功
		b = financeRecordService.addFinanceRecord(fr);
		if(b){
			List<Finance> fl = financeService.browsePagingFinance(null, payment.getAccountId(), 1, 1, "finance_id", "asc");
			if(fl.size()==1){
				Finance f = fl.get(0);
				f.setMoney(f.getMoney()+payment.getMoney());
				f.setRecharge(f.getRecharge()+payment.getMoney());
				b= financeService.updateFinance(f);
				return b;
			}
		}
		return b;
	}
	/**
	 * 团购卡余额不足通知
	 */
	public boolean buzuNotice(Integer accountId,Integer number){
		boolean b=true;
		Notice notice=noticeBusiness.getNoticeByBuzu(accountId, number);
		b=noticeService.addNotice(notice);
		return b;
	}
	/**
	 * 团购通知  （当前申请人收到）
	 * accountId 申请人的账户id
	 */
	public boolean tuangouNotice(Integer accountId,Double money){
		boolean b=true;
		Notice notice=noticeBusiness.getNoticeByTuangou(accountId, money);
		b=noticeService.addNotice(notice);
		return b;
	}
	/**
	 * 团购申请 （真实上级收到）
	 * accountId 接收人的账户id
	 * realname 申请人的
	 */
	public boolean shenqingNotice(Integer accountId,String realname,Double money){
		boolean b=true;
		Notice notice=noticeBusiness.getNoticeByShenqing(accountId, realname, money);
		b=noticeService.addNotice(notice);
		return b;
	}
}
