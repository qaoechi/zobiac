package com.jumjari.zobiac.application.classroom.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.classroom.dto.Classroom;
import com.jumjari.zobiac.application.classroom.dto.ClassroomBoardResponse;
import com.jumjari.zobiac.domain.classroom.entity.ClassroomEntity;

@Mapper(componentModel = "spring", uses = RoomMapper.class)
public interface ClassroomMapper {
    Classroom toDto(ClassroomEntity entity);
    ClassroomEntity toEntity(Classroom dto);
    List<ClassroomBoardResponse> toBoards(List<ClassroomEntity> entities);
}