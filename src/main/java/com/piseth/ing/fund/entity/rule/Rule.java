package com.piseth.ing.fund.entity.rule;

import java.math.BigDecimal;

import com.piseth.ing.fund.exception.NotApplicableException;

public abstract class Rule {
	
	public abstract Integer getMaxAmount(); // Common rule
	
	public abstract Integer getDecimalPoint(); // Common rule
	
	public Integer getMinAmount() {
		throw new NotApplicableException();
	}
	
	public Integer getWholeNumber() {
		throw new NotApplicableException();
	}
	
	public BigDecimal getMiddlePrice() {
		throw new NotApplicableException();
	}
	
	public Integer getPriceDeviationPercent() {
		throw new NotApplicableException();
	}
	
	
	
	

}
