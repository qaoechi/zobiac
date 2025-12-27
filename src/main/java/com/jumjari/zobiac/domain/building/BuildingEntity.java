package com.jumjari.zobiac.domain.building;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import org.hibernate.annotations.Immutable;

import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@Immutable
@NoArgsConstructor
@Getter
@Table(name = "buildings")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "building_name", nullable = false, length = 31, unique = true)
    private String korFull;
    @Column(name = "campus", nullable = false)
    private boolean campus;
    @Column(name = "short_name", nullable = false, length = 15)
    private String korShort;
    @Column(name = "url_name", nullable = false, length = 50, unique = true)
    private String engShort;
    @Column(name = "longitude", nullable = false)
    private Double longitude;
    @Column(name = "latitude", nullable = false)
    private Double latitude;
}