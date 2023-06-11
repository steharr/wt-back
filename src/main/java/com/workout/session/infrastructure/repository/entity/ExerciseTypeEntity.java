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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

}
