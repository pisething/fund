package com.piseth.ing.fund.entity.rule;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixedIncomeRule extends Rule{
	
	private Integer decimalPoint;
	private Integer maxAmount;
	private BigDecimal middlePrice;
	private Integer priceDeviationPercent;

}
