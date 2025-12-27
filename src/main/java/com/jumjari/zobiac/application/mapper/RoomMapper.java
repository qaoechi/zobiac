package com.jumjari.zobiac.application.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.room.dto.Room;
import com.jumjari.zobiac.domain.room.RoomEntity;

@Mapper(componentModel = "spring", uses = BuildingMapper.class)
public interface RoomMapper {
    Room toRoomDto(RoomEntity entity);
    RoomEntity toRoomEntity(Room dto);
}