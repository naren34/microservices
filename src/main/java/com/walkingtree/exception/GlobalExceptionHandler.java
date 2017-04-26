package com.walkingtree.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = { Exception.class })
    
    public ApiErrorResponse unknownException(Exception ex, WebRequest req) {
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),ex.getMessage());
    }
}
