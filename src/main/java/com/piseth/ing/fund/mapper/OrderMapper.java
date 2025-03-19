package com.piseth.ing.fund.mapper;

import org.mapstruct.Mapper;

import com.piseth.ing.fund.dto.OrderDTO;
import com.piseth.ing.fund.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	OrderDTO toDto(Order entity);
	
	Order toOrder(OrderDTO dto);
}
