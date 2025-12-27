package com.jumjari.zobiac.domain.classroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
    @EntityGraph(attributePaths = "room")
    @Query(value = """
        SELECT c
        FROM ClassroomEntity c
        JOIN FETCH c.room r
        WHERE r.building = :building AND c.actived = true
        ORDER BY r.floor ASC, r.number ASC
    """)
    List<ClassroomEntity> findAllByBuildingTrue(String building);

    @EntityGraph(attributePaths = "room")
    @Query(value = """
        SELECT c
        FROM ClassroomEntity c
        JOIN FETCH c.room r
        WHERE r.building = :building
        ORDER BY r.floor ASC, r.number ASC
    """)
    List<ClassroomEntity> findAllByBuilding(String building);
    
    @EntityGraph(attributePaths = "room")
    Optional<ClassroomEntity> findById(Long id);
    // @EntityGraph(attributePaths = "room")
    // List<ClassroomEntity> findAllByRoom_BuildingAndActivedTrue(String name);
    // @EntityGraph(attributePaths = "room")
    // Optional<ClassroomEntity> findByIdAndActivedTrue(Long id);
}