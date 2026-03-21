package com.jumjari.zobiac.application.member.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumjari.zobiac.application.member.ProfileRequest;
import com.jumjari.zobiac.domain.member.entity.User;
import com.jumjari.zobiac.domain.member.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSearchService {
    private final UserRepository repository;

    public User getUser(long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(id + "not found"));
    }
    public List<User> getUsersByUsername(String username) {
        return repository.findAllByUsername(username);
    }
    public List<User> getByUsersBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findAllByCreatedAtBetween(start, end);
    }
    public User getByNickname(String nickname) {
        return repository.findByNickname(nickname)
            .orElseThrow(() -> new IllegalArgumentException(nickname + "not found"));
    }
    public void updateProfile(Long id, ProfileRequest request) {
        User user = repository.findById(id)
            .orElseThrow();
        
        user.updateProfile(
            request.username(),
            request.number(),
            request.nickname()
        );
    }
}