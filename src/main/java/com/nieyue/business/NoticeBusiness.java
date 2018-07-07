package com.nieyue.business;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import com.nieyue.bean.Account;
import com.nieyue.bean.Mer;
import com.nieyue.bean.MerCate;
import com.nieyue.bean.Notice;
import com.nieyue.bean.Order;
import com.nieyue.bean.OrderProblem;
import com.nieyue.bean.OrderProblemAnswer;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.exception.NoticeException;
import com.nieyue.service.AccountService;
import com.nieyue.service.MerCateService;
import com.nieyue.service.MerService;
import com.nieyue.service.NoticeService;
import com.nieyue.service.OrderProblemAnswerService;
import com.nieyue.service.OrderProblemService;
import com.nieyue.service.OrderService;

import net.sf.json.JSONObject;
/**
 * 通知业务
 * @author 聂跃
 * @date 2017年8月19日
 * 
 */
@Configuration
public class NoticeBusiness {
	@Resource
	MerService merService;
	@Resource
	MerCateService merCateService;
	@Resource
	AccountService accountService;
	@Resource
	OrderService orderService;
	@Resource
	NoticeService noticeService;
	@Resource
	OrderProblemService orderProblemService;
	@Resource
	OrderProblemAnswerService orderProblemAnswerService;
	
