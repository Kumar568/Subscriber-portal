package com.emudhra.emra.subscriber.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emudhra.emra.subscriber.entity.entity.Application;
import com.emudhra.emra.subscriber.entity.entity.OrderDetails;

public interface ApplicationRepository  extends JpaRepository<Application, Long>{
	 Optional<Application> findTopByOrderByApplicationNumberDesc();
	 
	 //Application findByOrderId(Long orderId);
	 Application findByOrderDetails(OrderDetails orderDetails);
	//
		Application findByApplicationNumber(Long applicationNumber);
}
