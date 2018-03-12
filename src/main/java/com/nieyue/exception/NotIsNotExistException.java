package com.nieyue.exception;


/**
 * {title}不存在异常
 * @author 聂跃
 * @date 2017年8月8日
 */
public class NotIsNotExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;//名称
	
	public NotIsNotExistException(){
		
	}
	public NotIsNotExistException(String title){
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	
}


