package com.jumjari.zobiac.domain.classroom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jumjari.zobiac.domain.classroom.entity.ClassroomEntity;
import com.jumjari.zobiac.domain.classroom.entity.Status;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
    Optional<ClassroomEntity> findById(Long id);

    @EntityGraph(attributePaths = {"room", "room.building"})
    List<ClassroomEntity> findAllByRoom_Building_korFull(String korFull);
    @EntityGraph(attributePaths = {"room", "room.building"})
    List<ClassroomEntity> findAllByRoom_Building_korFullAndStatus(String korFull, Status status);
    long countByRoomId(Long roomId);
    // Optional<ClassroomEntity> findAllByBuildingNameTrue(String buildingName);
}