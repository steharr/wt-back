package com.workout.domain.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Workout {

    private Long workoutId;
    private List<Exercise> exercise;
    private Date date;
    private Integer rating;

}
