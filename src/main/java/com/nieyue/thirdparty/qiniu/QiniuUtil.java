package com.nieyue.thirdparty.qiniu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.qiniu.util.Auth;

/**
 * 七牛云存储工具
 * @author 聂跃
 * @date 2018年1月10日
 */
@Configuration
public class QiniuUtil {
   @Value("${myPugin.qiniu.accessKey}")
   String accessKey;
   @Value("${myPugin.qiniu.secretKey}")
   String secretKey;
   @Value("${myPugin.qiniu.bucketName}")
   String bucketName;
//   static String accessKey="JiMHrAiq4H6yHKc7iTvz9rCRzw8txedG0caUsw_s";
//   static String secretKey="pwEvywJ8HDoUuX6XOgPQsC_iNXXsmlJa3hdw-4qr";
//   static String bucketName="jihuoma";
//   static String domain="p55v5f6od.bkt.clouddn.com";
   /**
    * 获取uploadToken
    * @return
    */
   public String getQiniuUploadToken(){
	   Auth auth = Auth.create(accessKey, secretKey);
	   String token = auth.uploadToken(bucketName);
	   return token;
   }

   public static void main(String[] args) {
	System.out.println(new QiniuUtil().getQiniuUploadToken());
}
}
