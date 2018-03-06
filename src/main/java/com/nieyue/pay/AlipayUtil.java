package com.nieyue.pay;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.nieyue.bean.Payment;
import com.nieyue.business.FinanceBusiness;
import com.nieyue.service.PaymentService;

import net.sf.json.JSONObject;

/**
 * 阿里云支付
 * @author 聂跃
 * @date 2017年9月12日
 */
@Configuration
public class AlipayUtil {
	@Value("${myPugin.alibaba.alipay.APP_ID}")
	 String APP_ID;
	 @Value("${myPugin.alibaba.alipay.APP_PRIVATE_KEY}")
	 String APP_PRIVATE_KEY;
	 @Value("${myPugin.alibaba.alipay.APP_PUBLIC_KEY}")
	 String APP_PUBLIC_KEY;
	 @Value("${myPugin.alibaba.alipay.ALIPAY_PUBLIC_KEY}")
	 String ALIPAY_PUBLIC_KEY;
	 @Resource
	 PaymentService paymentService;
	 @Resource
	 FinanceBusiness financeBusiness;
	 /**
	  * 支付宝app支付
	  * @param payment
	  * @return
	  * @throws UnsupportedEncodingException
	  */
	 public String getAppPayment(Payment payment) throws UnsupportedEncodingException{
		 //实例化客户端
		 AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");
		 //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		 AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		 //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		 AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		 //存储payment
		boolean b = paymentService.addPayment(payment);
		if(!b){
			return null;
		}
		 model.setBody(payment.getBody());//主题
		 model.setSubject(payment.getSubject());//名称
		 model.setOutTradeNo(payment.getOrderNumber());//订单号
		 model.setTimeoutExpress("30m");
		 model.setTotalAmount(String.valueOf(payment.getMoney()));//金额
		 model.setProductCode("QUICK_MSECURITY_PAY");
		 model.setPassbackParams(payment.getPaymentId().toString());//支付id
		 request.setBizModel(model);
		 if(payment.getNotifyUrl()!=null && !payment.getNotifyUrl().equals("")){
			 request.setNotifyUrl(payment.getNotifyUrl());//回调
		 }
		 try {
			 //这里和普通的接口调用不同，使用的是sdkExecute
			 AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			 // System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
			 return response.getBody();
		 } catch (AlipayApiException e) {
			 e.printStackTrace();
			 return "";
		 }
		 
	 }
	 /**
	  * 统一收单线下交易查询
	  * @param outTradeNo自己平台订单号
	  * @param tradeNo支付宝平台订单号
	  * 
	  * @return
	 * @throws AlipayApiException 
	  */
	 public String getAlipayTradeQuery(String outTradeNo ) throws AlipayApiException{
		 //String APP_ID="2017081808253496"; 
	    // String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnAu8NDakgD5RRtYl70Q5pz7k1QIQ2YL80yVBL5/ciwgjOYsV+GXq4hJkNOzIOZArblMZvSaGGAmugkBKVhdMiXhBkVxH6OAZayUPsve1dmjBi2K5NO8Qm34z3waml8F+lGWxw21GGQIUvxbZk1tv4Pb1fDDqOpOXNYseLmWLfw9mVZ217LOVlgVMVBJ/1CTkqDUO7hUzHYTT0h4BzpxvlQltq4TIX6rhBMrv6gai3BXQqy7tZRZh5pXkOZVMlCnYdxM2QLLKrtGSpcFP+HIGGH8Yy8T4dvuNTzzQRwjrKSu+EiyEtyfMfYtKLRHLWDGfvmr8/z/2RluBQsKEdmoTRAgMBAAECggEAPNWUvex77nG+VQULT06XMXO4wuz9O9GB1HiAByY3nzGOg5720Tf2u7+RAIDPeu52BgXrQh5P98Bp89KG8nAMuDgLQIF2yu4dYnRtiY6lJ7NCbL8AvqFGkeJcNuMUKJ2mOT1JgtJgn8ZgvUYHajPZbFMQ585MhikKjAWvKep1WYwkAnXnBpO1no1Tf2CvgEFbJzRpOKUyuBeWLXuf+kl3kPBQTiXsT5rza7SS6uvmEEceIyP0oiJU9+hiELfMnoSUj5fcdBrBfbUwMFso14noLmALCSDNz02jsXEQreSYlTMvl7Q1Vj8xUs4BZQQE4KRhEhjsoXPnqy+gGg8S5KwMAQKBgQD6xl28L/c/bOFR0Ebh+x2dti00iVvE5sccl8XlhJkhYSG8uU2gZvXxxM4i8ybmU4YBd/NWHGS9WDxY8Hj5b2wev4oX1h3sKNFMLw+NGcsuIOojDWAHSKejTZi5qsEE5KVA1d1KtDCZueUjcJ9iqs99wmQy8FKxyHSged9EwKEZXQKBgQCqfcX809hZP/iw1P8gdD+7RmkmDEJ1t/+Ev2V+N1xfH375/d0QxieH9XGnGpWZ77XbQOR+kQzgVvj5wa2UfoclDMQwc3XWCNIhrxqAV8NCUed3d3TUK/XCb68/u4m4XaDToJGO/B/JwtawnazZjlAdESNYNb8V6zNJWAcZPAq+BQKBgEM9FLz0j3KuGuv40EifMB2tTwmbGsP8rL5541Ha78QHayhS9wFfGeqtPigY0gFHu9KA+vnHuysZkXS3ZXhb672C1d/2RSeg+h1XMPBATj4cDStA1tKdJHWgxKZjpkVNmRF9RkxVbAKL2WhbNiEac2/gw0T1MvcALGUqyDaC/67xAoGADpbEomXeVYTL2xXe3wATlArqZH8YEMq16r4WCT7jgKBJVUsL56uNJm0yVIJJ2upQPXrIEqyG1YubsoOIPvS0y87T2vGGW9JVaNx7ABCCfOJVVDu8m4tHdGPqMyHxHv2kDXfn/LxGXBV4GZTkfZlX8aTNyXaP62g880G9GcOxh6ECgYEA1Eq8E9TtkjnW7iZbM+xtMiqCRn7a3Weghi81UjjafRrCRnzbgylagC2MQIyxKmv08MBs40Fwsa5870c4MBSsPWQum5QpUM5vrbY11iG8i2YHd3CZAj6GMUFJshjmOLm+27ORzgzhHzm0XxMrPonfNabGV8fKQkvziJ40OUwLRsc=";
	    // String ALIPAY_COMMENT_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgLp4pohzptgY+59o11fAxeY5xwEMQPS5a0uauzRzHkAOjZR2nKx/V7uyZOYNCA+FQtKop+xabwMYs+0MsQubTc6C8E8viZ+o1Q3W0pKn6oYDh6rMqcYQGKEQ61NTgy/xAJUCbFgFM2lwrh4QfwtYGvuio2v8A7j3TN8szKErGwvrxAjAz26auuhSZfUqJgGqP++4ChzeHYnlJeoay7PXFfIOiL5Yl6HqgC0eVU36GGmvYY3rAJgbtnQvmVfQxAzD6JhFMkhYFj1wwFtFYr5s+pF1igZSs4VlftsMfVCBpsojQXddgqF6OL2d63ZfMi2OXfFriUf7nG3BpXhF+kag1wIDAQAB";

		 AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",APP_ID,APP_PRIVATE_KEY,"json","UTF-8",ALIPAY_PUBLIC_KEY,"RSA2");
		 AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		 request.setBizContent("{" +
		 "\"out_trade_no\":\""+outTradeNo+"\"" +
		// "\"out_trade_no\":\""+outTradeNo+"\"," +
		 //"\"trade_no\":\""+tradeNo+"\"" + 
		 "  }");
		 AlipayTradeQueryResponse response = alipayClient.execute(request);
		 if(response.isSuccess()){
		 System.out.println("调用成功");
		 return response.getBody();
		 } else {
		 System.out.println("调用失败");
		 return response.getMsg();
		 }
		 
	 }
	 /**
	  * 异步通知
	  * @param paramsMap
	 * @throws Exception 
	  */
	 public String  getNotifyUrl(HttpServletRequest request){
		 //将异步通知中收到的待验证所有参数都存放到map中
		 boolean signVerified=true;
		 Payment payment= null;
				try {
					//调用SDK验证签名
					//获取支付宝POST过来反馈信息
					Map<String,String> params = new HashMap<String,String>();
					 Map<String, String[]> requestParams = request.getParameterMap();
					for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					    String name = (String) iter.next();
					    String[] values = (String[]) requestParams.get(name);
					    String valueStr = "";
					    for (int i = 0; i < values.length; i++) {
					        valueStr = (i == values.length - 1) ? valueStr + values[i]
					                    : valueStr + values[i] + ",";
					  	}
					    //乱码解决，这段代码在出现乱码时使用。
						//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
						params.put(name, valueStr);
					}
					//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
					//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
					signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, "UTF-8","RSA2");
					
