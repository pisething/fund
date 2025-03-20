package com.piseth.ing.fund.entity;

import java.math.BigDecimal;

import com.piseth.ing.fund.enumeration.InstrumentType;
import com.piseth.ing.fund.enumeration.OrderType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
	private InstrumentType instrumentType;
	private String fundID;
	private BigDecimal price;
	private Integer amount;
	private OrderType orderType;
}
