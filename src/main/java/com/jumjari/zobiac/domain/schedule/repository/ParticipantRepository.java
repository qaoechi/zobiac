package com.jumjari.zobiac.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;
import java.util.List;
import com.jumjari.zobiac.domain.schedule.entity.MeetingEntity;


public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    List<ParticipantEntity> findAllByMeeting(MeetingEntity meeting);
}