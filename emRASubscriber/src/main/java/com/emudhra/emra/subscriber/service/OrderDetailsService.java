package com.emudhra.emra.subscriber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emudhra.emra.subscriber.entity.entity.OrderDetails;
import com.emudhra.emra.subscriber.repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {
@Autowired
private OrderDetailsRepository orderDetailsRepository;

public OrderDetails SaveOrderDetails(OrderDetails orderDetails) {
	return orderDetailsRepository.save(orderDetails);
}
	
	
}
