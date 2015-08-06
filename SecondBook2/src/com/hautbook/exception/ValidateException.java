package com.hautbook.exception;
/**
 * 
 * @author caiyao 
 *
 * @function 校验出现的异常
 */
public class ValidateException extends Throwable {

	private static final long serialVersionUID = 1L;
	public String validateMess ;
	public ValidateException(String message){
		this.validateMess = message ;
	}
}
