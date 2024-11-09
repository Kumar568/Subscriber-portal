package com.emudhra.emra.subscriber.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.emudhra.emra.subscriber.entity.entity.FieldGroup;



public interface FieldGroupRepository extends JpaRepository<FieldGroup, Long> {
	

//List<FieldGroup findByIsActive(int isActive);
}
