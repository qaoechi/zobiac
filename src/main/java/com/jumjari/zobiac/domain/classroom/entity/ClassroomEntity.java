package com.jumjari.zobiac.domain.classroom.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "classrooms")
public class ClassroomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", length = 7, nullable = false)
    private Direction direction;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "door_type", length = 20, nullable = false)
    private DoorType type;

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

    @OneToMany(mappedBy = "parent")
    private List<ClassroomEntity> children = new ArrayList<>();
}