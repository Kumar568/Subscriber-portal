package com.emudhra.emra.subscriber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.emudhra.emra.subscriber.dto.FieldGroupDto;
import com.emudhra.emra.subscriber.entity.entity.FieldGroup;

@Mapper(uses = MapFieldGroupMapper.class)
public interface FieldGroupMapper {
    
    FieldGroupMapper MAPPER = Mappers.getMapper(FieldGroupMapper.class);

    List<FieldGroupDto> mapFieldGroupToFieldGroupDtoList(List<FieldGroup> fieldGroup);

    @Mapping(source = "mapFieldGroup", target = "mapFieldGroupDtoList")
    FieldGroupDto mapFieldGroupToFieldGroupDto(FieldGroup fieldGroup);
}
