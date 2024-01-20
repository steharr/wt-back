package com.workout.session.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "EXERCISE_TYPES")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long exerciseTypeId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String descriptionUrl;
    @Column(name = "image")
    private String imageUrl;
}
