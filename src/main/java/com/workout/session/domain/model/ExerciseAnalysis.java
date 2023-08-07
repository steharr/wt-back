package com.workout.session.domain.model;

import com.workout.session.domain.dto.ExerciseAnalysisDTO;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ExerciseAnalysis {

    private final List<Workout> workouts;
    private Exercise mostRecentExercise;
    private ProgressionData weightProgression;
    private final String exercise;
    private String comment;

    public ExerciseAnalysis(List<Workout> workouts, String exercise) {
        this.workouts = workouts;
        this.exercise = exercise;
        getMostRecentExerciseData(exercise).ifPresent(data -> this.mostRecentExercise = data);
        this.weightProgression = this.determineWeightProgression();
        this.determineComment();
    }

    public Optional<Exercise> mostRecentExercise() {
        return Optional.ofNullable(mostRecentExercise);
    }

    public ExerciseAnalysisDTO getAnalysisDTO() {
        ExerciseAnalysisDTO analysis = new ExerciseAnalysisDTO();
        analysis.setWeightProgression(this.weightProgression.progressEnum());
        analysis.setComment(this.comment);
        return analysis;
    }

    public ProgressionData determineWeightProgression() {
        int increaseCount = 0;
        int decreaseCount = 0;
        int sameCount = 0;
        BigDecimal lastValue = BigDecimal.ZERO;
        ProgressEnum progressEnum;

        for (Workout workout : this.workouts) {
            Optional<Exercise> ex = filteredExercise(this.exercise, workout);
            if (ex.isPresent()) {
                int comparison = ex.get().getWeight().compareTo(lastValue);
                if (comparison > 0) {
                    increaseCount++;
                } else if (comparison == 0) {
                    sameCount++;
                } else {
                    decreaseCount++;
                }
            }
        }

        if (increaseCount > sameCount && increaseCount > decreaseCount) {
            progressEnum = ProgressEnum.IMPROVING;
        } else if (decreaseCount > sameCount && decreaseCount > increaseCount) {
            progressEnum = ProgressEnum.DECLINING;
        } else if (sameCount > increaseCount && sameCount > decreaseCount) {
            progressEnum = ProgressEnum.NO_CHANGE;
        } else {
            progressEnum = ProgressEnum.NA;
        }

        return new ProgressionData(
                progressEnum,
                this.mostRecentExercise().isPresent() ?
                        this.mostRecentExercise.getWeight() : BigDecimal.ZERO);
    }

    private Optional<Exercise> filteredExercise(String ex, Workout workout) {
        return workout.getExercises().stream().filter(elem -> elem.getExercise().equals(ex)).findFirst();
    }

    private Optional<Exercise> getMostRecentExerciseData(String ex) {
        List<Workout> sorted = new ArrayList<>(List.copyOf(this.workouts));
        sorted.sort(Comparator.comparing(Workout::getDate).reversed());

        Optional<List<Exercise>> exs = sorted.stream()
                .map(Workout::getExercises)
                .filter(exercises -> exercises.stream().anyMatch(e -> e.getExercise().equals(ex))).findFirst();

        return exs.flatMap(exercises -> exercises.stream().filter(e -> e.getExercise().equals(ex)).findFirst());
    }

    private void determineComment() {
        String text;

        switch (this.weightProgression.progressEnum()) {
            case IMPROVING: {
                text = "Your weight progression is improving for this exercise.";
                break;
            }
            case DECLINING: {
                text = "Your weight progression is declining for this exercise";
                break;
            }
            case NO_CHANGE: {
                text = "Your weight progression has not changed recently for this exercise";
                break;
            }
            default: {
                text = "No progression data available";
                break;
            }

        }
        if (this.mostRecentExercise().isPresent()) {
            text = this.addLastWeightData(text);
        }
        this.comment = text;
    }

    private String addLastWeightData(String text) {
        String lastTime = String.join(" ", "Last time you lifted:", this.mostRecentExercise.getWeight().toString(), "kg");
        return String.join("\n", text, lastTime);
    }
}
