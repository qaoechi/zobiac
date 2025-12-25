package com.jumjari.zobiac.application.building.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import com.jumjari.zobiac.domain.building.BuildingRepository;
import com.jumjari.zobiac.domain.building.BuildingEntity;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ConversionService {
    private final BuildingRepository repository;

    public String getKorFull(String engShort) {
        return repository.findByEngShort(engShort)
            .map(BuildingEntity::getKorFull)
            .orElse(null);
    }
    public String getKorShort(String engShort) {
        return repository.findByEngShort(engShort)
            .map(BuildingEntity::getKorShort)
            .orElse(null);
    }
    public String getEngShort(String korFull) {
        return repository.findByKorFull(korFull)
            .map(BuildingEntity::getEngShort)
            .orElse(null);
    }
}