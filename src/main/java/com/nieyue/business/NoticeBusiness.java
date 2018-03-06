package com.nieyue.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.nieyue.bean.Notice;
/**
 * 通知业务
 * @author 聂跃
 * @date 2017年8月19日
 */
@Configuration
public class NoticeBusiness {
	@Value("${myPugin.noticeXitongTitle}")
	String noticeXitongTitle;
	@Value("${myPugin.noticeXitongImgUrl}")
	String noticeXitongImgUrl;
	
	@Value("${myPugin.noticeTuangouTitle}")
	String noticeTuangouTitle;
	@Value("${myPugin.noticeTuangouImgUrl}")
	String noticeTuangouImgUrl;
	
	@Value("${myPugin.noticeBuzuTitle}")
	String noticeBuzuTitle;
	@Value("${myPugin.noticeBuzuImgUrl}")
	String noticeBuzuImgUrl;
	
	@Value("${myPugin.noticeDaozhangTitle}")
	String noticeDaozhangTitle;
	@Value("${myPugin.noticeDaozhangImgUrl}")
	String noticeDaozhangImgUrl;
	
	@Value("${myPugin.noticeShenqingTitle}")
	String noticeShenqingTitle;
	@Value("${myPugin.noticeShenqingImgUrl}")
	String noticeShenqingImgUrl;
	/**
	 * 获取支付方式
	 * @return
	 */
	public String getMethod(Integer method){
		String m="";
		if(method==1){
			m="支付宝";
		}else if(method==2){
			m="微信";
		}else if(method==3){
			m="ios内购";
		}
		return m;
	}
	/**
	 *  系统通知
	 *  content 内容
	 */
	public Notice getNoticeByXitong(
			String content){
			Notice notice=new Notice();
			notice.setTitle(noticeXitongTitle);
			notice.setImgAddress(noticeXitongImgUrl);
			notice.setStatus(0);//未读
			notice.setContent(content);
	return notice;
	}
	/**
	 *  团购通知
	 *  accountId 申请人的账户id
	 *  money 金额
	 */
	public Notice getNoticeByTuangou(
			Integer accountId,
			Double money){
		Notice notice=new Notice();
		notice.setTitle(noticeTuangouTitle);
		notice.setAccountId(accountId);
		notice.setImgAddress(noticeTuangouImgUrl);
		notice.setStatus(0);//未读
		notice.setContent("您的"+money+"元团购申请已成功，详情请前往您的团购中查看。");
		return notice;
	}
	/**
	 *  提现到账通知
	 *  accountId 账户id
	 *  method  获取支付方式
	 *  money 金额
	 */
	public Notice getNoticeByDaozhang(
			Integer accountId,
			Integer method,
			Double money){
		//提现到账通知
		Notice notice=new Notice();
		notice.setTitle(noticeDaozhangTitle);
		notice.setAccountId(accountId);
		notice.setImgAddress(noticeDaozhangImgUrl);
		notice.setStatus(0);//未读
		notice.setContent("您申请的"+money+"元提现已到账，请前往您的"+getMethod(method)+"账号进行查看。");
		return notice;
	}
	/**
	 *  团购卡余额不足
	 *  accountId 账户id
	 *  number  数量
	 */
	public Notice getNoticeByBuzu(
			Integer accountId,
			Integer number){
		Notice notice=new Notice();
		notice.setTitle(noticeBuzuTitle);
		notice.setAccountId(accountId);
		notice.setImgAddress(noticeBuzuImgUrl);
		notice.setStatus(0);//未读
		notice.setContent("您的团购卡余额不足"+number+"张，为了不影响您下级的正常升级，请前往升级。");
		return notice;
	}
	/**
	 *  团购申请
	 *  accountId 接受人的账户id
	 *  realname  申请人的真实姓名
	 *  money 金额
	 */
	public Notice getNoticeByShenqing(
			Integer accountId,
			String realname,
			Double money){
		Notice notice=new Notice();
		notice.setTitle(noticeShenqingTitle);
		notice.setAccountId(accountId);
		notice.setImgAddress(noticeShenqingImgUrl);
		notice.setStatus(0);//未读
		notice.setContent("您新收到一个来自"+realname+"的"+money+"元团购申请，请及时处理。");
		return notice;
	}
}
