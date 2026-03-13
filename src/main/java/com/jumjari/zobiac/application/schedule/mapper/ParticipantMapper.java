package com.jumjari.zobiac.application.schedule.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.schedule.dto.ParticipantResponse;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;

@Mapper(componentModel = "spring", uses = AvailabilityMapper.class)
public interface ParticipantMapper {
    ParticipantResponse toResponse(ParticipantEntity entity);
    List<ParticipantResponse> toResponses(List<ParticipantEntity> entities);
}