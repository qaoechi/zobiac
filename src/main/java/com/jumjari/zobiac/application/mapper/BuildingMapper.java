package com.jumjari.zobiac.application.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.building.dto.Building;
import com.jumjari.zobiac.domain.building.BuildingEntity;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    Building toDTO(BuildingEntity building);
}