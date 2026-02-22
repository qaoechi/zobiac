package com.jumjari.zobiac.application.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumjari.zobiac.domain.member.User;
import com.jumjari.zobiac.domain.member.UserRepository;

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
}