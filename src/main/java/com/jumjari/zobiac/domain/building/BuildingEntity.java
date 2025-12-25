package com.jumjari.zobiac.domain.building;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "buildings")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "building_name")
    private String korFull;
    @Column(name = "campus")
    private boolean campus;
    @Column(name = "short_name")
    private String korShort;
    @Column(name = "url_name")
    private String engShort;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;
}