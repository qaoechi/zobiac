package com.jumjari.zobiac.application.classroom.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.classroom.dto.BuildingDetail;
import com.jumjari.zobiac.application.classroom.dto.BuildingMarkerResponse;
import com.jumjari.zobiac.application.classroom.dto.BuildingSelectResponse;
import com.jumjari.zobiac.domain.classroom.entity.BuildingEntity;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    BuildingDetail toDetail(BuildingEntity entity);
    BuildingMarkerResponse toMarker(BuildingEntity entity);
    BuildingSelectResponse toOption(BuildingEntity entity);
}