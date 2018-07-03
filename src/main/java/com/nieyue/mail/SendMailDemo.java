package com.nieyue.mail;

public class SendMailDemo {
	/**
	 * 发送验证码
	 * @param email 接受人邮箱
	 * @param code   验证码
	 * @param subject   主题
	 * @return  true or false
	 */
	public static boolean sendCodeMail(String email,int code,String subject){
		
		// 设置邮件服务器信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.mxhichina.com");
		//mailInfo.setMailServerPort("25");
		mailInfo.setMailServerPort("465");
		mailInfo.setValidate(true);
		
		// 邮箱用户名
		//mailInfo.setUserName("email@tmall188.xin");
		mailInfo.setUserName("benzhenchayuan@yayao8.com");
		// 邮箱密码
		//mailInfo.setPassword("123456qqQQ");
		mailInfo.setPassword("yayao123+++");
		// 发件人邮箱
		//mailInfo.setFromAddress("email@tmall188.xin");
		mailInfo.setFromAddress("benzhenchayuan@yayao8.com");
		// 收件人邮箱
		mailInfo.setToAddress(email);
		//mailInfo.setToAddress("278076304@qq.com");
		// 邮件标题
		mailInfo.setSubject(subject);
		// 邮件内容
		StringBuffer buffer = new StringBuffer();
		buffer.append("<strong style='font-size:28px;'>"+subject+"</strong><br/><hr/>");
		buffer.append("请在提交请求后的10分钟内，通过下面的验证码激活并确认您的账号:<br/>");
		//buffer.append("<a href='http://localhost:8080/YaYaoXiangXiu/retrieveAccountBack.html'>http://localhost:8080/YaYaoXiangXiu/retrieveAccountBack.html</a><br/>");
		buffer.append("验证码：<span style='font-weight:bold;color:blue;'>"+code+"</span>");
		buffer.append("（该验证码在10分钟有效，10分钟后需要重新获取验证邮件）<br/>如果该验证码无法验证，请重新获取。<br/>如果这不是您的邮件，请忽略此邮件。<br/>这是系统邮件，请勿回复。");
		mailInfo.setContent(buffer.toString());
		//SimpleMailSender sml=new SimpleMailSender();
		// sml.sendTextMail(mailInfo);
		boolean b = SimpleMailSender.sendHtmlMail(mailInfo);
		return b;
	}
	/**
	 * 发送链接
	 * @param email 接收人邮箱 
	 * @param link 链接 
	 * @param subject 主题
	 * @param content 内容
	 */
	public static boolean sendLinkMail(String email,String link,String subject,String content ){
		
		// 设置邮件服务器信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.mxhichina.com");
		//mailInfo.setMailServerPort("25");
		mailInfo.setMailServerPort("465");
		mailInfo.setValidate(true);
		
		// 邮箱用户名
		//mailInfo.setUserName("email@tmall188.xin");
		mailInfo.setUserName("benzhenchayuan@yayao8.com");
		// 邮箱密码
		//mailInfo.setPassword("123456qqQQ");
		mailInfo.setPassword("yayao123+++");
		// 发件人邮箱
		//mailInfo.setFromAddress("email@tmall188.xin");
		mailInfo.setFromAddress("benzhenchayuan@yayao8.com");
		// 收件人邮箱
		mailInfo.setToAddress(email);
		// 邮件标题
		mailInfo.setSubject(subject);
		// 邮件内容
		StringBuffer buffer = new StringBuffer();
		buffer.append("<strong style='font-size:24px;'>激活码商城,"+content+"</strong><br/><hr/>");
		buffer.append("请在提交请求后的1小时内，通过下面的验证码激活并确认您的账号:<br/>");
		buffer.append("点击激活链接完成注册，激活链接在1小时内有效!<a href='"+link+"'>"+link+"</a><br/>");
		buffer.append("（该验证码在1小时有效，1小时需要重新获取验证邮件）<br/>如果无法验证，请重新获取。<br/>如果这不是您的邮件，请忽略此邮件。<br/>这是系统邮件，请勿回复。");
		mailInfo.setContent(buffer.toString());
		//SimpleMailSender sml=new SimpleMailSender();
		// sml.sendTextMail(mailInfo);
		boolean b = SimpleMailSender.sendHtmlMail(mailInfo);
		return b;
	}
	/**
	 * 发送卡密
	 * @param email 接收人邮箱 
	 * @param subject 主题
	 * @param content 内容
	 */
	public static boolean sendCardCipher(String email,String subject,String content ){
		
		// 设置邮件服务器信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.mxhichina.com");
		//mailInfo.setMailServerPort("25");
		mailInfo.setMailServerPort("465");
		mailInfo.setValidate(true);
		// 邮箱用户名
		//mailInfo.setUserName("email@tmall188.xin");
		mailInfo.setUserName("benzhenchayuan@yayao8.com");
		// 邮箱密码
		//mailInfo.setPassword("123456qqQQ");
		mailInfo.setPassword("yayao123+++");
		// 发件人邮箱
		//mailInfo.setFromAddress("email@tmall188.xin");
		mailInfo.setFromAddress("benzhenchayuan@yayao8.com");
		// 收件人邮箱
		mailInfo.setToAddress(email);
		// 邮件标题
		mailInfo.setSubject(subject);
		// 邮件内容
		StringBuffer buffer = new StringBuffer();
		buffer.append("<strong style='font-size:24px;'>激活码商城,"+content+"</strong><br/><hr/>");
		mailInfo.setContent(buffer.toString());
		boolean b = SimpleMailSender.sendHtmlMail(mailInfo);
		return b;
	}
	public static boolean sendSelfMail(MailSenderInfo mailInfo){
		boolean s = SimpleMailSender.sendHtmlMail(mailInfo);
		return s;
	}
	public static void sendSafeMail(  final String email , final int code,final String subject ) throws InterruptedException{
		Thread ss=new Thread(new Runnable() {
			public void run() {
				sendCodeMail(email,code,subject);
			}
		});
		ss.start();
		//ss.join();//优点安全，缺点阻塞
	}
	
