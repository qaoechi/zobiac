package com.jumjari.zobiac.domain.door_type;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import org.hibernate.annotations.Immutable;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Immutable
@NoArgsConstructor
@Getter
@Setter
@Table(name = "door_types")
public class DoorTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "type_name", nullable = false, length = 20, unique = true)
    private String type;
}