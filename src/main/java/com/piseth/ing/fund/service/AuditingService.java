package com.piseth.ing.fund.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.piseth.ing.fund.service.event.FundOrderEvent;

@Component
public class AuditingService {

	@EventListener
    public void handleEvent(FundOrderEvent event) {
        System.out.println("AuditingService Received event: " + event.getMessage());
    }
}
