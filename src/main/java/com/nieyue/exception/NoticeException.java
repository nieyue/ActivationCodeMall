package com.nieyue.exception;


/**
 * 通知异常
 * @author 聂跃
 * @date 2017年8月8日
 */
public class NoticeException extends CommonRollbackException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoticeException(){
	}
	public NoticeException(String title){
		this.setTitle(title);
	}

}


