package com.workout.session.domain.map;

import com.workout.common.map.EntityMapper;
import com.workout.session.domain.model.Exercise;
import com.workout.session.infrastructure.repository.entity.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseEntityMapper implements EntityMapper<Exercise, ExerciseEntity> {

    private ExerciseEntityMapper() {
    }

    @Override
    public Exercise entityToModel(ExerciseEntity ent) {
        return new Exercise(ent.getType(), ent.getSets(), ent.getReps(), ent.getWeight());
    }

    @Override
    public ExerciseEntity modelToEntity(Exercise model) {
        var ex = new ExerciseEntity();
        ex.setType(model.getExercise());
        ex.setReps(model.getReps());
        ex.setSets(model.getSets());
        ex.setWeight(model.getWeight());
        return ex;
    }
}

