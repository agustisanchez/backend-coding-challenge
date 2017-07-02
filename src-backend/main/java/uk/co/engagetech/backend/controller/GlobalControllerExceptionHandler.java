package uk.co.engagetech.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exc, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.error("Exception caught in controller layer.", exc);
		return ResponseEntity.status(status).body(new ErrorResponse("server.error"));
	}

}