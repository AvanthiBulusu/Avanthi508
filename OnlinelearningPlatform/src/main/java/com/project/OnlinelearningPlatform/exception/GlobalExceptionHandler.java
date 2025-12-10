package com.project.onlinelearningplatform.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.onlinelearningplatform.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ErrorResponseDto handleUserAlreadyExistsException(UserAlreadyExistsException caeException,
			WebRequest webRequest) {
		return new ErrorResponseDto(webRequest.getDescription(true), HttpStatus.BAD_REQUEST, caeException.getMessage(),
				LocalDateTime.now());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ErrorResponseDto userNotFoundException(UserNotFoundException caeException, WebRequest webRequest) {
		return new ErrorResponseDto(webRequest.getDescription(true), HttpStatus.NOT_FOUND, caeException.getMessage(),
				LocalDateTime.now());
	}

	@ExceptionHandler(Exception.class)
	public ErrorResponseDto hanleGlobalException(Exception ex, WebRequest webRequest) {
		return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
				LocalDateTime.now());
	}
}
