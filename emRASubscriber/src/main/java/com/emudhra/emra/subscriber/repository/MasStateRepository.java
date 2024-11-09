package com.emudhra.emra.subscriber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emudhra.emra.subscriber.entity.master.MasState;





@Repository
public interface MasStateRepository extends JpaRepository <MasState , Long> {
	
	List<MasState>  findBycountryId(int id);

}
