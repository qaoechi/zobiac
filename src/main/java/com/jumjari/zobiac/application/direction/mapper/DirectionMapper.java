package com.jumjari.zobiac.application.direction.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.direction.dto.Direction;
import com.jumjari.zobiac.domain.direction.DirectionEntity;

@Mapper(componentModel = "spring")
public interface DirectionMapper {
    Direction toDto(DirectionEntity entity);
}