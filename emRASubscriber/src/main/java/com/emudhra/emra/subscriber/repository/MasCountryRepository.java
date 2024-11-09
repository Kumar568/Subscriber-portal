package com.emudhra.emra.subscriber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emudhra.emra.subscriber.entity.master.MasCountry;







@Repository
public interface MasCountryRepository extends JpaRepository <MasCountry, Integer> {
	
	List<MasCountry> findAll();


}
