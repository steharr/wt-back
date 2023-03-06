package com.workout.session.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    private String exercise;
    private Long sets;
    private Long reps;
    private BigDecimal weight;
}
