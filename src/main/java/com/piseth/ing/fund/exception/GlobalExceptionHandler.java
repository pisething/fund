package com.piseth.ing.fund.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidRuleException.class)
    public ProblemDetail handleInvalidRuleException(InvalidRuleException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errorCode", "INVALID_INPUT");
        return problemDetail;
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleInvalidRuleException(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errorCode", "NOT_FOUND");
        return problemDetail;
    }
	
	@ExceptionHandler(NotApplicableException.class)
    public ProblemDetail handleInvalidRuleException(NotApplicableException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errorCode", "INVALID_METHOD_CALLED");
        return problemDetail;
    }

}
