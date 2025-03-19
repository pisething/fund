package com.piseth.ing.fund.service.impl;

import org.springframework.stereotype.Service;

import com.piseth.ing.fund.dto.OrderDTO;
import com.piseth.ing.fund.entity.Order;
import com.piseth.ing.fund.exception.ResourceNotFoundException;
import com.piseth.ing.fund.mapper.OrderMapper;
import com.piseth.ing.fund.repository.InstrumentRepository;
import com.piseth.ing.fund.service.FundService;
import com.piseth.ing.fund.validation.FundValidation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FundServiceImpl implements FundService{
	private final OrderMapper orderMapper;
	private final InstrumentRepository instrumentRepository;
	private final FundValidation fundValidation;
	
	@Override
	public OrderDTO saveOrder(OrderDTO orderDTO) {
		Order order = orderMapper.toOrder(orderDTO);
		instrumentRepository.findById(order.getFundID())
			.ifPresentOrElse(fund ->{
				fundValidation.validateOrder(order, fund);
			}, () -> {throw new ResourceNotFoundException("Funds",order.getFundID());});
		// Validation
		
		
		switch (orderDTO.getOrderType()){
		case BUY : doBuy(orderDTO);
		case SELL : doSell(orderDTO);
		}
		
		return orderMapper.toDto(order);
	}

	@Override
	public void doBuy(OrderDTO orderDTO) {
		// Buy Logic
		
	}

	@Override
	public void doSell(OrderDTO orderDTO) {
		// Sell Logic
		
	}

}
