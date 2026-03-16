package com.jumjari.zobiac.application.building.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.building.dto.BuildingMarkerResponse;
import com.jumjari.zobiac.application.building.mapper.BuildingMapper;
import com.jumjari.zobiac.domain.building.BuildingRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuildingService {
    private final BuildingRepository repository;
    private final BuildingMapper mapper;

    public List<BuildingMarkerResponse> getAllMarker() {
        return repository.findAll()
            .stream()
            .map(mapper::toMarker)
            .toList();
    }
    public String getByKorFull(String korFull) {
        return repository.findByKorFull(korFull)
            .orElseThrow(() -> new IllegalArgumentException(korFull + "does not exist"))
            .getKorFull();
    }
    // public String getKorShort(String engShort) {
    //     return repository.findByEngShort(engShort)
    //         .orElseThrow(() -> new IllegalArgumentException(engShort + "does not exist"))
    //         .getKorShort();
    // }
    // public String getEngShort(String korFull) {
    //     return repository.findByKorFull(korFull)
    //         .orElseThrow(() -> new IllegalArgumentException(korFull + "does not exist"))
    //         .getEngShort();
    // }
    public Optional<String> getByInput(String name) {
        return repository.findByKorFullContaining(name)
            .stream()
            .findFirst()
            .map(req -> "/" + req.getKorFull());
    }
}