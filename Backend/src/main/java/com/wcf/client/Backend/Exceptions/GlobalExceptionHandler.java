package com.wcf.client.Backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	// when update fails - enable / disable
	@ExceptionHandler({FailedToUpdateException.class})
	public final ResponseEntity<String> handleFailedToUpdateException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	// when deletion of feature fails
	@ExceptionHandler({FailedToDeleteException.class})
	public final ResponseEntity<String> handleFailedToDeleteException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// when adding new feature fails
	@ExceptionHandler({FailedToAddException.class})
	public final ResponseEntity<String> handleFailedToAddException(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
