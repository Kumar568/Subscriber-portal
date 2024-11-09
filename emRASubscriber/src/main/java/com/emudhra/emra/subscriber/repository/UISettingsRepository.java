package com.emudhra.emra.subscriber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emudhra.emra.subscriber.entity.entity.UISettings;



@Repository
public interface UISettingsRepository extends JpaRepository<UISettings, Integer>{

UISettings findById(int id);
	
}
