package com.jumjari.zobiac.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.jumjari.zobiac.domain.schedule.entity.MeetingEntity;
import com.jumjari.zobiac.domain.schedule.entity.ParticipantEntity;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    List<ParticipantEntity> findAllByMeeting(MeetingEntity meeting);
    Optional<ParticipantEntity> findByMeetingIdAndUserId(Long meetingId, Long UserId);
    Optional<ParticipantEntity> findByMeetingIdAndToken(Long meetingId, String token);
}