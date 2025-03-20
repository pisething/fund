package com.piseth.ing.fund.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.piseth.ing.fund.data.Database;
import com.piseth.ing.fund.dto.OrderDTO;
import com.piseth.ing.fund.entity.Instrument;
import com.piseth.ing.fund.entity.Order;
import com.piseth.ing.fund.entity.rule.FixedIncomeRule;
import com.piseth.ing.fund.entity.rule.FundRule;
import com.piseth.ing.fund.entity.rule.Rule;
import com.piseth.ing.fund.enumeration.InstrumentType;
import com.piseth.ing.fund.enumeration.OrderType;
import com.piseth.ing.fund.mapper.OrderMapper;
import com.piseth.ing.fund.repository.InstrumentRepository;
import com.piseth.ing.fund.service.impl.FundServiceImpl;
import com.piseth.ing.fund.validation.FundValidation;

@ExtendWith(MockitoExtension.class)
public class FundServiceTest {
	
	@Mock
	private OrderMapper orderMapper;
	@Mock
	private InstrumentRepository instrumentRepository;
	@Mock
	private FundValidation fundValidation;
	@Mock
	private ApplicationEventPublisher eventPublisher;
	
	@InjectMocks
	private FundServiceImpl fundService;
	
	private Order order;
	private Instrument mutualFund;
	private Instrument fixedIncomeFund;
	
	@BeforeEach
	void setup() {
		order = Order.builder()
				.instrumentType(InstrumentType.MUTUAL_FUND)
				.fundID("001")
				.price(BigDecimal.valueOf(1000.50))
				.amount(10)
				.orderType(OrderType.SELL)
				.build();
		
		Rule fundRule = new FundRule(3,2,2000,1); // For Mutual Funds and Index Funds
		Rule fixedIncomeRule = new FixedIncomeRule(2,2000, BigDecimal.valueOf(1000.0),5); // For FixedIncome Funds
		
		mutualFund = new Instrument("001","Real Estate Fund",InstrumentType.MUTUAL_FUND, fundRule);
		fixedIncomeFund = new Instrument("002","Fixed Income Fund",InstrumentType.FIXED_INCOME, fixedIncomeRule);
		
	}
	
	@Test
	void test_success() {
		// given
		OrderDTO orderDTO = OrderDTO.builder()
			.instrumentType(InstrumentType.MUTUAL_FUND)
			.fundID("001")
			.price(BigDecimal.valueOf(1000.50))
			.amount(10)
			.orderType(OrderType.SELL)
			.build();
		
		// when
		when(orderMapper.toOrder(orderDTO)).thenReturn(order);
		when(instrumentRepository.findById(anyString())).thenReturn(Optional.ofNullable(mutualFund));
		when(orderMapper.toDto(order)).thenReturn(orderDTO);
		
		OrderDTO savedOrder = fundService.saveOrder(orderDTO);
		// then
		
		verify(orderMapper, times(1)).toDto(order);
		verify(instrumentRepository, times(1)).findById(orderDTO.getFundID());
		verify(fundValidation, times(1)).validateOrder(order, mutualFund);
		verify(orderMapper, times(1)).toOrder(orderDTO);
		
		assertNotNull(savedOrder);
		assertEquals("001", savedOrder.getFundID());
	}

}
