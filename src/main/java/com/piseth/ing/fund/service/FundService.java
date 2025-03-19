package com.piseth.ing.fund.service;

import com.piseth.ing.fund.dto.OrderDTO;

public interface FundService {
	
	OrderDTO saveOrder(OrderDTO orderDTO);
	
	void doBuy(OrderDTO orderDTO);
	
	void doSell(OrderDTO orderDTO);

}
