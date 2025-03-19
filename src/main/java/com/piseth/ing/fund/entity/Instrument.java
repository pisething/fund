package com.piseth.ing.fund.entity;

import com.piseth.ing.fund.entity.rule.Rule;
import com.piseth.ing.fund.enumeration.InstrumentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instrument {
	private String id;
	private String name;
	private InstrumentType instrumentType;
	private Rule rule;
}
