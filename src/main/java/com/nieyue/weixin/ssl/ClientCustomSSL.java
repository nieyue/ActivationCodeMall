package com.nieyue.weixin.ssl;

/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.nieyue.util.MyFile;

/**
 * 微信退款证书
 */
@Configuration
public class ClientCustomSSL {
		 //商户ID
		@Value("${myPugin.weixin.weChatPayment.MCH_ID}") 
		 public  String mch_id;
	/**
	 * 设置证书
	 * @return
	 * @throws Exception
	 */
	public  CloseableHttpClient getCloseableHttpClient() throws Exception{
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(ClientCustomSSL.class.getResource("").getPath()+"apiclient_cert.p12"));
       //只能本地访问
        //FileInputStream instream = new FileInputStream("src/com/nieyue/weixin/ssl/apiclient_cert.p12");
        try {
            keyStore.load(instream,mch_id.toCharArray());
        } finally {
            instream.close();
        }
        // Trust own CA and all self-signed certs
        @SuppressWarnings("deprecation")
		SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, mch_id.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        @SuppressWarnings("deprecation")
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        return httpclient;
	}
	
    public final static void main(String[] args) throws Exception {
    	//File file = new File(System.getProperty("user.dir")+"/src/com/nieyue/weixin/ssl/apiclient_cert.p12");
    	//URL path = ClientCustomSSL.class.getClassLoader().getResource("/src/com/nieyue/weixin/ssl/apiclient_cert.p12");
    	URL path = ClientCustomSSL.class.getResource("");
    	new MyFile().imageRW("WebRoot/resources/img/404.jpg", "WebRoot/resources/payQRCode/test.jpg", "png");
    	//File file = new File(path.getPath());
    	System.out.println(path.getPath()+"sdfsd");
    	//System.out.println(file.getAbsolutePath());
    	//System.out.println(file.getCanonicalPath());
    	//System.out.println(file.getPath());
    	System.out.println(new ClientCustomSSL().getCloseableHttpClient());
    }
}
