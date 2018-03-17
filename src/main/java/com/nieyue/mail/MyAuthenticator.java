package com.nieyue.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 邮箱用户名和密码认证器
 * @author yy
 *
 */
public class MyAuthenticator extends Authenticator{
	 private String userName = null;

	 private String password = null;

	 public MyAuthenticator() {
	 }

	 public MyAuthenticator(String username, String password) {
	  this.userName = username;
	  this.password = password;
	 }

	 protected PasswordAuthentication getPasswordAuthentication() {
	  return new PasswordAuthentication(userName, password);
	 } 
}
