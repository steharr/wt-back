package com.workout.session.domain.model;

import java.math.BigDecimal;

public class WorkoutAnalysis {

    private Workout workout;
    private Long avgReps;
    private BigDecimal avgWeight;
    private Integer numberOfExercises;

    public WorkoutAnalysis(Workout workout) {
        this.workout = workout;
        this.numberOfExercises = calculateNumberOfExercises();
        this.avgReps = calculateAvgReps();
        this.avgWeight = calculateAvgWeight();
    }

    private Integer calculateNumberOfExercises() {
        return this.workout.getExercise().size();
    }

    private Long calculateAvgReps() {
        Long totalReps = this.workout.getExercise().stream().map(Exercise::getReps).reduce(0L, Long::sum);
        return totalReps / this.numberOfExercises;
    }

    private BigDecimal calculateAvgWeight() {
        BigDecimal totalWeight = this.workout.getExercise().stream().map(Exercise::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalWeight.divide(BigDecimal.valueOf(this.numberOfExercises));
    }

}
