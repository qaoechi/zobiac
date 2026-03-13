package com.jumjari.zobiac.application.schedule.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.schedule.dto.Participant;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;

@Mapper(componentModel = "spring", uses = AvailabilityMapper.class)
public interface ParticipantMapper {
    Participant toDto(ParticipantEntity entity);
    ParticipantEntity toEntity(Participant dto);
}