package com.workout.infrastructure.repository.map;

import com.workout.domain.model.Exercise;
import com.workout.infrastructure.repository.entity.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper implements Mapper<Exercise, ExerciseEntity> {

    private ExerciseMapper() {
    }

    @Override
    public Exercise entityToModel(ExerciseEntity ent) {
        return new Exercise(ent.getExerciseName(), ent.getSets(), ent.getReps());
    }

    @Override
    public ExerciseEntity modelToEntity(Exercise model) {
        var ex = new ExerciseEntity();
        ex.setExerciseName(model.getExercise());
        ex.setReps(model.getReps());
        ex.setSets(model.getSets());
        return ex;
    }
}

