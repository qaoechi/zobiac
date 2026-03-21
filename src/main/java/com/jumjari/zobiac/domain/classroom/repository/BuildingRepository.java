package com.jumjari.zobiac.domain.classroom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumjari.zobiac.domain.classroom.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
    Optional<BuildingEntity> findByEngShort(String engShort);
    Optional<BuildingEntity> findByKorFull(String korFull);
    List<BuildingEntity> findByKorFullContaining(String input);
}