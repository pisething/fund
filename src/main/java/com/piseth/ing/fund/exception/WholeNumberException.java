package com.piseth.ing.fund.exception;

public class WholeNumberException extends InvalidRuleException{

	private static final long serialVersionUID = 3241930840808077048L;
	private static final String MESSAGE = "Invalid digit allowed [%d]";
	
	public WholeNumberException(Integer digitAllowed) {
		super(MESSAGE.formatted(digitAllowed));
	}

}
