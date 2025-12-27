package com.jumjari.zobiac.application.building.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import com.jumjari.zobiac.domain.building.BuildingRepository;
import com.jumjari.zobiac.application.mapper.BuildingMapper;
import com.jumjari.zobiac.application.building.dto.Building;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class BuildingSearchService {
    private final BuildingRepository repository;
    private final BuildingMapper mapper; 

    public List<Building> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .toList();
    }
    public Building getByEngShort(String engShort) {
        return repository.findByEngShort(engShort)
            .map(mapper::toDto)
            .orElse(null);
    }
    public Building getByKorFull(String korFull) {
        return repository.findByKorFull(korFull)
            .map(mapper::toDto)
            .orElse(null);
    }
}