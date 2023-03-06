package com.workout.session.infrastructure.repository.map;

import com.workout.session.domain.model.Workout;
import com.workout.session.infrastructure.repository.entity.ExerciseEntity;
import com.workout.session.infrastructure.repository.entity.WorkoutEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkoutMapper implements Mapper<Workout, WorkoutEntity> {

    @Autowired
    ExerciseMapper exerciseMapper;

    @Override
    public Workout entityToModel(WorkoutEntity ent) {
        return new Workout(ent.getWorkoutId(),
                ent.getExercises().stream().map(exerciseMapper::entityToModel).collect(Collectors.toList()),
                ent.getDate(), 1);
    }

    @Override
    public WorkoutEntity modelToEntity(Workout model) {
        List<ExerciseEntity> exercises = model.getExercise().stream().map(exerciseMapper::modelToEntity).toList();
        WorkoutEntity entity = new WorkoutEntity();
        entity.setWorkoutId(model.getWorkoutId());
        exercises.stream().forEach(ex -> ex.setWorkout(entity));
        entity.setExercises(exercises);
        entity.setDate(model.getDate());
        return entity;
    }


}
