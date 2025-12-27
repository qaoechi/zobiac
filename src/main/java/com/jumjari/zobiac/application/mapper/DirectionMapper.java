package com.jumjari.zobiac.application.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.domain.direction.DirectionEntity;
import com.jumjari.zobiac.application.direction.dto.Direction;

@Mapper(componentModel = "spring")
public interface DirectionMapper {
    Direction toDto(DirectionEntity entity);
}