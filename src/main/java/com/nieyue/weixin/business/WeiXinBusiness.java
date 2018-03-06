package com.nieyue.weixin.business;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.nieyue.bean.Payment;
import com.nieyue.util.HttpClientUtil;
import com.nieyue.util.MyConvertXML;
import com.nieyue.util.MyDom4jUtil;
import com.nieyue.weixin.CheckUtil;
import com.nieyue.weixin.PastUtil;
import com.nieyue.weixin.UnifiedOrderUtil;
import com.nieyue.weixin.WeiXinHttpClientUtil;
import com.nieyue.weixin.WeiXinJSToken;
import com.nieyue.weixin.bean.UnifiedOrder;

import net.sf.json.JSONObject;

/**
 * 微信支付
 * @author yy
 *
 */
@Component
public class WeiXinBusiness {
	
	@Resource
	UnifiedOrderUtil unifiedOrderUtil;
	@Resource
	WeiXinHttpClientUtil weiXinHttpClientUtil;
	/**
	 * 微信jssdk接口入口,返回配置数据
	 * @param url 访问路径
	 * @return 配置数据
	 */
	public  String WXJSSDK(String appid ,String secret,String url){
		String json = PastUtil.getParam(appid,secret,url);
		return json;
	}
	/**
	 * 微信服务器接口入口,返回配置数据
	 * @param signature 加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @param echostr 随机字符串
	 * @return echostr
	 */
	public  String WXServer(String signature,String timestamp,String nonce,String echostr){
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			return echostr;
		}	
		return "";
	}
	/**
	 * 微信授权访问
	 * @return redirect_url
	 */
	public  String WXAuth(String appid,String redirect_url,String snsapi_base,String state){
		String aurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+redirect_url+"&response_type=code&scope="+snsapi_base+"&state="+state+"#wechat_redirect";
		return aurl;
	}
	/**
	 * 微信登录获取openid
	 * @return openid
	 * 
	 */
	public  String WXOpenid(String appid,String secret,String code){
		if(null != code && !"".equals(code)){
            //根据code换取openId
        String at = WeiXinJSToken.loadJson("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code");
            if(!"".equals(at) && null != at){
            	JSONObject jo =JSONObject.fromObject(at);
            	String openid=jo.get("openid").toString();
            	return openid;
            }
            }
		return "";
	}
	/**
	 * 微信支付 统一下单
	 * @param order 订单
	 * @param ip 访问ip
	 * @param openid 用户openid
	 * @return prepay_id ，code_url（trade_type为NATIVE有返回）
	 * @throws Exception 
	 */
	public  String WXUnifiedorder(Payment payment,String ip,String openid,String trade_type) throws Exception {
		UnifiedOrder uo = unifiedOrderUtil.createUnifiedOrder(payment,ip,openid,trade_type);//创建微信订单
		String sign = unifiedOrderUtil.getSign(uo);
		uo.setSign(sign);//设置签名
		String xxml = MyConvertXML.getUnderlineXML(uo,true);//对象转xml 驼峰转下划线
		String result = HttpClientUtil.doPostJson("https://api.mch.weixin.qq.com/pay/unifiedorder", xxml);//获取统一下单
    	
		return result;
	}
	
	/**
	 * 微信统一下单notify_url
	 * @throws Exception 
	 */
	public  String WXNotifyUrl( String rxml) throws Exception  {
    	Map<String, Object> map = MyDom4jUtil.xmlStrToMap(rxml);
		//System.out.println(map);
		return "SUCCESS";
	}
	/**
	 * 微信订单查询
	 * @param out_trade_no 商户订单号
	 * @throws Exception 
	 */
	public  String WXOrderQuery(String out_trade_no) throws Exception {
        	UnifiedOrder uo = unifiedOrderUtil.queryOrder(out_trade_no);
        	String orderxml = MyConvertXML.getUnderlineXML(uo,true);
        	String result = HttpClientUtil.doPostJson("https://api.mch.weixin.qq.com/pay/orderquery", orderxml);
        	Map<String, Object> map = MyDom4jUtil.xmlStrToMap(result);
        	JSONObject xml = JSONObject.fromObject(map);
        	return xml.toString();
	}
	/**
	 * 关闭微信订单
	 * @param out_trade_no 商户订单号
	 * @throws Exception 
	 */
	public  String WXOrderClose(String out_trade_no) throws Exception {
        	UnifiedOrder uo = unifiedOrderUtil.queryOrder(out_trade_no);
        	String orderxml = MyConvertXML.getUnderlineXML(uo,true);
        	String result = HttpClientUtil.doPostJson("https://api.mch.weixin.qq.com/pay/closeorder", orderxml);
        	Map<String, Object> map = MyDom4jUtil.xmlStrToMap(result);
        	JSONObject xml = JSONObject.fromObject(map);
        	return xml.toString();
	}
	/**
	 * 微信申请退款
	 * @param out_trade_no 商户订单号
	 * @param total_fee 订单总金额
	 * @param refund_fee 退款金额
	 * @throws Exception 
	 */
	public  String WXRefund(String out_trade_no,Integer total_fee,Integer refund_fee) throws Exception  {
		UnifiedOrder uo = unifiedOrderUtil.refund(out_trade_no, total_fee, refund_fee);
		String orderxml = MyConvertXML.getUnderlineXML(uo,true);
		JSONObject json = weiXinHttpClientUtil.doSSLXmlPostJson("https://api.mch.weixin.qq.com/secapi/pay/refund", orderxml);
		return json.toString();
	}
	/**
	 *查询微信退款订单
	 *@param out_trade_no 商户订单号
	 * @throws Exception 
	 */
	public  String WXRefundQuery(String out_trade_no) throws Exception  {
        	UnifiedOrder uo = unifiedOrderUtil.queryOrder(out_trade_no);
        	String orderxml = MyConvertXML.getUnderlineXML(uo,true);
        	String result = HttpClientUtil.doPostJson("https://api.mch.weixin.qq.com/pay/closeorder", orderxml);
        	Map<String, Object> map = MyDom4jUtil.xmlStrToMap(result);
        	JSONObject xml = JSONObject.fromObject(map);
        	return xml.toString();
	}
}
