package com.piseth.ing.fund.exception;

public class DecimalPointException extends InvalidRuleException{

	private static final long serialVersionUID = 3241930840808077048L;
	private static final String MESSAGE = "Invalid decimal point allowed [%d]";
	
	public DecimalPointException(Integer decimalPointAllowed) {
		super(MESSAGE.formatted(decimalPointAllowed));
	}

}
