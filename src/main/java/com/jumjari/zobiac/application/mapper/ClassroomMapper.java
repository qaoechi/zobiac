package com.jumjari.zobiac.application.mapper;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.classroom.dto.Classroom;
import com.jumjari.zobiac.domain.classroom.ClassroomEntity;

@Mapper(componentModel = "spring", uses = {RoomMapper.class, DirectionMapper.class, DoorTypeMapper.class})
public interface ClassroomMapper {
    Classroom toDto(ClassroomEntity entity);
    ClassroomEntity toEntity(Classroom dto);
}