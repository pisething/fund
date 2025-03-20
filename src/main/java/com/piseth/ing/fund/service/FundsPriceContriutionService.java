package com.piseth.ing.fund.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.piseth.ing.fund.service.event.FundOrderEvent;

@Component
public class FundsPriceContriutionService {

	@EventListener
    public void handleEvent(FundOrderEvent event) {
        System.out.println("FundsPriceContriutionService Received event: " + event.getMessage());
    }
}
