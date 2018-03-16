package com.nieyue.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nieyue.bean.Account;
import com.nieyue.bean.Finance;
import com.nieyue.bean.Role;
import com.nieyue.exception.AccountIsLoginException;
import com.nieyue.exception.MySessionException;
import com.nieyue.util.SingletonHashMap;

/**
 * 用户session控制拦截器
 * @author yy
 *
 */
@Configuration
public class SessionControllerInterceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
      // System.out.println(method.getDefaultValue());
        
       
        Account sessionAccount = null;
        Role sessionRole=null;
        Finance sessionFinance=null;
        if(request.getSession()!=null
        		&&request.getSession().getAttribute("account")!=null
        		&&request.getSession().getAttribute("role")!=null
        		&&request.getSession().getAttribute("finance")!=null
        		){
        sessionAccount = (Account) request.getSession().getAttribute("account");
        sessionRole = (Role) request.getSession().getAttribute("role");
        sessionFinance = (Finance) request.getSession().getAttribute("finance");
   
        }
//        Integer i=1;
//        Integer j=1;
//        if(i.equals(j)){
//        	return true;
//        }
        if(
        		request.getServletPath().equals("/")
        		||request.getRequestURI().indexOf("swagger")>-1
        		||request.getRequestURI().indexOf("api-docs")>-1
        		||request.getRequestURI().indexOf("getAPI")>-1
        		||request.getRequestURI().indexOf("tool")>-1
        		||request.getRequestURI().indexOf("validCode")>-1
        		||request.getRequestURI().indexOf("getVerificationCode")>-1
        		//role
        		||request.getRequestURI().indexOf("role/count")>-1
        		//等级
        		||request.getRequestURI().indexOf("accountLevel/count")>-1
        		//平台日
        		||request.getRequestURI().indexOf("platformDay/count")>-1
        		//vip
        		||request.getRequestURI().indexOf("vip/count")>-1
        		//vip成长记录
        		||request.getRequestURI().indexOf("vipGrowthRecord/count")>-1
        		//账户上级
        		||request.getRequestURI().indexOf("accountParent/count")>-1
        		//登陆、登出、增加师傅徒弟关系、账户
        		||request.getRequestURI().indexOf("account/count")>-1
        		||request.getRequestURI().indexOf("account/phoneIsExist")>-1
        		||request.getRequestURI().indexOf("register")>-1
        		||request.getRequestURI().indexOf("login")>-1
        		||request.getRequestURI().indexOf("account/updatePassword")>-1
        		//财务
        		||request.getRequestURI().indexOf("finance/count")>-1
        		||request.getRequestURI().indexOf("finance/alipayRechargeNotifyUrl")>-1
        		//账户记录
        		||request.getRequestURI().indexOf("financeRecord/count")>-1
        		//积分
        		||request.getRequestURI().indexOf("integral/count")>-1
        		//积分详情
        		||request.getRequestURI().indexOf("integralDetail/count")>-1
        		//积分榜
        		||request.getRequestURI().indexOf("integralBoard/count")>-1
        		||request.getRequestURI().indexOf("integralBoard/ranking")>-1
        		//视频集类型
        		||request.getRequestURI().indexOf("videoSetCate/count")>-1
        		||request.getRequestURI().indexOf("videoSetCate/list")>-1
        		||method.getName().equals("loadVideoSetCate")
        		//视频集标签
        		||request.getRequestURI().indexOf("videoSetTag/count")>-1
        		||request.getRequestURI().indexOf("videoSetTag/list")>-1
        		||method.getName().equals("loadVideoSetTag")
        		//视频集搜索
        		||request.getRequestURI().indexOf("videoSetSearch/search")>-1
        		||request.getRequestURI().indexOf("videoSetSearch/count")>-1
        		||request.getRequestURI().indexOf("videoSetSearch/list")>-1
        		||method.getName().equals("loadVideoSetSearch")
        		//视频集
        		||request.getRequestURI().indexOf("videoSet/count")>-1
        		||request.getRequestURI().indexOf("videoSet/list")>-1
        		||method.getName().equals("loadVideoSet")
        		//视频
        		||request.getRequestURI().indexOf("video/count")>-1
        		//视频集收藏
        		||request.getRequestURI().indexOf("videoSetCollect/count")>-1
        		//视频播放记录
        		||request.getRequestURI().indexOf("videoPlayRecord/count")>-1
        		||request.getRequestURI().indexOf("videoPlayRecord/list")>-1
        		||method.getName().equals("loadVideoPlayRecord")
        		//视频缓存
        		||request.getRequestURI().indexOf("videoCache/count")>-1
        		||request.getRequestURI().indexOf("videoCache/list")>-1
        		||method.getName().equals("loadVideoCache")
        		//vip购买次数
        		||request.getRequestURI().indexOf("vipNumber/count")>-1
        		||request.getRequestURI().indexOf("vipNumber/list")>-1
        		||method.getName().equals("loadVipNumber")
        		//订单
        		||request.getRequestURI().indexOf("order/count")>-1
        		//订单详情
        		||request.getRequestURI().indexOf("orderDetail/count")>-1
        		//文章类型
        		||request.getRequestURI().indexOf("articleCate/count")>-1
        		||request.getRequestURI().indexOf("articleCate/list")>-1
        		||method.getName().equals("loadArticleCate")
        		//文章
        		||request.getRequestURI().indexOf("article/count")>-1
        		||request.getRequestURI().indexOf("article/list")>-1
        		||method.getName().equals("loadArticle")
        		//文章评论
        		||request.getRequestURI().indexOf("articleComment/count")>-1
        		||request.getRequestURI().indexOf("articleComment/list")>-1
        		||method.getName().equals("loadArticleComment")
        		//通知
        		||request.getRequestURI().indexOf("notice/count")>-1
        		//收货信息
        		||request.getRequestURI().indexOf("receiptInfo/count")>-1
        		//团购信息
        		||request.getRequestURI().indexOf("teamPurchaseInfo/count")>-1
        		//分发
        		||request.getRequestURI().indexOf("distribute/count")>-1
        		//拆分
        		||request.getRequestURI().indexOf("split/count")>-1
        		//app版本
        		||request.getRequestURI().indexOf("appVersion/count")>-1
        		||request.getRequestURI().indexOf("appVersion/list")>-1
        		||method.getName().equals("loadAppVersion")
        		//勋章项
        		||request.getRequestURI().indexOf("medalTerm/count")>-1
        		||request.getRequestURI().indexOf("medalTerm/list")>-1
        		||method.getName().equals("loadMedalTerm")
        		//勋章
        		||request.getRequestURI().indexOf("medal/count")>-1
        		||request.getRequestURI().indexOf("medal/list")>-1
        		||method.getName().equals("loadMedal")
        		//意见反馈
        		||request.getRequestURI().indexOf("feedback/count")>-1
        		||request.getRequestURI().indexOf("feedback/list")>-1
        		||method.getName().equals("loadFeedback")
        		//配置
        		||request.getRequestURI().indexOf("config/count")>-1
        		//第三方支付
        		||request.getRequestURI().indexOf("payment/count")>-1
        		||request.getRequestURI().indexOf("payment/alipayTradeQuery")>-1
        		||request.getRequestURI().indexOf("payment/alipayNotifyUrl")>-1
       
        		){
        	return true;
        }else if (sessionAccount!=null) {
        	//确定角色存在
        	if(sessionRole!=null ){
        	//超级管理员
        	if(sessionRole.getName().equals("超级管理员")
        			){
        		return true;
        	}
        	//admin中只许修改自己的值
        	if(sessionRole.getName().equals("用户")){
        		   //当前sessionId放入单例map
        			HashMap<String,Object> smap=  SingletonHashMap.getInstance(); 
        			if(smap.get("accountId"+sessionAccount.getAccountId())==null
        					||smap.get("accountId"+sessionAccount.getAccountId()).equals("")
        					//非最后一个登陆的用户不能调用
        					||!smap.get("accountId"+sessionAccount.getAccountId()).equals(request.getSession().getId())){
        				//账户已经登陆
        				throw new AccountIsLoginException();
        			}
        			
        		//角色全不许
        		if( request.getRequestURI().indexOf("/role")>-1 ){
        			throw new MySessionException();
        		}
        		//等级不许删除/修改/增加
        		if( request.getRequestURI().indexOf("/accountLevel/delete")>-1 
        				|| request.getRequestURI().indexOf("/accountLevel/add")>-1
        				|| request.getRequestURI().equals("/accountLevel/list")
        				|| request.getRequestURI().equals("/accountLevel/teamAccountLevelList")
        				|| request.getRequestURI().indexOf("/accountLevel/update")>-1
        				||method.getName().equals("loadAccountLevel")
        				){
        			//自身信息
        			if((
        					request.getRequestURI().indexOf("/accountLevel/list")>-1
        					||request.getRequestURI().indexOf("/accountLevel/teamAccountLevelList")>-1
        					||method.getName().equals("loadAccountLevel")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//平台日
        		if( request.getRequestURI().indexOf("/platformDay/delete")>-1 
        				|| request.getRequestURI().indexOf("/platformDay/add")>-1
        				|| request.getRequestURI().equals("/platformDay/list")
        				|| request.getRequestURI().indexOf("/platformDay/update")>-1
        				||method.getName().equals("loadPlatformDay")
        				){
        			throw new MySessionException();
        		}
        		//vip
        		if( request.getRequestURI().indexOf("/vip/delete")>-1 
        				|| request.getRequestURI().indexOf("/vip/add")>-1
        				|| request.getRequestURI().equals("/vip/list")
        				|| request.getRequestURI().indexOf("/vip/update")>-1
        				||method.getName().equals("loadVip")
        				){
        			//自身信息
        			if((request.getRequestURI().indexOf("/vip/list")>-1
        					||method.getName().equals("loadVip")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//vip成长记录
        		if( request.getRequestURI().indexOf("/vipGrowthRecord/delete")>-1 
        				|| request.getRequestURI().indexOf("/vipGrowthRecord/add")>-1
        				|| request.getRequestURI().equals("/vipGrowthRecord/list")
        				|| request.getRequestURI().indexOf("/vipGrowthRecord/update")>-1
        				||method.getName().equals("loadVipGrowthRecord")
        				){
        			//自身信息
        			if((request.getRequestURI().indexOf("/vipGrowthRecord/list")>-1
        					||method.getName().equals("loadVipGrowthRecord")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//账户上级
        		if( request.getRequestURI().indexOf("/accountParent/delete")>-1 
        				|| request.getRequestURI().indexOf("/accountParent/add")>-1
        				|| request.getRequestURI().equals("/accountParent/list")
        				|| request.getRequestURI().indexOf("/accountParent/update")>-1
        				||method.getName().equals("loadAccountParent")
        				){
        			//自身信息
        			if((request.getRequestURI().indexOf("/accountParent/list")>-1
        					||method.getName().equals("loadAccountParent")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//账户不许删除/增加
        		if( request.getRequestURI().indexOf("/account/delete")>-1 
        				|| request.getRequestURI().indexOf("/account/add")>-1
        				|| request.getRequestURI().equals("/account/list")
        				|| request.getRequestURI().indexOf("/account/update")>-1
        				|| request.getRequestURI().indexOf("/account/updatePasswordByFinanceId")>-1
        				|| request.getRequestURI().indexOf("/account/auth")>-1
        				||method.getName().equals("loadAccount")
        				){
        			//只能管理员使用
        			if(request.getRequestURI().equals("/account/updatePasswordByFinanceId")){
        				throw new MySessionException();
        			}
        			//加载自身账户
        			if((	method.getName().equals("loadAccount")
        					|| request.getRequestURI().indexOf("/account/list")>-1
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			//获取合伙人
        			if((request.getRequestURI().indexOf("/account/list")>-1)
        					&& request.getParameter("masterId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			//不能修改全部
        			if(request.getRequestURI().equals("/account/update")){
        				throw new MySessionException();
        			}
        			//提交修改自身信息
        			if((request.getRequestURI().indexOf("/account/update")>-1
        					||request.getRequestURI().indexOf("/account/auth")>-1)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//财务不许删除/修改/增加
        		if( request.getRequestURI().indexOf("/finance/delete")>-1 
        				|| request.getRequestURI().indexOf("/finance/update")>-1 
        				|| request.getRequestURI().indexOf("/finance/list")>-1 
        				|| request.getRequestURI().indexOf("/finance/add")>-1 
        				|| request.getRequestURI().indexOf("/finance/passwordValid")>-1 
        				||method.getName().equals("loadFinance")){
        			//修改交易密码
        			if((request.getRequestURI().indexOf("/finance/updatePassword")>-1)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			//交易密码验证
        			if((request.getRequestURI().indexOf("/finance/passwordValid")>-1)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			//充值。提现
        			if((request.getRequestURI().indexOf("/finance/recharge")>-1
        					||request.getRequestURI().indexOf("/finance/withdrawals")>-1)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			//加载自身财务
        			if((method.getName().equals("loadFinance"))
        					&& request.getRequestURI().indexOf(sessionFinance.getFinanceId().toString())>-1){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//财务记录
        		if( request.getRequestURI().indexOf("/financeRecord/delete")>-1 
        				|| request.getRequestURI().indexOf("/financeRecord/update")>-1 
        				|| request.getRequestURI().indexOf("/financeRecord/withdrawals")>-1 
        				|| request.getRequestURI().indexOf("/financeRecord/list")>-1 
        				|| request.getRequestURI().indexOf("/financeRecord/add")>-1 
        				||method.getName().equals("loadFinanceRecord")){
        			//自身信息
        			if((request.getRequestURI().indexOf("/financeRecord/list")>-1 
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//积分
        		if( request.getRequestURI().indexOf("/integral/delete")>-1 
        				|| request.getRequestURI().indexOf("/integral/update")>-1 
        				|| request.getRequestURI().indexOf("/integral/list")>-1 
        				|| request.getRequestURI().indexOf("/integral/add")>-1 
        				||method.getName().equals("loadIntegral")){
        			//自身信息
        			if((method.getName().equals("loadIntegral")
        					|| request.getRequestURI().indexOf("/integral/list")>-1 
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//积分详情
        		if( request.getRequestURI().indexOf("/integralDetail/delete")>-1 
        				|| request.getRequestURI().indexOf("/integralDetail/update")>-1 
        				|| request.getRequestURI().indexOf("/integralDetail/list")>-1 
        				|| request.getRequestURI().indexOf("/integralDetail/add")>-1 
        				||method.getName().equals("loadIntegralDetail")){
        			//自身信息
        			if((method.getName().equals("loadIntegralDetail")
        					|| request.getRequestURI().indexOf("/integralDetail/list")>-1 
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//积分榜
        		if( request.getRequestURI().indexOf("/integralBoard/delete")>-1 
        				|| request.getRequestURI().indexOf("/integralBoard/update")>-1 
        				|| request.getRequestURI().indexOf("/integralBoard/list")>-1 
        				|| request.getRequestURI().indexOf("/integralBoard/add")>-1 
        				||method.getName().equals("loadIntegralBoard")){
        			//自身信息
        			if((method.getName().equals("loadIntegralBoard")
        					|| request.getRequestURI().indexOf("/integralBoard/list")>-1 
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//视频集类型
        		if( request.getRequestURI().indexOf("/videoSetCate/delete")>-1 
        				|| request.getRequestURI().indexOf("/videoSetCate/update")>-1 
        				|| request.getRequestURI().indexOf("/videoSetCate/add")>-1){
        			throw new MySessionException();
        		}
        		//视频集标签
        		if( request.getRequestURI().indexOf("/videoSetTag/delete")>-1 
        				|| request.getRequestURI().indexOf("/videoSetTag/update")>-1 
        				|| request.getRequestURI().indexOf("/videoSetTag/add")>-1){
        			throw new MySessionException();
        		}
        		//视频集搜索
        		if( request.getRequestURI().indexOf("/videoSetSearch/delete")>-1 
        				|| request.getRequestURI().indexOf("/videoSetSearch/update")>-1 
        				|| request.getRequestURI().indexOf("/videoSetSearch/add")>-1){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/videoSetSearch/add")>-1
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//视频集
        		if( request.getRequestURI().indexOf("/videoSet/delete")>-1 
        				|| request.getRequestURI().indexOf("/videoSet/update")>-1 
        				|| request.getRequestURI().indexOf("/videoSet/add")>-1){
        			throw new MySessionException();
        		}
        		//视频
        		if( request.getRequestURI().indexOf("/video/delete")>-1
        				||request.getRequestURI().indexOf("/video/list")>-1
        				|| request.getRequestURI().indexOf("/video/update")>-1 
        				|| request.getRequestURI().indexOf("/video/add")>-1
        				|| request.getRequestURI().indexOf("/video/watch")>-1
        				||method.getName().equals("loadVideo")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/video/list")>-1
        					||request.getRequestURI().indexOf("/video/loadVideoSet")>-1
        					||request.getRequestURI().indexOf("/video/watch")>-1
        					||method.getName().equals("loadVideo")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//视频集收藏
        		if( request.getRequestURI().indexOf("/videoSetCollect/delete")>-1 
        				||request.getRequestURI().indexOf("/videoSetCollect/list")>-1
        				|| request.getRequestURI().indexOf("/videoSetCollect/update")>-1 
        				|| request.getRequestURI().indexOf("/videoSetCollect/add")>-1
        				||method.getName().equals("loadVideoSetCollect")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/videoSetCollect/delete")>-1
        					||request.getRequestURI().indexOf("/videoSetCollect/list")>-1
        					|| request.getRequestURI().indexOf("/videoSetCollect/add")>-1
        					|| request.getRequestURI().indexOf("/videoSetCollect/deleteBatch")>-1
        					||method.getName().equals("loadVideoSetCollect")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//视频播放记录
        		if( request.getRequestURI().indexOf("/videoPlayRecord/delete")>-1 
        				|| request.getRequestURI().indexOf("/videoPlayRecord/update")>-1 
                		||request.getRequestURI().indexOf("videoPlayRecord/deleteBatch")>-1
        				|| request.getRequestURI().indexOf("/videoPlayRecord/add")>-1){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/videoPlayRecord/delete")>-1
        					||request.getRequestURI().indexOf("/videoPlayRecord/list")>-1
        					|| request.getRequestURI().indexOf("/videoPlayRecord/update")>-1
        					|| request.getRequestURI().indexOf("/videoPlayRecord/add")>-1
        					|| request.getRequestURI().indexOf("/videoPlayRecord/deleteBatch")>-1
        					||method.getName().equals("loadVideoPlayRecord")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//视频缓存
        		if( request.getRequestURI().indexOf("/videoCache/delete")>-1 
        				|| request.getRequestURI().indexOf("/videoCache/update")>-1 
        				|| request.getRequestURI().indexOf("/videoCache/add")>-1){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/videoCache/delete")>-1
        					||request.getRequestURI().indexOf("/videoCache/list")>-1
        					|| request.getRequestURI().indexOf("/videoCache/add")>-1
        					||method.getName().equals("loadVideoCache")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//vip购买次数
        		if( request.getRequestURI().indexOf("/vipNumber/delete")>-1 
        				|| request.getRequestURI().indexOf("/vipNumber/update")>-1 
        				|| request.getRequestURI().indexOf("/vipNumber/add")>-1){
        			throw new MySessionException();
        		}
        		//订单
        		if( request.getRequestURI().indexOf("/order/delete")>-1 
        				|| request.getRequestURI().indexOf("/order/list")>-1 
        				|| request.getRequestURI().indexOf("/order/update")>-1 
        				|| request.getRequestURI().indexOf("/order/add")>-1
        				|| request.getRequestURI().indexOf("/order/payment")>-1
        				|| request.getRequestURI().indexOf("/order/videoSetIsOrder")>-1
        				||method.getName().equals("loadOrder")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/order/list")>-1
        					|| request.getRequestURI().indexOf("/order/add")>-1
        					|| request.getRequestURI().indexOf("/order/update")>-1
        					|| request.getRequestURI().indexOf("/order/payment")>-1
        					|| request.getRequestURI().indexOf("/order/videoSetIsOrder")>-1
        					||method.getName().equals("loadOrder")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//订单详情
        		if( request.getRequestURI().indexOf("/orderDetail/delete")>-1 
        				|| request.getRequestURI().indexOf("/orderDetail/list")>-1 
        				|| request.getRequestURI().indexOf("/orderDetail/update")>-1 
        				|| request.getRequestURI().indexOf("/orderDetail/add")>-1
        				||method.getName().equals("loadOrderDetail")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/orderDetail/list")>-1
        					||method.getName().equals("loadOrderDetail")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//文章类型
        		if( request.getRequestURI().indexOf("/articleCate/delete")>-1 
        				|| request.getRequestURI().indexOf("/articleCate/update")>-1 
        				|| request.getRequestURI().indexOf("/articleCate/add")>-1){
        			throw new MySessionException();
        		}
        		//文章
        		if( request.getRequestURI().indexOf("/article/delete")>-1 
        				|| request.getRequestURI().indexOf("/article/update")>-1 
        				|| request.getRequestURI().indexOf("/article/add")>-1){
        			throw new MySessionException();
        		}
        		//文章评论
        		if( request.getRequestURI().indexOf("/articleComment/delete")>-1 
        				|| request.getRequestURI().indexOf("/articleComment/update")>-1 
        				|| request.getRequestURI().indexOf("/articleComment/point")>-1 
        				|| request.getRequestURI().indexOf("/articleComment/add")>-1){
        			//自身
        			if((request.getRequestURI().indexOf("/articleComment/add")>-1
        					||request.getRequestURI().indexOf("/articleComment/delete")>-1 
            				|| request.getRequestURI().indexOf("/articleComment/update")>-1 
        					|| request.getRequestURI().indexOf("/articleComment/point")>-1 
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//通知
        		if( request.getRequestURI().indexOf("/notice/delete")>-1 
        				|| request.getRequestURI().indexOf("/notice/update")>-1 
        				|| request.getRequestURI().indexOf("/notice/list")>-1 
        				|| request.getRequestURI().indexOf("/notice/add")>-1 
        				||method.getName().equals("loadFinance")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/notice/list")>-1
        					||method.getName().equals("loadNotice")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//收货信息
        		if( request.getRequestURI().indexOf("/receiptInfo/delete")>-1 
        				|| request.getRequestURI().indexOf("/receiptInfo/update")>-1 
        				|| request.getRequestURI().indexOf("/receiptInfo/list")>-1 
        				|| request.getRequestURI().indexOf("/receiptInfo/add")>-1 
        				||method.getName().equals("loadReceiptInfo")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/receiptInfo/list")>-1
        					||request.getRequestURI().indexOf("/receiptInfo/add")>-1
        					||request.getRequestURI().indexOf("/receiptInfo/update")>-1
        					||request.getRequestURI().indexOf("/receiptInfo/delete")>-1
        					||method.getName().equals("loadReceiptInfo")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//团购信息
        		if( request.getRequestURI().indexOf("/teamPurchaseInfo/delete")>-1 
        				|| request.getRequestURI().indexOf("/teamPurchaseInfo/list")>-1 
        				|| request.getRequestURI().indexOf("/teamPurchaseInfo/update")>-1 
        				|| request.getRequestURI().indexOf("/teamPurchaseInfo/add")>-1
        				||method.getName().equals("loadTeamPurchaseInfo")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/teamPurchaseInfo/list")>-1
        					||method.getName().equals("loadTeamPurchaseInfo")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//分发
        		if( request.getRequestURI().indexOf("/distribute/delete")>-1 
        				|| request.getRequestURI().indexOf("/distribute/list")>-1 
        				|| request.getRequestURI().indexOf("/distribute/update")>-1 
        				|| request.getRequestURI().indexOf("/distribute/add")>-1
        				||method.getName().equals("loadDistribute")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/distribute/list")>-1
        					||method.getName().equals("loadDistribute")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//拆分
        		if( request.getRequestURI().indexOf("/split/delete")>-1 
        				|| request.getRequestURI().indexOf("/split/list")>-1 
        				|| request.getRequestURI().indexOf("/split/update")>-1 
        				|| request.getRequestURI().indexOf("/split/recommendParent")>-1 
        				|| request.getRequestURI().indexOf("/split/immediatelySplit")>-1 
        				|| request.getRequestURI().indexOf("/split/add")>-1
        				||method.getName().equals("loadSplit")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/split/list")>-1
        					||request.getRequestURI().indexOf("/split/recommendParent")>-1
        					||request.getRequestURI().indexOf("/split/immediatelySplit")>-1
        					||method.getName().equals("loadSplit")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//app版本不许删除/修改/增加
        		if( request.getRequestURI().indexOf("/appVersion/delete")>-1 
        				|| request.getRequestURI().indexOf("/appVersion/update")>-1 
        				|| request.getRequestURI().indexOf("/appVersion/add")>-1){
        			throw new MySessionException();
        		}
        		//勋章项
        		if( request.getRequestURI().indexOf("/medalTerm/delete")>-1 
        				|| request.getRequestURI().indexOf("/medalTerm/update")>-1 
        				|| request.getRequestURI().indexOf("/medalTerm/add")>-1){
        			throw new MySessionException();
        		}
        		//勋章
        		if( request.getRequestURI().indexOf("/medal/delete")>-1 
        				|| request.getRequestURI().indexOf("/medal/update")>-1 
        				|| request.getRequestURI().indexOf("/medal/add")>-1){
        			throw new MySessionException();
        		}
        		//意见反馈
        		if( request.getRequestURI().indexOf("/feedback/delete")>-1 
        				|| request.getRequestURI().indexOf("/feedback/list")>-1 
        				|| request.getRequestURI().indexOf("/feedback/update")>-1 
        				|| request.getRequestURI().indexOf("/feedback/add")>-1
        				||method.getName().equals("loadFeedback")){
        			//自身
        			if((
        					request.getRequestURI().indexOf("/feedback/list")>-1
        					|| request.getRequestURI().indexOf("/feedback/add")>-1
        					||method.getName().equals("loadFeedback")
        					)
        					&& request.getParameter("accountId").equals(sessionAccount.getAccountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//配置
        		if( request.getRequestURI().indexOf("/config/delete")>-1 
        				|| request.getRequestURI().indexOf("/config/update")>-1 
        				|| request.getRequestURI().indexOf("/config/add")>-1){
        			throw new MySessionException();
        		}
        		//第三方支付
        		if( request.getRequestURI().indexOf("/payment/delete")>-1 
        				|| request.getRequestURI().indexOf("/payment/update")>-1 
        				|| request.getRequestURI().indexOf("/payment/list")>-1 
        				|| request.getRequestURI().indexOf("/payment/add")>-1
        				||method.getName().equals("loadPayment")){
        			throw new MySessionException();
        		}
        		return true;
        	}
        	}
        	
        }
        //如果验证token失败
       throw new MySessionException();
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
