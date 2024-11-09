package com.emudhra.emra.subscriber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.emudhra.emra.subscriber.dto.MapFieldGroupDto;
import com.emudhra.emra.subscriber.entity.entity.MapFieldGroup;

@Mapper
public interface MapFieldGroupMapper {
	
    MapFieldGroupMapper MAPPER = Mappers.getMapper(MapFieldGroupMapper.class);

    @Mapping(source = "masFieldList.displayName", target = "displayName")
    @Mapping(source = "masFieldList.fieldName", target = "fieldName")
    @Mapping(source = "masFieldList.id", target = "masfieldid")
    @Mapping(source = "masFieldList.oid", target = "oid")
    @Mapping(source = "masFieldList.alphabetsValidation", target = "alphabetsValidation")
    @Mapping(source = "masFieldList.numbersValidation", target = "numbersValidation")
    @Mapping(source = "masFieldList.specialCharactersValidation", target = "specialCharactersValidation")
    @Mapping(source = "masFieldList.isDefault", target = "isDefault")
    MapFieldGroupDto mapMapFieldGroupToMapFieldGroupDto(MapFieldGroup mapFieldGroup);

    List<MapFieldGroupDto> mapListMapFieldGroupToListMapFieldGroupDto(List<MapFieldGroup> mapFieldGroupList);
}


