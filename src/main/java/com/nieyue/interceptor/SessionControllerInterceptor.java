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
        Integer i=1;
        Integer j=1;
        if(i.equals(j)){
        	return true;
        }
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
        		//登陆、登出、增加师傅徒弟关系、账户
        		||request.getRequestURI().indexOf("account/count")>-1
        		||request.getRequestURI().indexOf("account/validCodeEmail")>-1
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
        		//角色全不许
        		if( request.getRequestURI().indexOf("/role")>-1 ){
        			throw new MySessionException();
        		}
        		//等级不许删除/修改/增加
        		if( request.getRequestURI().indexOf("/accountLevel/delete")>-1 
        				|| request.getRequestURI().indexOf("/accountLevel/add")>-1
        				|| request.getRequestURI().equals("/accountLevel/list")
        				|| request.getRequestURI().indexOf("/accountLevel/update")>-1
        				|| request.getRequestURI().indexOf("/accountLevel/load")>-1
        				){
        			//自身信息
        			if((
        					request.getRequestURI().indexOf("/accountLevel/list")>-1
        					|| request.getRequestURI().indexOf("/accountLevel/load")>-1
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
