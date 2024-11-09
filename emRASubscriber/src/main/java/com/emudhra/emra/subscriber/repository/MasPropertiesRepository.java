package com.emudhra.emra.subscriber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emudhra.emra.subscriber.entity.master.MasProperties;




public interface MasPropertiesRepository extends JpaRepository<MasProperties, Integer> {
	List<MasProperties> findByIdIn(List<Integer> id);
	
	 List<MasProperties> findAll();
}
