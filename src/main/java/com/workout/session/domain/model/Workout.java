package com.workout.session.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workout {
    private Long workoutId;
    private List<Exercise> exercise;
    private Date date;
    private Integer rating;
}
