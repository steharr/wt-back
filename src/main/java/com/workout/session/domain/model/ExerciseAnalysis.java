package com.workout.session.domain.model;

import com.workout.session.domain.dto.ExerciseAnalysisDTO;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ExerciseAnalysis {

    private final List<Workout> workouts;
    private final String exercise;

    public ExerciseAnalysis(List<Workout> workouts, String exercise) {
        this.workouts = workouts;
        this.exercise = exercise;
    }

    public ExerciseAnalysisDTO getAnalysisDTO() {
        var analysis = new ExerciseAnalysisDTO();

        return analysis;
    }

    public Integer getWeightProgression() {
        int increaseCount = 0;
        BigDecimal initial = BigDecimal.ZERO;
        BigDecimal lastValue = BigDecimal.ZERO;

        for (Workout workout : this.workouts) {
            Optional<Exercise> ex = filteredExercise(this.exercise, workout);
            if (ex.isPresent()) {
                if (BigDecimal.ZERO.equals(initial)) {
                    initial = ex.get().getWeight();
                }
                if (lastValue.compareTo(ex.get().getWeight()) > 0) {
                    increaseCount++;
                }
                lastValue = ex.get().getWeight();
            }
        }

        if (increaseCount > 0) {
            return 1;
        } else if (increaseCount == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    public Optional<Exercise> filteredExercise(String ex, Workout workout) {
        return workout.getExercises().stream().filter(elem -> elem.getExercise().equals(ex)).findFirst();
    }
}