					//signVerified = AlipaySignature.rsaCheckV1(paramsMap, ALIPAY_PUBLIC_KEY, "UTF-8");
					 String paymentId = (String) params.get("passback_params");
					 String out_trade_no = (String) params.get("out_trade_no");
					 String total_amount = (String) params.get("total_amount");
//					 String paymentId = (String) request.getParameter("passback_params");
//					 String out_trade_no = (String) request.getParameter("out_trade_no");
//					 String total_amount = (String) request.getParameter("total_amount");
					 //String paymentId = "5";
					 payment = paymentService.loadPayment(Integer.valueOf(paymentId));
					 //已经处理过了
					 if(payment.getStatus()!=1){
						 return "success";
					 }
					 if(!out_trade_no.equals(payment.getOrderNumber())
							 ||!total_amount.equals(payment.getMoney().toString())){
						 signVerified=false;
					 }
					 if(signVerified &&payment!=null){
					    // TODO 验签成功后
					    //按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
					String businessNotifyUrl=payment.getBusinessNotifyUrl();//
					String nickname=null;
					String phone=null;
					String contactPhone=null;
					if(businessNotifyUrl!=null&&!businessNotifyUrl.equals("")){
						JSONObject jsono = JSONObject.fromObject(businessNotifyUrl);
						nickname=jsono.getString("nickname");
						phone=jsono.getString("phone");
						contactPhone=jsono.getString("contactPhone");
					}
					int fbi=financeBusiness.financeExcute(payment.getBusinessType(), payment.getType(), payment.getAccountId(), payment.getBusinessId(), payment.getOrderNumber(), nickname, phone, contactPhone);
					if(fbi==1){
						 //支付成功
						 payment.setStatus(2);//成功
						 paymentService.updatePayment(payment);
						 return "success";
					}
					 }else{
						 payment.setStatus(3);//失败
						 paymentService.updatePayment(payment);
					 }
				} catch (Exception e) {
					payment.setStatus(4);//异常
					 paymentService.updatePayment(payment);
				} 
				return "fail";
				
	 }
	 /**
	  * 充值异步通知
	  * @param paramsMap
	  * @throws Exception 
	  */
	 public String  getRechargeNotifyUrl(HttpServletRequest request){
		 //将异步通知中收到的待验证所有参数都存放到map中
		 boolean signVerified=true;
		 Payment payment= null;
		 try {
			 //调用SDK验证签名
			 //获取支付宝POST过来反馈信息
			 Map<String,String> params = new HashMap<String,String>();
			 Map<String, String[]> requestParams = request.getParameterMap();
			 for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				 String name = (String) iter.next();
				 String[] values = (String[]) requestParams.get(name);
				 String valueStr = "";
				 for (int i = 0; i < values.length; i++) {
					 valueStr = (i == values.length - 1) ? valueStr + values[i]
							 : valueStr + values[i] + ",";
				 }
				 //乱码解决，这段代码在出现乱码时使用。
				 //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
				 params.put(name, valueStr);
			 }
			 //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
			 //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			 signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, "UTF-8","RSA2");
			 
			 //signVerified = AlipaySignature.rsaCheckV1(paramsMap, ALIPAY_PUBLIC_KEY, "UTF-8");
			 String paymentId = (String) params.get("passback_params");
			 String out_trade_no = (String) params.get("out_trade_no");
			 String total_amount = (String) params.get("total_amount");
			 payment = paymentService.loadPayment(Integer.valueOf(paymentId));
			 //已经处理过了
			 if(payment.getStatus()!=1){
				 return "success";
			 }
			 if(!out_trade_no.equals(payment.getOrderNumber())
					 ||!total_amount.equals(payment.getMoney().toString())){
				 signVerified=false;
			 }
			 if(signVerified &&payment!=null){
				 // TODO 验签成功后
				 //按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
				 boolean b = financeBusiness.rechargeFinance(payment);
				 if(b){
					 //支付成功
					 payment.setStatus(2);//成功
					 paymentService.updatePayment(payment);
					 return "success";
				 }
			 }else{
				 payment.setStatus(3);//失败
				 paymentService.updatePayment(payment);
			 }
		 } catch (Exception e) {
			 payment.setStatus(4);//异常
			 paymentService.updatePayment(payment);
		 } 
		 return "fail";
		 
	 }
	 
}
