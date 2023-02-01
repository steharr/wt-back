package com.workout.domain.model;

import lombok.Data;

@Data
public class Exercise {

    private String exercise;
    private Long sets;
    private Long reps;
}
