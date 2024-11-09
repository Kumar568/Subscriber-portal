package com.emudhra.emra.subscriber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.emudhra.emra.subscriber.dto.MapVeritificationChecklistDto;
import com.emudhra.emra.subscriber.entity.entity.MapVeritificationChecklist;



@Mapper
public interface MapVeritificationChecklistMapper {

	
	MapVeritificationChecklistMapper mapVerificationChecklist=Mappers.getMapper(MapVeritificationChecklistMapper.class);
	
	
	MapVeritificationChecklistDto mapVerificationChecklistEntitytoDto(MapVeritificationChecklist checklist);
	
	MapVeritificationChecklist mapVerificationChecklistDtotoEntity(MapVeritificationChecklistDto checklistDto);
	
	List<MapVeritificationChecklistDto> mapVerificationChecklistEntitylistToDto(List<MapVeritificationChecklist> list);
}
