package com.workout.infrastructure.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Table(name = "WORKOUTS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workoutId;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<ExerciseEntity> exercises;

    @Temporal(TemporalType.DATE)
    private Date date;
}
