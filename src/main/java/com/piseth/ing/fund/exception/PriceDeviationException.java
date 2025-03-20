package com.piseth.ing.fund.exception;

public class PriceDeviationException extends InvalidRuleException{

	private static final long serialVersionUID = 3241930840808077048L;
	private static final String MESSAGE = "Invalid price deviation allowed [%d]";
	
	public PriceDeviationException(Integer priceDeviationAllowed) {
		super(MESSAGE.formatted(priceDeviationAllowed));
	}

}
