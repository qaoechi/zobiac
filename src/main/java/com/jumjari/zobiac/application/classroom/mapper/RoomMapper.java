package com.jumjari.zobiac.application.classroom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jumjari.zobiac.application.classroom.dto.Room;
import com.jumjari.zobiac.domain.classroom.entity.RoomEntity;

@Mapper(componentModel = "spring", uses = BuildingMapper.class)
public interface RoomMapper {
    @Mapping(source = "id", target = "roomId")
    Room toRoomDto(RoomEntity entity);
    @Mapping(source = "roomId", target = "id")
    RoomEntity toRoomEntity(Room dto);
}