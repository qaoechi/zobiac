package com.jumjari.zobiac.domain.classroom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jumjari.zobiac.domain.building.BuildingEntity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(
    name = "rooms",
    uniqueConstraints = @UniqueConstraint(
        name = "unique_room",
        columnNames = {"room_number", "room_floor"}
    )
)
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "building_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_rooms_building")
    )
    private BuildingEntity building;

    @Column(name = "room_number", nullable = false, length = 5)
    private String number;
    @Column(name = "room_floor", nullable = false)
    private Byte floor;
}