package com.jumjari.zobiac.domain.classroom.field;

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
@Table(name = "directions")
public class DirectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "direction_name", nullable = false, length = 7, unique = true)
    private String direction;
}