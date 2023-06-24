package com.workout.session.infrastructure.repository.entity;


import com.workout.security.infrastructure.repository.entity.AccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Table(name = "WORKOUTS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WORKOUT_ID")
    private Long workoutId;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<ExerciseEntity> exercises;

    @Column(name = "WORKOUT_DATE")
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "WORKOUT_USER_ID")
    private AccountEntity user;
}
