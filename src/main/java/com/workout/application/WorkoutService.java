package com.workout.application;

import com.workout.domain.model.Exercise;
import com.workout.domain.model.Workout;
import com.workout.infrastructure.repository.ExerciseRepository;
import com.workout.infrastructure.repository.WorkoutRepository;
import com.workout.infrastructure.repository.entity.ExerciseEntity;
import com.workout.infrastructure.repository.entity.WorkoutEntity;
import com.workout.infrastructure.repository.map.ExerciseMapper;
import com.workout.infrastructure.repository.map.WorkoutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    WorkoutRepository workoutRepository;
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    WorkoutMapper workoutMapper;
    @Autowired
    ExerciseMapper exerciseMapper;

    @Transactional
    public Workout getWorkout() {
        List<WorkoutEntity> entities = workoutRepository.findAll();
        return workoutMapper.entityToModel(entities.get(0));
    }

    @Transactional
    public void saveWorkout(Workout workout) {
        WorkoutEntity entity = workoutMapper.modelToEntity(workout);
        workoutRepository.saveAndFlush(entity);
    }

    @Transactional
    public void saveExercise(Exercise exercise) {
        ExerciseEntity entity = exerciseMapper.modelToEntity(exercise);
        exerciseRepository.saveAndFlush(entity);
    }

}
