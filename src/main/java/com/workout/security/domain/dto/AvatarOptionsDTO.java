package com.workout.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarOptionsDTO {

    List<String> hairChoices;
    List<String> eyeChoices;
}
