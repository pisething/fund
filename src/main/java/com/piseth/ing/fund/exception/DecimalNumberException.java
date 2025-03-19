package com.piseth.ing.fund.exception;

public class DecimalNumberException extends InvalidRuleException{

	private static final long serialVersionUID = 3241930840808077048L;
	private static final String MESSAGE = "Invalid decimal point allowed [%d]";
	
	public DecimalNumberException(Integer decimalPointAllowed) {
		super(MESSAGE.formatted(decimalPointAllowed));
	}

}
