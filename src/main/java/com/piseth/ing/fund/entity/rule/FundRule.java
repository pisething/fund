package com.piseth.ing.fund.entity.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundRule extends Rule{
	
	private Integer wholeNumber;
	private Integer decimalPoint;
	private Integer maxAmount;
	private Integer minAmount;

}
