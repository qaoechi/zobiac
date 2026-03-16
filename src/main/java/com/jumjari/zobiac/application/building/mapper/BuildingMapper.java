package com.jumjari.zobiac.application.building.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.building.dto.BuildingDetail;
import com.jumjari.zobiac.application.building.dto.BuildingMarkerResponse;
import com.jumjari.zobiac.application.building.dto.BuildingSearchRequest;
import com.jumjari.zobiac.domain.building.BuildingEntity;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    BuildingDetail toDetail(BuildingEntity entity);
    BuildingMarkerResponse toMarker(BuildingEntity entity);
    BuildingSearchRequest toRequest(BuildingEntity entity);
}