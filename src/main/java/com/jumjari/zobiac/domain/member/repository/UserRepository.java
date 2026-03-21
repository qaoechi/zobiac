package com.jumjari.zobiac.domain.member.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumjari.zobiac.domain.member.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    List<User> findAllByUsername(String username);
    Optional<User> findByNickname(String nickname);
    List<User> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}