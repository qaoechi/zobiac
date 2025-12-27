package com.jumjari.zobiac.domain.room;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(
    name = "rooms",
    uniqueConstraints = @UniqueConstraint(
        name = "unique_room",
        columnNames = {"building_name", "room_number", "room_floor"}
    )
)
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "building_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_rooms_building")
    )
    private Long building;

    @Column(name = "room_number", nullable = false, length = 5)
    private String number;
    @Column(name = "room_floor", nullable = false)
    private Byte floor;
}