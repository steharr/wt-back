package com.workout.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="EXERCISES")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exerciseId;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private WorkoutEntity workout;

    private String exerciseName;
    private Long sets;
    private Long reps;

}
