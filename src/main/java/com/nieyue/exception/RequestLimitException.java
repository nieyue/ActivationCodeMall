package com.nieyue.exception;

public class RequestLimitException extends CommonRollbackException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RequestLimitException(){
	}
	public RequestLimitException(String title){
		this.setTitle(title);
	}
}