	public static void main(String[] args) throws InterruptedException {
		//sendMail("278076304@qq.com",(int) (Math.random()*9000+1000));
		//System.out.println("发送成功");
		// 设置邮件服务器信息
				MailSenderInfo mailInfo = new MailSenderInfo();
				mailInfo.setMailServerHost("smtp.mxhichina.com");
				//mailInfo.setMailServerPort("25");
				mailInfo.setMailServerPort("465");
				mailInfo.setValidate(true);
				
				// 邮箱用户名
				mailInfo.setUserName("benzhenchayuan@yayao8.com");
				// 邮箱密码
				mailInfo.setPassword("yayao123+++");
				// 发件人邮箱
				mailInfo.setFromAddress("benzhenchayuan@yayao8.com");
				// 收件人邮箱
				mailInfo.setToAddress("278076304@qq.com");
				// 邮件标题
				mailInfo.setSubject("激活码商城");
				String content="注册";
				// 邮件内容
				StringBuffer buffer = new StringBuffer();
				buffer.append("<strong style='font-size:24px;'>激活码商城,"+content+"</strong><br/><hr/>");
				buffer.append("点击激活链接完成注册，激活链接在24小时内有效!<a href='http://www.baidu.com'>http://www.baidu.com</a><br/>");
				mailInfo.setContent(buffer.toString());
		      //boolean b = sendSelfMail(mailInfo);
				//boolean b = sendLinkMail("278076304@qq.com", "http://admin.laoyeshuo.cn", "激活码商城", "注册激活");
				boolean b = SimpleMailSender.sendHtmlMail(mailInfo);
				System.out.println(b);
	}
	
} 