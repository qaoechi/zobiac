package com.jumjari.zobiac.application.building.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.building.dto.BuildingMarkerResponse;
import com.jumjari.zobiac.application.building.dto.BuildingSelectResponse;
import com.jumjari.zobiac.application.building.mapper.BuildingMapper;
import com.jumjari.zobiac.domain.building.BuildingEntity;
import com.jumjari.zobiac.domain.building.BuildingRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuildingService {
    private final BuildingRepository repository;
    private final BuildingMapper mapper;

    public BuildingEntity getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(id + "does not exist"));
    }
    public BuildingEntity getByEng(String eng) {
        return repository.findByEngShort(eng)
            .orElseThrow(() -> new IllegalArgumentException(eng + "does not exist"));
    }

    public List<BuildingSelectResponse> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toOption)
            .toList();
    }
    public List<BuildingMarkerResponse> getAllMarker() {
        return repository.findAll()
            .stream()
            .map(mapper::toMarker)
            .toList();
    }
    public String getKorFullByEngShort(String engShort) {
        return repository.findByEngShort(engShort)
            .orElseThrow(() -> new IllegalArgumentException(engShort + "does not exist"))
            .getKorFull();
    }
    public String getKorShortByEngShort(String engShort) {
        return repository.findByEngShort(engShort)
            .orElseThrow(() -> new IllegalArgumentException(engShort + "does not exist"))
            .getKorShort();
    }
    public Optional<String> getByInput(String name) {
        return repository.findByKorFullContaining(name)
            .stream()
            .findFirst()
            .map(req -> req.getEngShort());
    }
}