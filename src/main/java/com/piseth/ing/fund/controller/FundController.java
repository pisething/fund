package com.piseth.ing.fund.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.ing.fund.dto.OrderDTO;
import com.piseth.ing.fund.service.FundService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FundController {
	
	private final FundService fundService;
	
	@PostMapping("orders")
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO orderDTO){
		OrderDTO orderResponse = fundService.saveOrder(orderDTO);
		return ResponseEntity.ok(orderResponse);
	}

}
