package com.jumjari.zobiac.application.schedule.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jumjari.zobiac.application.schedule.dto.AvailabilityRequest;
import com.jumjari.zobiac.application.schedule.dto.AvailabilityResponse;
import com.jumjari.zobiac.domain.schedule.entity.AvailabilityEntity;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;

@Mapper(componentModel = "spring")
public interface AvailabilityMapper {
    AvailabilityResponse toResponse(AvailabilityEntity entity);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "participant", source = "participant")
    AvailabilityEntity toEntity(AvailabilityRequest request, ParticipantEntity participant);
}