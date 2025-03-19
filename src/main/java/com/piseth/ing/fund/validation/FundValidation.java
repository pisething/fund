package com.piseth.ing.fund.validation;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.piseth.ing.fund.entity.Instrument;
import com.piseth.ing.fund.entity.Order;
import com.piseth.ing.fund.entity.rule.Rule;
import com.piseth.ing.fund.enumeration.InstrumentType;
import com.piseth.ing.fund.exception.MaxAmountException;
import com.piseth.ing.fund.exception.MinAmountException;
import com.piseth.ing.fund.exception.WholeNumberException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FundValidation {
	
	

	public void validateOrder(Order order, Instrument fund) {
		Rule rule = fund.getRule();
		
		// max amount
		validateMaxAmount(order, rule);
		validateMinAmount(order, rule);
		validateWholeNumber(order, rule);
	}
	
	private void validateMaxAmount(Order order, Rule rule) {
		if(order.getAmount() > rule.getMaxAmount()) {
			throw new MaxAmountException(order.getAmount());
		}
	}
	
	private void validateMinAmount(Order order, Rule rule) {
		if(order.getInstrumentType().equals(InstrumentType.FIXED_INCOME)) {
			log.info("No need to validate min amount for "+ order.getInstrumentType());
			return; 
		}
		if(order.getAmount() < rule.getMinAmount()) {
			throw new MinAmountException(order.getAmount());
		}
	}
	
	private void validateWholeNumber(Order order, Rule rule) {
		double price = Math.pow(10, rule.getWholeNumber()) -1; 
		BigDecimal allowedPrice = BigDecimal.valueOf(price);
		if(order.getPrice().compareTo(allowedPrice) > 0) {
			throw new WholeNumberException(rule.getWholeNumber());
		}
	}
}
