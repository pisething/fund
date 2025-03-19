package com.piseth.ing.fund.exception;

public class MaxAmountException extends InvalidRuleException{

	private static final long serialVersionUID = 3241930840808077048L;
	private static final String MESSAGE = "Invalid max amount allowed [%d]";
	
	public MaxAmountException(Integer maxAmountAllowed) {
		super(MESSAGE.formatted(maxAmountAllowed));
	}

}
