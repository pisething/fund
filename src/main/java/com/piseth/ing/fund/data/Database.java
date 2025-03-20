package com.piseth.ing.fund.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.piseth.ing.fund.entity.Instrument;
import com.piseth.ing.fund.entity.rule.FixedIncomeRule;
import com.piseth.ing.fund.entity.rule.FundRule;
import com.piseth.ing.fund.entity.rule.Rule;
import com.piseth.ing.fund.enumeration.InstrumentType;

@Component
public class Database {
	private static List<Instrument> instruments = new ArrayList<>();
	
	public Database() {
		// rule base on each instrument
		
		Rule fundRule = new FundRule(3,2,2000,1); // For Mutual Funds and Index Funds
		Rule fixedIncomeRule = new FixedIncomeRule(2,2000, BigDecimal.valueOf(1000.0),5); // For FixedIncome Funds
		
		Instrument mutualFund = new Instrument("001","Real Estate Fund",InstrumentType.MUTUAL_FUND, fundRule);
		Instrument fixedIncomeFund = new Instrument("002","Fixed Income Fund",InstrumentType.FIXED_INCOME, fixedIncomeRule);
		
		instruments.add(mutualFund);
		instruments.add(fixedIncomeFund);
	}
	
	public List<Instrument> getInstruments(){
		return instruments;
	}
	
	
}
