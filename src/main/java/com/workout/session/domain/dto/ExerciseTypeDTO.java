package com.workout.session.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseTypeDTO {
    private String name;
    private String descriptionUrl;
    private String imageUrl;
}
