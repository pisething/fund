package com.piseth.ing.fund.entity;

import java.math.BigDecimal;

import com.piseth.ing.fund.enumeration.InstrumentType;

import lombok.Data;

@Data
public class Order {
	private InstrumentType instrumentType;
	private String fundID;
	private BigDecimal price;
	private Integer amount;
}
