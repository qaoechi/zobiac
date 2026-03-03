package com.jumjari.zobiac.domain.schedule.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumjari.zobiac.domain.schedule.entity.AvailabilityEntity;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;

public interface AvailabilityRepository extends JpaRepository<AvailabilityEntity, Long> {
    List<AvailabilityEntity> findAllBySlotBetween(int start, int end);
    void deleteAllByParticipant(ParticipantEntity participant);
}