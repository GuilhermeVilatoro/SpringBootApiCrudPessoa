package br.com.elotech.springbootapipessoa.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.elotech.springbootapipessoa.business.exceptions.BusinessException;
import br.com.elotech.springbootapipessoa.services.exceptions.DatabaseException;
import br.com.elotech.springbootapipessoa.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), "Resource not found",
				ex.getMessage(), request.getRequestURI()));
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), "Database error",
				ex.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<StandardError> business(BusinessException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(new StandardError(Instant.now(), status.value(), "Business error",
				ex.getMessage(), request.getRequestURI()));
	}
}
