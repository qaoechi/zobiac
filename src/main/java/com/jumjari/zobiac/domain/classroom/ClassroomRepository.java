package com.jumjari.zobiac.domain.classroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
    Optional<ClassroomEntity> findById(Long id);

    @EntityGraph(attributePaths = {"room", "room.building"})
    List<ClassroomEntity> findAllByRoom_Building_korFullAndIsActiveTrue(String korFull);

    // Optional<ClassroomEntity> findAllByBuildingNameTrue(String buildingName);
}