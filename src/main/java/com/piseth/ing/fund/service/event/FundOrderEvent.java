package com.piseth.ing.fund.service.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class FundOrderEvent extends ApplicationEvent{
	private final String message;
	
	public FundOrderEvent(Object source, String message) {
		super(source);
        this.message = message;
	}

}
