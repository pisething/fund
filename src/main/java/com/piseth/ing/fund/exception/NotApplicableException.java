package com.piseth.ing.fund.exception;

public class NotApplicableException extends RuntimeException{

	private static final long serialVersionUID = 3241930840808077048L;
	private static final String MESSAGE = "Not Applicable Method called";
	
	public NotApplicableException() {
		super(MESSAGE);
	}

}
