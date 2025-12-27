package com.jumjari.zobiac.domain.classroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
    Optional<ClassroomEntity> findById(Long id);

    @EntityGraph(
        value = "ClassroomEntity.room.building",
        type = EntityGraphType.FETCH
    )
    @Query("""
            SELECT c
            FROM ClassroomEntity c
            JOIN c.room r
            JOIN r.building b
            where b.korFull = :buildingName and c.isActive = true
    """)
    List<ClassroomEntity> findAllByBuildingNameTrue(String buildingName);
    // @EntityGraph(attributePaths = {"room", "room.building"})
}