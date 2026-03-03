package com.jumjari.zobiac.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.jumjari.zobiac.application.schedule.dto.Meeting;
import com.jumjari.zobiac.domain.schedule.entity.MeetingEntity;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    List<ParticipantEntity> findAllByMeeting(MeetingEntity meeting);
    Optional<ParticipantEntity> findByMeetingAndToken(Meeting meeting, String token);
}