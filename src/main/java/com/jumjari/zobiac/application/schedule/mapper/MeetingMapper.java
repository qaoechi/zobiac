package com.jumjari.zobiac.application.schedule.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.jumjari.zobiac.api.manager.schedule.dto.MeetingRequest;
import com.jumjari.zobiac.application.schedule.dto.Meeting;
import com.jumjari.zobiac.application.schedule.dto.MeetingResponse;
import com.jumjari.zobiac.domain.schedule.entity.MeetingEntity;

@Mapper(componentModel = "spring", uses = ParticipantMapper.class)
public interface MeetingMapper {
    MeetingResponse toResponse(MeetingEntity entity);
    List<MeetingResponse> toResponses(List<MeetingResponse> entities);
    Meeting toDto(MeetingEntity entity);
    
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "participants", ignore = true),
        @Mapping(target = "createdBy", ignore = true),
        @Mapping(target = "createdAt", ignore = true)

    })
    MeetingEntity toEntity(MeetingRequest request);
}