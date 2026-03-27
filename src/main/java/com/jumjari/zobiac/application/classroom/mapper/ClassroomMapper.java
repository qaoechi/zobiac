package com.jumjari.zobiac.application.classroom.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jumjari.zobiac.application.classroom.dto.ClassroomDetail;
import com.jumjari.zobiac.application.classroom.dto.ClassroomBoardResponse;
import com.jumjari.zobiac.domain.classroom.entity.ClassroomEntity;

@Mapper(componentModel = "spring", uses = RoomMapper.class)
public interface ClassroomMapper {
    ClassroomDetail toDetail(ClassroomEntity entity);
    List<ClassroomDetail> toDetails(List<ClassroomEntity> entities);

    List<ClassroomBoardResponse> toBoards(List<ClassroomEntity> entities);
}