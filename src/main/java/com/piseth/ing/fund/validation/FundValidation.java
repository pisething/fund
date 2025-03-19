package com.piseth.ing.fund.validation;

import org.springframework.stereotype.Component;

import com.piseth.ing.fund.entity.Instrument;
import com.piseth.ing.fund.entity.Order;
import com.piseth.ing.fund.entity.rule.Rule;
import com.piseth.ing.fund.enumeration.InstrumentType;
import com.piseth.ing.fund.exception.MaxAmountException;
import com.piseth.ing.fund.exception.MinAmountException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FundValidation {
	
	

	public void validateOrder(Order order, Instrument fund) {
		Rule rule = fund.getRule();
		
		// max amount
		validateMaxAmount(order, rule);
		validateMinAmount(order, rule);
	}
	
	private void validateMaxAmount(Order order, Rule rule) {
		if(order.getAmount() > rule.getMaxAmount()) {
			throw new MaxAmountException(order.getAmount());
		}
	}
	
	private void validateMinAmount(Order order, Rule rule) {
		if(!order.getInstrumentType().equals(InstrumentType.FIXED_INCOME)) {
			log.info("No need to validate min amount for "+ order.getInstrumentType());
			return; 
		}
		if(order.getAmount() < rule.getMinAmount()) {
			throw new MinAmountException(order.getAmount());
		}
	}
}
