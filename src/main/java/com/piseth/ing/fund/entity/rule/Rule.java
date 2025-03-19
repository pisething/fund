package com.piseth.ing.fund.entity.rule;

public abstract class Rule {
	
	public abstract Integer getMaxAmount();
	
	public Integer getMinAmount() {
		throw new RuntimeException("Not Applicable");
	}

}
