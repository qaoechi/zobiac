package com.jumjari.zobiac.domain.room;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

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

    @Column(name = "building_name", nullable = false, length = 31)
    private String building;
    @Column(name = "room_number", nullable = false, length = 5)
    private String number;
    @Column(name = "room_floor", nullable = false)
    private Byte floor;
}