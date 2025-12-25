package com.jumjari.zobiac.application.building;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jumjari.zobiac.application.building.dto.Building;
import com.jumjari.zobiac.application.building.service.ConversionService;
import com.jumjari.zobiac.application.building.service.SearchService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuildingFacadeService {
    private final SearchService search;
    private final ConversionService conversion;

    public List<Building> getBuildings() {
        return search.getAll();
    }
    public String getKorFull(String engShort) {
        return conversion.getKorFull(engShort);
    }
    public String getKorShort(String engShort) {
        return conversion.getKorShort(engShort);
    }
    public String getEngShort(String korFull) {
        return conversion.getEngShort(korFull);
    }
}