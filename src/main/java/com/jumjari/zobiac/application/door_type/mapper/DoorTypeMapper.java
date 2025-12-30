package com.jumjari.zobiac.application.door_type.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.door_type.dto.DoorType;
import com.jumjari.zobiac.domain.door_type.DoorTypeEntity;

@Mapper(componentModel = "spring")
public interface DoorTypeMapper {
    DoorType toDto(DoorTypeEntity entity);
}