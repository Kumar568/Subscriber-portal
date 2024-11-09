package com.emudhra.emra.subscriber.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emudhra.emra.subscriber.entity.entity.EmailContent;






@Repository
public interface EmailContentRepository extends JpaRepository<EmailContent, Long> {
	 EmailContent findByMasEmailTypeSlNo(int masEmailTypeSlNo);

}
