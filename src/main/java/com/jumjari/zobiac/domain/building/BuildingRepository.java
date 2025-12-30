package com.jumjari.zobiac.domain.building;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
    Optional<BuildingEntity> findByEngShort(String engShort);
    Optional<BuildingEntity> findByKorFull(String korFull);
    List<BuildingEntity> findByKorFullContaining(String input);
}