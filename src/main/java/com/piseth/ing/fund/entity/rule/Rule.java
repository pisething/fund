package com.piseth.ing.fund.entity.rule;

import com.piseth.ing.fund.exception.NotApplicableException;

public abstract class Rule {
	
	public abstract Integer getMaxAmount();
	
	public Integer getMinAmount() {
		throw new NotApplicableException();
	}
	
	public Integer getWholeNumber() {
		throw new NotApplicableException();
	}
	

}
