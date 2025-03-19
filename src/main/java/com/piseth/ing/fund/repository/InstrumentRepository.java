package com.piseth.ing.fund.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.piseth.ing.fund.data.Database;
import com.piseth.ing.fund.entity.Instrument;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InstrumentRepository {

	private final Database database;
	
	// Find Fund by Id
	public Optional<Instrument> findById(String fundId) {
		return database.getInstruments()
			.stream()
			.filter(ins -> ins.getId().equals(fundId))
			.findFirst();
	}
	
			
}
