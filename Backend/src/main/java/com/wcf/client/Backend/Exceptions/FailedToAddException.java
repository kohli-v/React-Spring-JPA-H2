package com.wcf.client.Backend.Exceptions;

public class FailedToAddException extends RuntimeException  {
	public FailedToAddException(String errorMessage) {
	    super(errorMessage);
	}
	
	// super call is implicit
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
