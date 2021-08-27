package com.starwars.quasar.application.http.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.starwars.quasar.domain.exceptions.BusinessException;
import com.starwars.quasar.domain.exceptions.LocationException;
import com.starwars.quasar.domain.exceptions.MessageException;

@ControllerAdvice
public class ControllerAdviceConfig {

	@ExceptionHandler(value = { LocationException.class })
	public ResponseEntity<?> handleLocationException(LocationException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { MessageException.class })
	public ResponseEntity<?> handleMessageException(MessageException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { BusinessException.class })
	public ResponseEntity<?> handleBusinessException(BusinessException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

}
