package com.piseth.ing.fund.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import com.piseth.ing.fund.dto.OrderDTO;
import com.piseth.ing.fund.entity.Instrument;
import com.piseth.ing.fund.entity.Order;
import com.piseth.ing.fund.enumeration.InstrumentType;
import com.piseth.ing.fund.enumeration.OrderType;
import com.piseth.ing.fund.exception.ResourceNotFoundException;
import com.piseth.ing.fund.mapper.OrderMapper;
import com.piseth.ing.fund.repository.InstrumentRepository;
import com.piseth.ing.fund.service.event.FundOrderEvent;
import com.piseth.ing.fund.validation.FundValidation;

@ExtendWith(MockitoExtension.class)
public class FundServiceImplTest {
	@Mock private OrderMapper orderMapper;
    @Mock private InstrumentRepository instrumentRepository;
    @Mock private FundValidation fundValidation;
    @Mock private ApplicationEventPublisher eventPublisher;
    @InjectMocks private FundServiceImpl fundService;

    private OrderDTO orderDTO;
    private Order order;
    private Instrument fund;

    @BeforeEach
    public void setUp() {
        
        orderDTO = OrderDTO.builder()
    			.instrumentType(InstrumentType.MUTUAL_FUND)
    			.fundID("001")
    			.price(BigDecimal.valueOf(1000.50))
    			.amount(10)
    			.orderType(OrderType.SELL)
    			.build();
        
        order = Order.builder()
				.instrumentType(InstrumentType.MUTUAL_FUND)
				.fundID("001")
				.price(BigDecimal.valueOf(1000.50))
				.amount(10)
				.orderType(OrderType.SELL)
				.build();
        
        fund = new Instrument(); 
    }

    @Test
    public void testSaveOrder_ValidOrder() {
        // Setup mock behavior
        when(orderMapper.toOrder(orderDTO)).thenReturn(order);
        when(instrumentRepository.findById(order.getFundID())).thenReturn(Optional.of(fund));
        when(orderMapper.toDto(order)).thenReturn(orderDTO);

        // Call the method
        OrderDTO result = fundService.saveOrder(orderDTO);

        // Verify interactions
        verify(fundValidation).validateOrder(order, fund);
        verify(eventPublisher).publishEvent(any(FundOrderEvent.class));

        // Assert the result
        assertNotNull(result);
        verify(orderMapper).toDto(order);
    }

    @Test
    public void testSaveOrder_FundNotFound() {
        // Setup mock behavior
        when(orderMapper.toOrder(orderDTO)).thenReturn(order);
        when(instrumentRepository.findById(order.getFundID())).thenReturn(Optional.empty());

        // Assert exception is thrown
        assertThrows(ResourceNotFoundException.class, () -> fundService.saveOrder(orderDTO));
    }
    
}
