package com.workout.session.domain.dto;

import com.workout.session.domain.model.ProgressEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseAnalysisDTO {

    private Integer ratingProgression;
    private Integer repsProgression;
    private ProgressEnum weightProgression;
    private Integer lastRepsCount;
    private String comment;
}
