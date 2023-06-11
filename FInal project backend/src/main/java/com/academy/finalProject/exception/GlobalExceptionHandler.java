package com.academy.finalProject.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import com.academy.finalProject.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleException(DataIntegrityViolationException exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getMostSpecificCause().toString());
		ErrorResponse error = new ErrorResponse("Duplicate Value", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Web token expired", details);
		return new ResponseEntity<>(error, HttpStatus.GATEWAY_TIMEOUT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(HttpServletRequest request, HttpServletResponse response,Exception exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getMessage());
		System.out.println(exception);
		ErrorResponse error = new ErrorResponse(exception.getMessage(), details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<Object> handleServletException(IOException exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getMessage());
		System.out.println(exception);
		ErrorResponse error = new ErrorResponse(exception.getMessage(), details);
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(exception.getMessage(), details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception){
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(exception.getLocalizedMessage(),details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(WrongCourseStatusException.class)
	public ResponseEntity<Object> handleWrongCourseStatusException(WrongCourseStatusException exception){
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(exception.getLocalizedMessage(),details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TimeDifferenceException.class)
	public ResponseEntity<Object> handleTimeDifferenceException(TimeDifferenceException exception){
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse(exception.getLocalizedMessage(),details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
