package com.piseth.ing.fund.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.piseth.ing.fund.entity.Instrument;
import com.piseth.ing.fund.entity.Order;
import com.piseth.ing.fund.entity.rule.FixedIncomeRule;
import com.piseth.ing.fund.entity.rule.FundRule;
import com.piseth.ing.fund.enumeration.InstrumentType;
import com.piseth.ing.fund.enumeration.OrderType;
import com.piseth.ing.fund.exception.DecimalPointException;
import com.piseth.ing.fund.exception.MaxAmountException;
import com.piseth.ing.fund.exception.MinAmountException;
import com.piseth.ing.fund.exception.PriceDeviationException;
import com.piseth.ing.fund.exception.WholeNumberException;

@ExtendWith(MockitoExtension.class)
public class FundValidationTest {

	@InjectMocks
	private FundValidation fundValidation;
	
	private Order order;
	private FundRule fundRule;
	private FixedIncomeRule fixedIncomeRule;
	private Instrument mutualFund;
	private Instrument fixedIncomeFund;
	
	@BeforeEach
    public void setUp() {
        
        order = Order.builder()
				.instrumentType(InstrumentType.MUTUAL_FUND)
				.fundID("001")
				.price(BigDecimal.valueOf(1000.50))
				.amount(0)
				.orderType(OrderType.SELL)
				.build();
        
        fundRule = new FundRule(3,2,2000,1); // For Mutual Funds and Index Funds
		fixedIncomeRule = new FixedIncomeRule(2,2000, BigDecimal.valueOf(100.0),5); // For FixedIncome Funds
		
		mutualFund = new Instrument("001","Real Estate Fund",InstrumentType.MUTUAL_FUND, fundRule);
		fixedIncomeFund = new Instrument("002","Fixed Income Fund",InstrumentType.FIXED_INCOME, fixedIncomeRule);
        
    }
	
	@Test
	void testValidateOrder_MutualFund_Success() {
		// given
		order.setAmount(1);
		order.setPrice(BigDecimal.valueOf(99));
		// then
		assertDoesNotThrow(() -> fundValidation.validateOrder(order, mutualFund));
		
	}
	
	@Test
	void testValidateOrder_MutualFund_MinAmount() {
		// given
		
		// when
		MinAmountException exception = assertThrows(MinAmountException.class, () -> fundValidation.validateOrder(order, mutualFund));
		// then
		assertEquals("Invalid min amount allowed [0]", exception.getMessage());
	}
	
	@Test
	void testValidateOrder_MutualFund_WholeNumber() {
		// given
		order.setAmount(1000); // 4 digit
		// when
		WholeNumberException exception = assertThrows(WholeNumberException.class, () -> fundValidation.validateOrder(order, mutualFund));
		// then
		assertEquals("Invalid digit allowed [3]", exception.getMessage());
	}
	
	@Test
	void testValidateOrder_MutualFund_MaxAmount() {
		// given
		order.setAmount(2001); // Max amount 2000  
		fundRule.setWholeNumber(5);
		// when
		MaxAmountException exception = assertThrows(MaxAmountException.class, () -> fundValidation.validateOrder(order, mutualFund));
		// then
		assertEquals("Invalid max amount allowed [2000]", exception.getMessage());
	}
	
	@Test
	void testValidateOrder_MutualFund_Fractional() {
		// given
		order.setPrice(BigDecimal.valueOf(1900.456)); // 3 fractional 
		order.setAmount(30);
		fundRule.setWholeNumber(5);
		// when
		DecimalPointException exception = assertThrows(DecimalPointException.class, () -> fundValidation.validateOrder(order, mutualFund));
		// then
		assertEquals("Invalid decimal point allowed [2]", exception.getMessage());
	}
	
	@Test
	void testValidateOrder_FixedIncomeFund_Above_MiddlePrice() {
		// given
		order.setPrice(BigDecimal.valueOf(106)); // 105 is ok, but 106 is over middle price 
		order.setInstrumentType(InstrumentType.FIXED_INCOME);
		// when
		PriceDeviationException exception = assertThrows(PriceDeviationException.class, () -> fundValidation.validateOrder(order, fixedIncomeFund));
		// then
		assertEquals("Invalid price deviation allowed [5]", exception.getMessage());
	}
	
	@Test
	void testValidateOrder_FixedIncomeFund_Below_MiddlePrice() {
		// given
		order.setPrice(BigDecimal.valueOf(94)); // 95 is ok, but 94 is below middle price 
		order.setInstrumentType(InstrumentType.FIXED_INCOME);
		// when
		PriceDeviationException exception = assertThrows(PriceDeviationException.class, () -> fundValidation.validateOrder(order, fixedIncomeFund));
		// then
		assertEquals("Invalid price deviation allowed [5]", exception.getMessage());
	}
	
	@Test
	void testValidateOrder_FixedIncomeFund_Below_MiddlePrice_But_OK() {
		// given
		order.setPrice(BigDecimal.valueOf(96)); // 96 is ok because middle price is 100
		order.setInstrumentType(InstrumentType.FIXED_INCOME);
		// then
		assertDoesNotThrow(() -> fundValidation.validateOrder(order, fixedIncomeFund));
	}
	
	@Test
	void testValidateOrder_FixedIncomeFund_Above_MiddlePrice_But_OK() {
		// given
		order.setPrice(BigDecimal.valueOf(104)); // 104 is ok because middle price is 100
		order.setInstrumentType(InstrumentType.FIXED_INCOME);
		// then
		assertDoesNotThrow(() -> fundValidation.validateOrder(order, fixedIncomeFund));
	}
}
