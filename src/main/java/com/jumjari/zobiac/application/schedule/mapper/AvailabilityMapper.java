package com.jumjari.zobiac.application.schedule.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.jumjari.zobiac.application.schedule.dto.AvailabilityRequest;
import com.jumjari.zobiac.application.schedule.dto.AvailabilityResponse;
import com.jumjari.zobiac.domain.schedule.entity.AvailabilityEntity;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;

@Mapper(componentModel = "spring")
public interface AvailabilityMapper {
    AvailabilityResponse toResponse(AvailabilityEntity entity);
    List<AvailabilityResponse> toResponses(List<AvailabilityEntity> entities);
    
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "participant", source = "participant")
    })
    AvailabilityEntity toEntity(AvailabilityRequest request, ParticipantEntity participant);
    default List<AvailabilityEntity> toEntities(List<AvailabilityRequest> requests, ParticipantEntity participant) {
        return requests.stream()
            .map(r -> toEntity(r, participant))
            .toList();
    }
}