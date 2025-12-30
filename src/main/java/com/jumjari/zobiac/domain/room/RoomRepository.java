package com.jumjari.zobiac.domain.room;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @EntityGraph(attributePaths = "building")
    List<RoomEntity> findAllByBuilding_korFull(String korFull);

    boolean existsByBuilding_korFullAndNumberAndFloor(String building, String number, Byte floor);

    @EntityGraph(attributePaths = "building")
    Optional<RoomEntity> findByBuilding_korFullAndNumberAndFloor(String building, String number, Byte floor);
}