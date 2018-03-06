package com.nieyue.thirdparty.yun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * 云短信网短信
 * @author yy
 *
 */
@Configuration
public class YunSms {

    //产品域名,开发者无需替换
    static final String domain = "http://http.yunsms.cn/tx/?";
    @Value("${myPugin.yunSmsUID}")
    String yunSmsUID;
    @Value("${myPugin.yunSmsPWD}")
    String yunSmsPWD;
  	@Value("${myPugin.yunSmsSignName}")
  	String yunSmsSignName;
  	/**
  	 * @param stc  模板码 1用户注册，2修改密码，3修改交易密码
  	 * @param number  验证码
  	 * @return
  	 * @throws ClientException
  	 */
    public  String  sendMsg(String phone,Integer stc,String number) throws IOException{
    			// 发送内容
    			String content = yunSmsSignName;
    			if(stc.equals(1)){
    				content+="，您注册的验证码：";
    			}else if(stc.equals(2)){
    				content+="，您修改密码的验证码：";
    				
    			}else if(stc.equals(3)){
    				content+="，您交易密码的验证码：";
    				
    			}else{//错误
    				return "101";
    				}
    			content+=number;
    			// 创建StringBuffer对象用来操作字符串
    			StringBuffer sb = new StringBuffer(domain);

    			// 向StringBuffer追加用户名
    			sb.append("uid="+yunSmsUID);

    			// 向StringBuffer追加密码（密码采用MD5 32位 小写）
    			sb.append("&pwd="+DigestUtils.md5Hex(yunSmsPWD).toLowerCase());
    			sb.append("&encode=utf8");

    			// 向StringBuffer追加手机号码
    			sb.append("&mobile="+phone);

    			// 向StringBuffer追加消息内容转URL标准码
    			sb.append("&content=" + URLEncoder.encode(content,"utf-8"));
    			// System.out.println(sb.toString());
    			// 创建url对象
    			URL url = new URL(sb.toString());

    			// 打开url连接
    			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    			// 设置url请求方式 ‘get’ 或者 ‘post’
    			connection.setRequestMethod("POST");

    			// 发送
    			BufferedReader in = new BufferedReader(new InputStreamReader(
    					url.openStream()));

    			// 返回发送结果
    			String inputline = in.readLine();

    			// 返回结果为‘100’ 发送成功
    			System.out.println(inputline);
    			return inputline;
     }

}
