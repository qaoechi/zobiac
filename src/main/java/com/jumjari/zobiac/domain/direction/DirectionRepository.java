package com.jumjari.zobiac.domain.direction;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<DirectionEntity, Long> {
    Optional<DirectionEntity> findByDirection(String direction);
}