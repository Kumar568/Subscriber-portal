package com.emudhra.emra.subscriber.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emudhra.emra.subscriber.entity.entity.Customers;





@Repository
public interface CreateOrderRepository extends JpaRepository<Customers, Long> {

	Customers findByUserName(String userName);
	Customers findByEmailId(String emailId);
    Page<Customers> findByIsActiveAndCreatedBy(byte isActive, Long createdBy, Pageable pageable);
		// TODO Auto-generated method stub
		
	

}
