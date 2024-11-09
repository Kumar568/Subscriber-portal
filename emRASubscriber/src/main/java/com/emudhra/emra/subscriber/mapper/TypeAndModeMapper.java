package com.emudhra.emra.subscriber.mapper;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import com.emudhra.emra.subscriber.dto.TypeAndMode;
import com.emudhra.emra.subscriber.entity.entity.MapVeritificationChecklist;


@Mapper
public interface TypeAndModeMapper {

	TypeAndModeMapper typeAndModeMapper=Mappers.getMapper(TypeAndModeMapper.class);
	
	
	@Mapping(source = "modeId.id", target = "modeId")
	@Mapping(source = "modeId.name", target = "modeName")
	@Mapping(source = "typeId.id", target = "typeId")
	@Mapping(source = "typeId.name", target = "typeName")
	TypeAndMode mapVerificationChecklistEntitytoTypeAndModeDto(MapVeritificationChecklist checklist);
	@Mapping(target = "modeId.id", source = "modeId")
	@Mapping(target = "modeId.name", source = "modeName")
	@Mapping(target = "typeId.id", source = "typeId")
	@Mapping(target = "typeId.name", source = "typeName")
	MapVeritificationChecklist typeAndModeDtotoEntity(TypeAndMode checklistDto);
	
	List<TypeAndMode> mapVerificationChecklistEntitylistToTypeAndModeDto(List<MapVeritificationChecklist> list);
}
