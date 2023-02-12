package com.workout.application;

import com.workout.domain.model.Exercise;
import com.workout.domain.model.Workout;
import com.workout.infrastructure.repository.ExerciseRepository;
import com.workout.infrastructure.repository.WorkoutRepository;
import com.workout.infrastructure.repository.entity.ExerciseEntity;
import com.workout.infrastructure.repository.entity.WorkoutEntity;
import com.workout.infrastructure.repository.map.ExerciseMapper;
import com.workout.infrastructure.repository.map.WorkoutMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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
        log.info("Getting workout entities...begin");
        List<WorkoutEntity> entities = workoutRepository.findAll();
        log.info("Getting workout entities...complete");
        return workoutMapper.entityToModel(entities.get(0));
    }

    @Transactional
    public void saveWorkout(Workout workout) {
        log.info("Transforming workout model to entity...begin");
        WorkoutEntity entity = workoutMapper.modelToEntity(workout);
        log.info("Transforming workout model to entity...complete");
        workoutRepository.saveAndFlush(entity);
    }

    @Transactional
    public void saveExercise(Exercise exercise) {
        ExerciseEntity entity = exerciseMapper.modelToEntity(exercise);
        exerciseRepository.saveAndFlush(entity);
    }

}
