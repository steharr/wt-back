package com.workout.session.application;

import com.workout.session.domain.dto.WorkoutAnalysisDTO;
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
    public List<Workout> getWorkouts(String username) {
        log.info("Getting workout entities...begin");
        List<WorkoutEntity> entities = workoutRepository.findWorkoutEntitiesByUsername(username);
        log.info("Getting workout entities...complete");
        return entities.stream().map(ent -> workoutMapper.entityToModel(ent)).collect(Collectors.toList());
    }


    @Transactional
    public void saveWorkout(Workout workout, String username) {
        log.info("Transforming workout model to entity...begin");
        WorkoutEntity entity = workoutMapper.modelToEntity(workout);
        entity.setUsername(username);
        log.info("Transforming workout model to entity...complete");
        workoutRepository.saveAndFlush(entity);
    }

    @Transactional
    public void saveExercise(Exercise exercise) {
        ExerciseEntity entity = exerciseMapper.modelToEntity(exercise);
        exerciseRepository.saveAndFlush(entity);
    }

    @Transactional
    public Optional<WorkoutAnalysisDTO> getWorkoutAnalysis(Long id) {
        Optional<WorkoutEntity> entity = workoutRepository.findById(id);
        return entity.map(workoutEntity -> new WorkoutAnalysis(workoutMapper.entityToModel(workoutEntity)).toDTO());
    }


    @Transactional
    public boolean deleteWorkout(Long id) {
        if (workoutRepository.findById(id).isPresent()) {
            workoutRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
