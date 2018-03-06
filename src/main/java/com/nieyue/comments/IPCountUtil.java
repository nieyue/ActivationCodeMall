package com.nieyue.comments;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**
 * ip统计
 * @author yy
 *
 */
public class IPCountUtil {
	static int getIPByDay( ){
		return 0;
	}
	  
	    /** 
	     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	     * 参考文章： http://developer.51cto.com/art/201111/305181.htm 
	     *  
	     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	     *  
	     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	     * 192.168.1.100 
	     *  
	     * 用户真实IP为： 192.168.1.110 
	     *  
	     * @param request 
	     * @return 
	     */  
	    public static String getIpAddr(HttpServletRequest request) {  
	        String ip = request.getHeader("x-forwarded-for");
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }  
	     // 多次反向代理后会有多个IP值，第一个为真实IP。
	    	int index = ip.indexOf(',');
	    	if (index != -1) {
	    	return ip.substring(0, index);
	    	} 
	        return ip;  
	    }  
	/**
	* 获取访问者IP
	* 
	* 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	* 
	* 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	* 如果还不存在则调用Request .getRemoteAddr()。
	* 
	* @param request
	* @return
	*/
	public static String getIpAddr1(HttpServletRequest request){
	String ip = request.getHeader("X-Real-IP");
	if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	return ip;
	}
	ip = request.getHeader("X-Forwarded-For");
	if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	// 多次反向代理后会有多个IP值，第一个为真实IP。
	int index = ip.indexOf(',');
	if (index != -1) {
	return ip.substring(0, index);
	} else {
	return ip;
	}
	} else {
	return request.getRemoteAddr();
	}
	}
	/**
	 * 获取当前网络ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr2(HttpServletRequest request){
			String ipAddress = request.getHeader("x-forwarded-for");
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
					//根据网卡取本机配置的IP
					InetAddress inet=null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress= inet.getHostAddress();
				}
			}
			//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
				if(ipAddress.indexOf(",")>0){
					ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
				}
			}
			return ipAddress; 
	}
	/**
	 * ipv6
	 * @return
	 * @throws IOException
	 */
	public static String getLocalIPv6Address() throws IOException {
	    InetAddress inetAddress = null;
	    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
	    while (networkInterfaces.hasMoreElements()) {
	        Enumeration<InetAddress> inetAds = networkInterfaces.nextElement().getInetAddresses();
	        while (inetAds.hasMoreElements()) {	             
	            inetAddress = inetAds.nextElement(); 
	            //Check if it's ipv6 address and reserved address
	            if (inetAddress instanceof Inet6Address )
	                {
	            	System.out.println(inetAddress);
	                  return inetAddress.toString();
	            }
	        }
	    }
		return null;
	}
}
