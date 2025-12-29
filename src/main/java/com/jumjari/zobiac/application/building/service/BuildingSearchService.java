package com.jumjari.zobiac.application.building.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.building.dto.Building;
import com.jumjari.zobiac.application.building.mapper.BuildingMapper;
import com.jumjari.zobiac.domain.building.BuildingRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuildingSearchService {
    private final BuildingRepository repository;
    private final BuildingMapper mapper; 

    protected List<Building> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .toList();
    }
    protected Optional<Building> getByEngShort(String engShort) {
        return repository.findByEngShort(engShort)
            .map(mapper::toDto);
    }
    protected Optional<Building> getByKorFull(String korFull) {
        return repository.findByKorFull(korFull)
            .map(mapper::toDto);
    }
}