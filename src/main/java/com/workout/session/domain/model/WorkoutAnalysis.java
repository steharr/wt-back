package com.workout.session.domain.model;

import com.workout.session.domain.dto.WorkoutAnalysisDTO;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Slf4j
public class WorkoutAnalysis {

    private final Workout workout;
    private final Long avgReps;
    private final BigDecimal avgWeight;
    private final Integer numberOfExercises;

    public WorkoutAnalysis(Workout workout) {
        log.info("WorkoutAnalysis::Building workout analysis for workout id ... {}", workout.getWorkoutId());
        this.workout = workout;
        this.numberOfExercises = calculateNumberOfExercises();
        this.avgReps = calculateAvgReps();
        this.avgWeight = calculateAvgWeight();
    }

    private Integer calculateNumberOfExercises() {
        log.info("WorkoutAnalysis::Calculating number of exercises");
        return this.workout.getExercises().size();
    }

    private Long calculateAvgReps() {
        log.info("WorkoutAnalysis::Calculating average reps");
        Long totalReps = this.workout.getExercises().stream().map(Exercise::getReps).reduce(0L, Long::sum);
        return totalReps / this.numberOfExercises;
    }

    private BigDecimal calculateAvgWeight() {
        log.info("WorkoutAnalysis::Calculating average weight lifted");
        BigDecimal totalWeight = this.workout.getExercises().stream().map(Exercise::getWeight).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalWeight.divide(BigDecimal.valueOf(this.numberOfExercises), RoundingMode.HALF_UP);
    }

    public WorkoutAnalysisDTO toDTO() {
        return new WorkoutAnalysisDTO(this.avgReps, this.avgWeight, this.numberOfExercises);
    }

}
