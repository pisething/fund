package com.piseth.ing.fund.validation;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.piseth.ing.fund.entity.Instrument;
import com.piseth.ing.fund.entity.Order;
import com.piseth.ing.fund.entity.rule.Rule;
import com.piseth.ing.fund.enumeration.InstrumentType;
import com.piseth.ing.fund.exception.DecimalPointException;
import com.piseth.ing.fund.exception.MaxAmountException;
import com.piseth.ing.fund.exception.MinAmountException;
import com.piseth.ing.fund.exception.PriceDeviationException;
import com.piseth.ing.fund.exception.WholeNumberException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FundValidation {
	
	

	public void validateOrder(Order order, Instrument fund) {
		Rule rule = fund.getRule();
		
		if(order.getInstrumentType().equals(InstrumentType.FIXED_INCOME)) {
			log.info("Validate for Fixed Income Fund");
			
			validateMiddlePrice(order, rule);
		}else {
			log.info("Validate for Non Fixed Income Fund");
			validateMinAmount(order, rule);
			validateWholeNumber(order, rule);
		}
		
		// For common validations
		validateMaxAmount(order, rule);
		validateDigitNumber(order, rule);
		
	}
	
	private void validateMaxAmount(Order order, Rule rule) {
		if(order.getAmount() > rule.getMaxAmount()) {
			throw new MaxAmountException(order.getAmount());
		}
	}
	
	private void validateMinAmount(Order order, Rule rule) {
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
	
	private void validateDigitNumber(Order order, Rule rule) {
		int fractionalDigit = order.getPrice().scale();
		
		if(fractionalDigit > rule.getDecimalPoint()) {
			throw new DecimalPointException(rule.getDecimalPoint());
		}
	}
	
	private void validateMiddlePrice(Order order, Rule rule) {
		BigDecimal middlePrice = rule.getMiddlePrice(); // 100
		
		BigDecimal deviationPrice = middlePrice.multiply(BigDecimal.valueOf(rule.getPriceDeviationPercent() / 100));
		
		BigDecimal sellPrice = middlePrice.add(deviationPrice);
		BigDecimal buyPrice = middlePrice.subtract(deviationPrice);
		
		if(order.getPrice().compareTo(sellPrice) > 0 || 
				order.getPrice().compareTo(buyPrice) < 0) {
			throw new PriceDeviationException(rule.getPriceDeviationPercent());
		}		
	}
}
