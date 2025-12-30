package com.jumjari.zobiac.application.door_type.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.door_type.dto.DoorType;
import com.jumjari.zobiac.application.door_type.mapper.DoorTypeMapper;
import com.jumjari.zobiac.domain.door_type.DoorTypeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoorTypeService {
    private final DoorTypeRepository repository;
    private final DoorTypeMapper mapper;

    public DoorType getSingle() {
        return repository.findByType("SINGLE")
            .map(mapper::toDto)
            .orElse(null);
    }
    public DoorType getDouble() {
        return repository.findByType("DOUBLE")
            .map(mapper::toDto)
            .orElse(null);
    }
    public DoorType getIDK() {
        return repository.findByType("IDK")
            .map(mapper::toDto)
            .orElse(null);
    }
}