	/**
	 *  获取是否商品动态
	 *  @param type 类型，1系统消息，2申请新产品销售，3新增商品类型，4商品申请自营，5提现申请，6问题单反馈,7订单商品动态
	 *  @return isMerDynamic 是否商品动态，默认0不是，1是
	 */
	public Integer getIsMerDynamicByType(Integer type){
		Integer isMerDynamic=0;//默认为0
		if(type==2||type==3||type==4){
			isMerDynamic=1;//是商品动态
		}
		return isMerDynamic;
	}
	/**
	 *  获取状态
	 *  @param type 类型，1系统消息，2申请新产品销售，3新增商品类型，4商品申请自营，5提现申请，6问题单反馈,7订单商品动态
	 *  @return status 1审核中，2申请成功，3申请失败,个人为0，代表正常
	 */
	public Integer getStatusByType(Integer type){
		Integer status=0;//默认为0
		if(type==2||type==3||type==4||type==5){
			status=1;//是审核中
		}
		return status;
	}
	/**
	 *  获取标题
	 *  @param type 类型，1系统消息，2申请新产品销售，3新增商品类型，4商品申请自营，5提现申请，6问题单反馈,7订单商品动态
	 *  @return title 系统消息，申请新产品销售，新增商品类型，商品申请自营，提现申请，问题单反馈,订单商品动态
	 */
	public String getTitleByType(Integer type){
		String title="";//默认为空
		if(type==1){
			title="系统消息";
		}else if(type==2){
			title="申请新产品销售";
		}else if(type==3){
			title="新增商品类型";
		}else if(type==4){
			title="商品申请自营";
		}else if(type==5){
			title="提现申请";
		}else if(type==6){
			title="问题单反馈";
		}else if(type==7){
			title="订单商品动态";
		}
		return title;
	}
	/**
	 *  通知
	 *  content 内容
	 *  类型，1系统消息，2申请新产品销售，3新增商品类型，4商品申请自营，5提现申请，6问题单反馈，7订单商品动态
	 *  @return content
	 */
	public String getContentByType(
			Notice notice ){
		StringBuffer content = new StringBuffer();
			switch (notice.getType()) {
			//系统消息
			case 1:
				content.append(notice.getContent());
				break;
			//申请新产品销售
			case 2:
				if(ObjectUtils.isEmpty(notice.getBusinessId())
						){
					throw new NoticeException("申请新产品销售异常");
				}
				Mer mer2=merService.loadMer(notice.getBusinessId());
				if(ObjectUtils.isEmpty(mer2)){
					throw new NoticeException("申请新产品销售异常");
				}
				JSONObject json2=new JSONObject();
				json2.put("merName", mer2.getName());//商品名称
				json2.put("merCateName", mer2.getMerCate().getName());//商品种类
				json2.put("merPrice", mer2.getUnitPrice());//商品价格
				json2.put("merDiscount", mer2.getDiscount());//商品折扣
				content.append(json2.toString());
				break;
			//新增商品类型
			case 3:
				List<MerCate> merCate3List=merCateService.browsePagingMerCate(1, Integer.MAX_VALUE, "mer_cate_id", "asc");
				JSONObject json = JSONObject.fromObject(notice.getContent());
				merCate3List.forEach(e->{
					if(e.getName().equals(json.get("merCateName"))){
						//已经存在的商品类型名称
						throw new CommonRollbackException("商品类型名称已经存在");
					}
				});
				content.append(notice.getContent());
				/*if(ObjectUtils.isEmpty(notice.getBusinessId())
						){
					throw new NoticeException("新增商品类型异常");
				}
				MerCate merCate3=merCateService.loadMerCate(notice.getBusinessId());
				if(ObjectUtils.isEmpty(merCate3)){
					throw new NoticeException("新增商品类型异常");
				}
				JSONObject json3=new JSONObject();
				json3.put("merCateName", merCate3.getName());//商品类型名称
				json3.put("merCateSummary", merCate3.getSummary());//商品类型介绍
				content.append(json3.toString());*/
				break;
			//商品申请自营
			case 4:
				if(ObjectUtils.isEmpty(notice.getBusinessId())
						){
					throw new NoticeException("商品申请自营异常");
				}
				List<Notice> nl = noticeService.browsePagingNotice(2, 4, null, 1, notice.getAccountId(), notice.getBusinessId(), 1, 1, "notice_id", "asc");
				if(nl.size()>0){
					throw new NoticeException("商品申请自营已经存在，待审核后才能再次申请");
				}
				Mer mer4=merService.loadMer(notice.getBusinessId());
				if(ObjectUtils.isEmpty(mer4)){
					throw new NoticeException("商品申请自营异常");
				}
				/*JSONObject json4=new JSONObject();
				json4.put("merName", mer4.getName());//商品名称
				json4.put("merCateName", mer4.getMerCate().getName());//商品种类
				json4.put("merPrice", mer4.getUnitPrice());//商品价格
				json4.put("merPlatformProportion", mer4.getPlatformProportion());//平台技术服务费
				content.append(json4.toString());*/
				content.append(notice.getContent());
				break;
			//提现申请
			case 5:
				if(ObjectUtils.isEmpty(notice.getBusinessId())
						){
					throw new NoticeException("提现申请异常");
				}
				Order order5=orderService.loadOrder(notice.getBusinessId());
				Account account5=accountService.loadAccount(order5.getAccountId());
				if(ObjectUtils.isEmpty(order5)||ObjectUtils.isEmpty(account5)){
					throw new NoticeException("提现申请异常");
				}
				JSONObject json5=new JSONObject();
				json5.put("realname", account5.getRealname());//真实姓名
				json5.put("phone", account5.getPhone());//手机号
				json5.put("money", order5.getOrderDetailList().get(0).getTotalPrice());//提现金额
				content.append(json5.toString());
				break;
			//问题单反馈
			case 6:
				if(ObjectUtils.isEmpty(notice.getBusinessId())
						){
					throw new NoticeException("问题单反馈异常");
				}
				Order order6=orderService.loadOrder(notice.getBusinessId());
				if(ObjectUtils.isEmpty(order6)){
					throw new NoticeException("问题单反馈异常");
				}
				JSONObject json6=new JSONObject();
				json6.put("orderId", order6.getOrderId());//订单Id
				json6.put("orderNumber", order6.getOrderNumber());//订单编号
				json6.put("merName", order6.getOrderDetailList().get(0).getName());//商品名称
				String c="";
				//提问
				List<OrderProblem> opl = orderProblemService.browsePagingOrderProblem(null, null, order6.getOrderId(), order6.getAccountId(), 1, 1, "number", "desc");
				if(opl.size()==1){
					OrderProblem op = opl.get(0);
					//回复内容
					List<OrderProblemAnswer> opal = orderProblemAnswerService.browsePagingOrderProblemAnswer(op.getOrderProblemId(), null, 1, 1, "create_date", "desc");
					if(opal.size()==1){
						c=opal.get(0).getContent();
					}
				}
				json6.put("content", c);//问题详情/商家回复
				content.append(json6.toString());
				break;
			//订单商品动态
			case 7:
				if(ObjectUtils.isEmpty(notice.getBusinessId())
						){
					throw new NoticeException("订单商品动态异常");
				}
				Order order7=orderService.loadOrder(notice.getBusinessId());
				if(ObjectUtils.isEmpty(order7)){
					throw new NoticeException("订单商品动态异常");
				}
				JSONObject json7=new JSONObject();
				json7.put("orderId", order7.getOrderId());//订单Id
				json7.put("orderNumber", order7.getOrderNumber());//订单编号
				json7.put("merName", order7.getOrderDetailList().get(0).getName());//商品名称
				json7.put("content", "卡号及卡密已经发送给您，请查收！");//消息内容（商品状态进度）
				content.append(json7.toString());
				break;

			default:
				break;
			}
			
			
			return content.toString();
	}
public static void main(String[] args) {
	StringBuffer content=new StringBuffer();
	switch (2) {
	//系统消息
	case 1:
		content.append("dfs");
		break;
	case 2:
		content.append("22222");
		break;
	case 3:
		content.append("3333");
		break;
	default:
		content.append("5555");
		break;
	}
	System.out.println(content.toString());
	throw new NoticeException("sdfd");
}
}
