package com.emudhra.emra.subscriber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.emudhra.emra.subscriber.dto.StateDto;
import com.emudhra.emra.subscriber.entity.master.MasState;






@Mapper
public interface StatesMapper {

	StatesMapper Mapper = Mappers.getMapper(StatesMapper.class);
	
	List<StateDto> mapMasStateToStateDto(List<MasState> masState);
	
}
