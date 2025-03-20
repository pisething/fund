package com.piseth.ing.fund.entity;

import java.math.BigDecimal;

import com.piseth.ing.fund.enumeration.InstrumentType;
import com.piseth.ing.fund.enumeration.OrderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
	private InstrumentType instrumentType;
	private String fundID;
	private BigDecimal price;
	private Integer amount;
	private OrderType orderType;
}
