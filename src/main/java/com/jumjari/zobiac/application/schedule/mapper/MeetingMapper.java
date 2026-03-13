package com.jumjari.zobiac.application.schedule.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.schedule.dto.Meeting;
import com.jumjari.zobiac.domain.schedule.entity.MeetingEntity;

@Mapper(componentModel = "spring", uses = ParticipantMapper.class)
public interface MeetingMapper {
    Meeting toDto(MeetingEntity entity);
    MeetingEntity toEntity(Meeting dto);
}