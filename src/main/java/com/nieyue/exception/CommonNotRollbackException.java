package com.nieyue.exception;
/**
 * 公共不回滚异常
 * @author 聂跃
 * @date 2018年3月12日
 */
public class CommonNotRollbackException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;//名称
	@Override
	public synchronized Throwable fillInStackTrace() {
		// TODO Auto-generated method stub
		return super.fillInStackTrace();
	}
	public CommonNotRollbackException(){
		
	}
	public CommonNotRollbackException(String title){
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
