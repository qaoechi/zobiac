package com.jumjari.zobiac.application.direction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import com.jumjari.zobiac.domain.direction.DirectionRepository;
import com.jumjari.zobiac.application.mapper.DirectionMapper;
import com.jumjari.zobiac.application.direction.dto.Direction;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DirectionService {
    private final DirectionRepository repository;
    private final DirectionMapper mapper;

    public Direction getLeft() {
        return repository.findByDirection("LEFT")
            .map(mapper::toDto)
            .orElse(null);
    }
    public Direction getRight() {
        return repository.findByDirection("RIGHT")
            .map(mapper::toDto)
            .orElse(null);
    }
    public Direction getNot() {
        return repository.findByDirection("NO_SIGN")
            .map(mapper::toDto)
            .orElse(null);
    }
}