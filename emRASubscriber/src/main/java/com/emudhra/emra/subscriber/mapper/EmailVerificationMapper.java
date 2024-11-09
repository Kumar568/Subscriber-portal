package com.emudhra.emra.subscriber.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.emudhra.emra.subscriber.dto.EmailVerificationDto;

import com.emudhra.emra.subscriber.entity.entity.EmailVerification;

@Mapper
public interface EmailVerificationMapper {

	EmailVerificationMapper mapper = Mappers.getMapper(EmailVerificationMapper.class);
	
	EmailVerificationDto mapEntityToEmailVerificationDto(EmailVerification emailVerification);
	
}
