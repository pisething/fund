package com.piseth.ing.fund.exception;

public class MinAmountException extends InvalidRuleException{

	private static final long serialVersionUID = 3241930840808077048L;
	private static final String MESSAGE = "Invalid min amount allowed [%d]";
	
	public MinAmountException(Integer minAmountAllowed) {
		super(MESSAGE.formatted(minAmountAllowed));
	}

}
