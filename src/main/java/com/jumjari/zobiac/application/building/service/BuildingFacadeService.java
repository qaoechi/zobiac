package com.jumjari.zobiac.application.building.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.jumjari.zobiac.application.building.dto.Building;

@Service
@RequiredArgsConstructor
public class BuildingFacadeService {
    private final BuildingSearchService search;

    public List<Building> getBuildings() {
        return search.getAll();
    }
    public String getKorFull(String engShort) {
        return search.getByEngShort(engShort)
            .map(Building::getKorFull)
            .orElseThrow(() -> new IllegalArgumentException(engShort + "does not exist"));
    }
    public String getKorShort(String engShort) {
        return search.getByEngShort(engShort)
            .map(Building::getKorShort)
            .orElseThrow(() -> new IllegalArgumentException(engShort + "does not exist"));
    }
    public String getEngShort(String korFull) {
        return search.getByKorFull(korFull)
            .map(Building::getEngShort)
            .orElseThrow(() -> new IllegalArgumentException(korFull + "does not exist"));
    }
}