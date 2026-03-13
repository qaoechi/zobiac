package com.jumjari.zobiac.domain.schedule.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumjari.zobiac.domain.schedule.entity.MeetingEntity;

public interface MeetingRepository extends JpaRepository<MeetingEntity, Long> {
    Optional<MeetingEntity> findByTitle(String title);
    List<MeetingEntity> findAllByOpen(boolean open);
}