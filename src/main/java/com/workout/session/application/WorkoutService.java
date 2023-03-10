package com.workout.session.application;

import com.workout.session.domain.model.Exercise;
import com.workout.session.domain.model.Workout;
import com.workout.session.domain.model.WorkoutAnalysis;
import com.workout.session.infrastructure.repository.ExerciseRepository;
import com.workout.session.infrastructure.repository.WorkoutRepository;
import com.workout.session.infrastructure.repository.entity.ExerciseEntity;
import com.workout.session.infrastructure.repository.entity.WorkoutEntity;
import com.workout.session.infrastructure.repository.map.ExerciseMapper;
import com.workout.session.infrastructure.repository.map.WorkoutMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
//        TODO: add search by id for specific user workouts
        List<WorkoutEntity> entities = workoutRepository.findAll();
        log.info("Getting workout entities...complete");
        return workoutMapper.entityToModel(entities.get(0));
    }

    @Transactional
    public List<Workout> getWorkouts() {
        log.info("Getting workout entities...begin");
//        TODO: add search by id for specific user workouts
        List<WorkoutEntity> entities = workoutRepository.findAll();
        log.info("Getting workout entities...complete");
        return entities.stream().map(ent -> workoutMapper.entityToModel(ent)).collect(Collectors.toList());
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

    @Transactional
    public Optional<WorkoutAnalysis> getWorkoutAnalysis(Long id) {
        log.info("Getting workout analysis for id {}...begin", id);
//        TODO: add search by id for specific user workouts
        Optional<WorkoutEntity> e = workoutRepository.findById(id);
        log.info("Getting workout analysis for id {}...complete", id);
        return e.map(workoutEntity -> new WorkoutAnalysis(workoutMapper.entityToModel(workoutEntity)));
    }

}
