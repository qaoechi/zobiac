package com.jumjari.zobiac.domain.building;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Immutable
@NoArgsConstructor
@Getter
@Setter
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