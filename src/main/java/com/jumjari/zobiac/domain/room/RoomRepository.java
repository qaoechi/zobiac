package com.jumjari.zobiac.domain.room;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    List<RoomEntity> findAllByBuilding(String building);
    boolean existsByBuildingAndNumberAndFloor(String building, String number, Byte floor);
    Optional<RoomEntity> findByBuildingAndNumberAndFloor(String building, String number, Byte floor);
}