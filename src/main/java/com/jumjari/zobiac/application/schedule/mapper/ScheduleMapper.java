package com.jumjari.zobiac.application.schedule.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jumjari.zobiac.application.schedule.dto.ScheduleParticipantAvailabilityResponse;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;

@Mapper(componentModel = "spring", uses = AvailabilityMapper.class)
public interface ScheduleMapper {
    @Mapping(target = "participantId", source = "id")
    ScheduleParticipantAvailabilityResponse toParticipantAvailabilityResponse(ParticipantEntity entity);
    List<ScheduleParticipantAvailabilityResponse> toParticipantAvailabilityResponses(List<ParticipantEntity> entities);
}