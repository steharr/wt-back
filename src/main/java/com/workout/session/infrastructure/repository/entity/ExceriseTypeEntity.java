package com.workout.session.infrastructure.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceriseTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exerciseTypeId;
    private String name;
}
