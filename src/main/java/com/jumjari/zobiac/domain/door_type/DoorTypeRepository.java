package com.jumjari.zobiac.domain.door_type;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoorTypeRepository extends JpaRepository<DoorTypeEntity, Long> {
    Optional<DoorTypeEntity> findByType(String type);
}