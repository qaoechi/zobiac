package com.jumjari.zobiac.domain.classroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.jumjari.zobiac.domain.classroom.field.DirectionEntity;
import com.jumjari.zobiac.domain.classroom.field.DoorTypeEntity;
import com.jumjari.zobiac.domain.room.RoomEntity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "classrooms")
public class ClassroomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "room_id",
        nullable = false,
        unique = true,
        foreignKey = @ForeignKey(name = "fk_room")
    )
    private RoomEntity room;

    @Column(name = "room_name", length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "direction_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_direction")
    )
    private DirectionEntity direction;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "door_type_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_type")
    )
    private DoorTypeEntity type;

    @Column(name = "door_count", nullable = false)
    private Byte count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "parent_id",
        foreignKey = @ForeignKey(name = "fk_parent")
    )
    private ClassroomEntity parent;

    @Column(name = "memo", columnDefinition = "TEXT")
    private String memo;
}