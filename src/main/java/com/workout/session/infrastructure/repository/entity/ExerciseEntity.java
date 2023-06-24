package com.workout.session.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "EXERCISES")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exerciseId;

    @ManyToOne()
    @JoinColumn(name = "workoutId")
    private WorkoutEntity workout;

    //    TODO: configure in db
    private String type;

    private Long sets;
    private Long reps;
    private BigDecimal weight;

    private Integer rating;

}
