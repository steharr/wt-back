package com.workout.session.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutAnalysisDTO {

    private Long avgReps;
    private BigDecimal avgWeight;
    private Integer numberOfExercises;
}
