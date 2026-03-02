package com.jumjari.zobiac.application.schedule.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.schedule.dto.Availability;
import com.jumjari.zobiac.domain.schedule.entity.AvailabilityEntity;

@Mapper(componentModel = "spring")
public interface AvailabilityMapper {
    Availability toDto(AvailabilityEntity entity);
    AvailabilityEntity toEntity(Availability dto);
}