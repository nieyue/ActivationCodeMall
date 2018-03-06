package com.nieyue.weixin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.ConnectException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.nieyue.bean.Payment;
import com.nieyue.util.MyDom4jUtil;
import com.nieyue.weixin.bean.UnifiedOrder;

import net.sf.json.JSONObject;

/**
 * 微信统一下单工具类
 * @author yy
 *
 */
@Configuration
public class UnifiedOrderUtil {
	// 第三方用户唯一凭证
	@Value("${myPugin.weixin.weChatPayment.APPID}") 
	public String appid ;
	 // 第三方用户唯一凭证密钥
	@Value("${myPugin.weixin.weChatPayment.SECRET}") 
	 public String appsecret ;
	 //商户ID
	@Value("${myPugin.weixin.weChatPayment.MCH_ID}") 
	 public  String mch_id;
	 //商户api秘钥key
	@Value("${myPugin.weixin.weChatPayment.API}") 
	 public  String key;
	
	 
	 public  JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr) {
		  JSONObject jsonObject = null;
		  try {
		  StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
		  jsonObject = JSONObject.fromObject(buffer.toString());
		  } catch (ConnectException ce) {
		  } catch (Exception e) {
		  }
		  return jsonObject;
		 }
		  
		 private  StringBuffer httpsRequest(String requestUrl, String requestMethod, String output)
		  throws Exception {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
	      //SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	      // 从上述SSLContext对象中得到SSLSocketFactory对象
	     // SSLSocketFactory ssf = sslContext.getSocketFactory(); 
		  URL url = new URL(requestUrl);
		  HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		  //connection.setSSLSocketFactory(ssf);
		  connection.setDoOutput(true);
		  connection.setDoInput(true);
		  connection.setUseCaches(false);
		  connection.setRequestMethod(requestMethod);
		  if (null != output) {
		  OutputStream outputStream = connection.getOutputStream();
		  outputStream.write(output.getBytes("UTF-8"));
		  outputStream.close();
		  }
		  // 从输入流读取返回内容
		  InputStream inputStream = connection.getInputStream();
		  InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		  BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		  String str = null;
		  StringBuffer buffer = new StringBuffer();
		  while ((str = bufferedReader.readLine()) != null) {
		  buffer.append(str);
		  }
		  bufferedReader.close();
		  inputStreamReader.close();
		  inputStream.close();
		  inputStream = null;
		  connection.disconnect();
		  return buffer;
		 } 
		 
		 public  Map<String, Object> httpsRequestToXML(String requestUrl, String requestMethod, String outputStr) {
			  Map<String, Object> result = new HashMap<String,Object>();
			  try {
			  StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr);
			  result = MyDom4jUtil.xmlStrToMap(buffer.toString());
			  }  catch (Exception e) {
			  }
			  return result;
			 }
		 
	/**
	 * 随机字符串：我用的是UUID去中划线
	 * @return
	 */
	public  String createNonceStr() {
		      return UUID.randomUUID().toString().replace("-","");
	 }
	/**
	 * 创建统一下单的java对象
	 * @param order 系统中的业务单号
	 * @param ip 用户的ip地址
	 * @param openId 用户的openId
	 * @return
	 */
	 public  UnifiedOrder createUnifiedOrder(Payment payment,String ip,String openid,String trade_type) {
		  UnifiedOrder unifiedOrder = new UnifiedOrder();
		  unifiedOrder.setAppid(appid);
		  unifiedOrder.setDeviceInfo("WEB");
		  unifiedOrder.setMchId(mch_id);
		  unifiedOrder.setNonceStr(createNonceStr());
		  unifiedOrder.setBody(payment.getBody());
		  unifiedOrder.setAttach(payment.getPaymentId().toString());
		  unifiedOrder.setOutTradeNo(payment.getOrderNumber());
		  unifiedOrder.setTotalFee( Integer.valueOf((int) (payment.getMoney()*100)));
		  unifiedOrder.setSpbillCreateIp(ip);
		 // unifiedOrder.setNotifyUrl("http://nieyue.tea18.cn/weixin/notifyUrl");
		  unifiedOrder.setNotifyUrl(payment.getNotifyUrl());
		  unifiedOrder.setTradeType(trade_type);
		  if(openid!=null&&!openid.equals("") ){
			  unifiedOrder.setOpenid(openid);
		  }
		  return unifiedOrder;
		 }
	 /**
	  * 获取统一下单签名
	  * @param payInfo
	  * @return
	  * @throws Exception
	  */
	  public  String getSign(UnifiedOrder unifiedOrder) throws Exception {
		 //匹配微信内h5支付和扫码支付
		  String oi="";
		  if(unifiedOrder.getOpenid()!=null&&!unifiedOrder.getOpenid().equals("")){
			 oi="&openid="+unifiedOrder.getOpenid();
		  }
		String signTemp = "appid="+unifiedOrder.getAppid()
	    +"&attach="+unifiedOrder.getAttach()
	    +"&body="+unifiedOrder.getBody()
	    +"&device_info="+unifiedOrder.getDeviceInfo()
	    +"&mch_id="+unifiedOrder.getMchId()
	    +"&nonce_str="+unifiedOrder.getNonceStr()
	    +"&notify_url="+unifiedOrder.getNotifyUrl()
	    +oi
	    +"&out_trade_no="+unifiedOrder.getOutTradeNo()
	    +"&spbill_create_ip="+unifiedOrder.getSpbillCreateIp()
	    +"&total_fee="+unifiedOrder.getTotalFee()
	    +"&trade_type="+unifiedOrder.getTradeType()
	    +"&key="+key; //这个key注意
	  MessageDigest md = MessageDigest.getInstance("MD5");
	  md.reset();
	  md.update(signTemp.getBytes("UTF-8"));
	   String sign = byteToStr(md.digest()).toUpperCase();
	 // String md = DigestUtils.md5Hex(signTemp);
		 // String sign = md.toUpperCase();
		  return sign;
	  }
	  /**
	   * 获取APP支付签名
	   * @param payInfo
	   * @return
	   * @throws Exception
	   * 大小写敏感
	   */
	  public  String getAPPPaySign(Map<String,String> map) throws Exception {
		  String signTemp = "appid="+map.get("appid")
		  +"&noncestr="+map.get("noncestr")
		  +"&package="+map.get("package")
		  +"&partnerid="+map.get("partnerid")
		  +"&prepayid="+map.get("prepayid")
		  +"&timestamp="+map.get("timestamp")
		  +"&key="+key; //这个key注意
		  MessageDigest md = MessageDigest.getInstance("MD5");
		  md.reset();
		  md.update(signTemp.getBytes("UTF-8"));
		  String sign = byteToStr(md.digest()).toUpperCase();
		  return sign;
	  }
	  /**
	   * 获取微信APP支付签名map
	   * @return
	   */
	  public  Map<String ,String> getAPPPaySignMap(String prepay_id){
		  Map<String,String> m2=new HashMap<String,String>();
		  m2.put("appid", appid);
		  m2.put("partnerid", mch_id);
		  m2.put("prepayid",prepay_id);
		  m2.put("package","Sign=WXPay");
		  m2.put("noncestr", createNonceStr());
		  m2.put("timestamp", Long.toString(System.currentTimeMillis() / 1000));
		  return m2;
	  }
	  /**
	   * 获取公众号支付签名
	   * @param payInfo
	   * @return
	   * @throws Exception
	   * 大小写敏感
	   */
	  public  String getPaySign(Map<String,String> map) throws Exception {
		  String signTemp = "appId="+map.get("appid")
		  +"&nonceStr="+map.get("nonce_str")
		  +"&package="+map.get("package_value")
		  +"&signType="+map.get("sign_type")
		  +"&timeStamp="+map.get("time_stamp")
		  +"&key="+key; //这个key注意
		  MessageDigest md = MessageDigest.getInstance("MD5");
		  md.reset();
		  md.update(signTemp.getBytes("UTF-8"));
		  String sign = byteToStr(md.digest()).toUpperCase();
		  return sign;
	  }
	  /**
	   * 获取微信公众号支付签名map
	   * @return
	   */
	  public  Map<String ,String> getPaySignMap(String prepay_id){
		  Map<String,String> m2=new HashMap<String,String>();
		  m2.put("appid", appid);
		 // m2.put("nonce_str", nonce_str);
		  m2.put("nonce_str", createNonceStr());
		  m2.put("package_value","prepay_id="+prepay_id);
		  m2.put("sign_type","MD5");
		  m2.put("time_stamp", Long.toString(System.currentTimeMillis() / 1000));
		  return m2;
	  }
	  /**
		 * 订单查询
		 * @param order 系统中的业务单号
		 * @param ip 用户的ip地址
		 * @param openId 用户的openId
		 * @return
	 * @throws Exception 
		 */
		 public  UnifiedOrder queryOrder(String order_id) throws Exception {
			  UnifiedOrder unifiedOrder = new UnifiedOrder();
			  unifiedOrder.setAppid(appid);
			  unifiedOrder.setMchId(mch_id);
			  //unifiedOrder.setTransactionId(order_id);
			  unifiedOrder.setOutTradeNo(order_id);
			  String nonce_str=createNonceStr();
			  //String nonce_str="oz7uXO7L6QtUkcqx";
			  unifiedOrder.setNonceStr(nonce_str);
			  unifiedOrder.setSign(getQueryOrderSign(order_id,nonce_str));
			  return unifiedOrder;
			 }
		 /**
		   * 订单查询签名
		   * @return
		   * @throws Exception
		   * 大小写敏感
		   */
		  public  String getQueryOrderSign(String order_id,String nonce_str) throws Exception {
			  String signTemp = "appid="+appid
					  +"&mch_id="+mch_id
					  +"&nonce_str="+nonce_str
					  +"&out_trade_no="+order_id
					 // +"&transaction_id="+order_id
			  		  +"&key="+key; //这个key注意
			  MessageDigest md = MessageDigest.getInstance("MD5");
			  md.reset();
			  md.update(signTemp.getBytes("UTF-8"));
			  String sign = byteToStr(md.digest()).toUpperCase();
			  return sign;
		  }
		  /**
			 * 关闭订单
			 * @return
		 * @throws Exception 
			 */
			 public  UnifiedOrder closeOrder(String order_id) throws Exception {
				  UnifiedOrder unifiedOrder = new UnifiedOrder();
				  unifiedOrder.setAppid(appid);
				  unifiedOrder.setMchId(mch_id);
				  //unifiedOrder.setTransactionId(order_id);
				  unifiedOrder.setOutTradeNo(order_id);
				  String nonce_str=createNonceStr();
				  //String nonce_str="oz7uXO7L6QtUkcqx";
				  unifiedOrder.setNonceStr(nonce_str);
				  unifiedOrder.setSign(getCloseOrderSign(order_id,nonce_str));
				  return unifiedOrder;
				 }
			 /**
			   * 关闭订单签名
			   * @return
			   * @throws Exception
			   * 大小写敏感
			   */
			  public  String getCloseOrderSign(String order_id,String nonce_str) throws Exception {
				  String signTemp = "appid="+appid
						  +"&mch_id="+mch_id
						  +"&nonce_str="+nonce_str
						  +"&out_trade_no="+order_id
						 // +"&transaction_id="+order_id
				  		  +"&key="+key; //这个key注意
				  MessageDigest md = MessageDigest.getInstance("MD5");
				  md.reset();
				  md.update(signTemp.getBytes("UTF-8"));
				  String sign =byteToStr(md.digest()).toUpperCase();
				  return sign;
			  }
			  /**
				 * 申请退款
				 * @return
			 * @throws Exception 
				 */
				 public  UnifiedOrder refund(String order_id,Integer total_fee,Integer refund_fee) throws Exception {
					  UnifiedOrder unifiedOrder = new UnifiedOrder();
					  unifiedOrder.setAppid(appid);
					  unifiedOrder.setMchId(mch_id);
					  String nonce_str=createNonceStr();
					  unifiedOrder.setNonceStr(nonce_str);
					  unifiedOrder.setOutTradeNo(order_id);
					  unifiedOrder.setOutRefundNo(order_id+"refund");
					  unifiedOrder.setTotalFee(total_fee);
					  unifiedOrder.setRefundFee(refund_fee);
					  unifiedOrder.setOpUserId(mch_id);
					  unifiedOrder.setSign(getRefundSign(order_id,nonce_str, total_fee, refund_fee));
					  return unifiedOrder;
					 }
				 /**
				   * 退款签名
				   * @return
				   * @throws Exception
				   * 大小写敏感
				   */
				  public  String getRefundSign(String order_id,String nonce_str,Integer total_fee,Integer refund_fee) throws Exception {
					  String signTemp = "appid="+appid
							  +"&mch_id="+mch_id
							  +"&nonce_str="+nonce_str
							  +"&op_user_id="+mch_id
							  +"&out_refund_no="+order_id+"refund"
							  +"&out_trade_no="+order_id
							  +"&refund_fee="+refund_fee
							  +"&total_fee="+total_fee
					  		  +"&key="+key; //这个key注意
					  MessageDigest md = MessageDigest.getInstance("MD5");
					  md.reset();
					  md.update(signTemp.getBytes("UTF-8"));
					  String sign = byteToStr(md.digest()).toUpperCase();
					  return sign;
				  }
	  /**
	  * 将字节数组转换为十六进制字符串
	  * 
	  * @param byteArray
	  * @return
	  */
	  public  String byteToStr(byte[] byteArray) {
	   String strDigest = "";
	   for (int i = 0; i < byteArray.length; i++) {
	   strDigest += byteToHexStr(byteArray[i]);
	   }
	   return strDigest;
	  }
	  /**
	  * 将字节转换为十六进制字符串
	  * 
	  * @param btyes
	  * @return
	  */
	  public  String byteToHexStr(byte bytes) {
	   char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	   char[] tempArr = new char[2];
	   tempArr[0] = Digit[(bytes >>> 4) & 0X0F];
	   tempArr[1] = Digit[bytes & 0X0F];
	   String s = new String(tempArr);
	   return s;
	  }
	  /**
	  * 获取ip地址
	  * @param request
	  * @return
	  */
	  public  String getIpAddr(HttpServletRequest request) { 
	   InetAddress addr = null; 
	   try { 
	   addr = InetAddress.getLocalHost(); 
	   } catch (UnknownHostException e) { 
	   return request.getRemoteAddr(); 
	   } 
	   byte[] ipAddr = addr.getAddress(); 
	   String ipAddrStr = ""; 
	   for (int i = 0; i < ipAddr.length; i++) { 
	   if (i >0 ) { 
	    ipAddrStr += "."; 
	   } 
	   ipAddrStr += ipAddr[i] & 0xFF; 
	   } 
	   return ipAddrStr; 
	  }
	 
}
