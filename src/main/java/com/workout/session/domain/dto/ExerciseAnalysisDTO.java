package com.workout.session.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseAnalysisDTO {

    private Integer ratingProgression;
    private Integer repsProgression;
    private Integer weightProgression;
    private Integer lastRepsCount;
    private String comment;
}
