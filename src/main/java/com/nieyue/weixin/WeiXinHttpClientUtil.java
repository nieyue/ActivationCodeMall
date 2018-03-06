package com.nieyue.weixin;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.nieyue.util.MyDom4jUtil;
import com.nieyue.weixin.ssl.ClientCustomSSL;

import net.sf.json.JSONObject;
/**
 * 微信带证书的httpClient
 * @author 聂跃
 * @date 2017年9月26日
 */
@Component
public class WeiXinHttpClientUtil {
	@Resource
	ClientCustomSSL clientCustomSSL;
	/**
	   * 带证书的post请求json
	   * @param url
	   * @param json
	   * @return
	 * @throws Exception 
	   */
	  
	  public  JSONObject doSSLPostJson(String url,String json) throws Exception{
		  CloseableHttpClient client = clientCustomSSL.getCloseableHttpClient();
	    HttpPost post = new HttpPost(url);
	    JSONObject response = null;
	    try {
	      StringEntity s = new StringEntity(json.toString(),"utf-8");
	      post.setEntity(s);
	      CloseableHttpResponse res = client.execute(post);
	      if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	        String result = EntityUtils.toString(res.getEntity(),"utf-8");// 返回json格式：
	        response = JSONObject.fromObject(result);
	      }
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	    return response;
	  }
	  /**
	   * 带证书的post请求json
	   * @param url
	   * @param xml
	   * @return
	 * @throws Exception 
	   */
	  
	  public  JSONObject doSSLXmlPostJson(String url,String xml) throws Exception{
		  CloseableHttpClient client = clientCustomSSL.getCloseableHttpClient();
	    HttpPost post = new HttpPost(url);
	    JSONObject response = null;
	    try {
	      StringEntity s = new StringEntity(xml);
	      post.setEntity(s);
	      CloseableHttpResponse res = client.execute(post);
	      if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	        String result = EntityUtils.toString(res.getEntity() ,"utf-8");// 返回json格式：
	        Map<String, Object> map = MyDom4jUtil.xmlStrToMap(result);
	        response=JSONObject.fromObject(map);
	        return response;
	      }
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	    return response;
	  }
	  /**
	   * 带证书的post 获取xml
	   * @param url
	   * @param xml
	   * @return
	   * @throws Exception
	   */
	  public  String doSSLPostXml(String url,String xml) throws Exception{
		  CloseableHttpClient client = clientCustomSSL.getCloseableHttpClient();
		  HttpPost post = new HttpPost(url);
		  String response = null;
		  try {
			  StringEntity s = new StringEntity(xml,"utf-8");
			  post.setEntity(s);
			  System.out.println(s);
			  CloseableHttpResponse res = client.execute(post);
			  if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				  response = EntityUtils.toString(res.getEntity(),"utf-8");// 返回xml格式：
				  //response = JSONObject.fromObject(result);
			  }
		  } catch (Exception e) {
			  throw new RuntimeException(e);
		  }
		  return response;
	  }
}
