package com.emudhra.emra.subscriber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emudhra.emra.subscriber.entity.entity.Customers;
import com.emudhra.emra.subscriber.repository.CreateOrderRepository;

@Service
public class GeneratePasswordService {
	@Autowired
	private CreateOrderRepository customerRepository;
	public Customers getCustomersData(Long userId) {
		Customers customers = customerRepository.findById(userId).get();
		return customers;
		}
	
	public Customers getCustomersByUserName(String userName) {
		Customers customers = customerRepository.findByUserName(userName);
		return customers;
		}
	
	public Customers saveCustomers(Customers customers) {

		return customerRepository.save(customers);
	}

}
