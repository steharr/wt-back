package com.workout.session.application;

import com.workout.security.application.AccountService;
import com.workout.security.infrastructure.repository.entity.AccountEntity;
import com.workout.session.domain.dto.WorkoutAnalysisDTO;
import com.workout.session.domain.map.ExerciseEntityMapper;
import com.workout.session.domain.map.WorkoutEntityMapper;
import com.workout.session.domain.model.Exercise;
import com.workout.session.domain.model.Workout;
import com.workout.session.domain.model.WorkoutAnalysis;
import com.workout.session.infrastructure.repository.ExerciseRepository;
import com.workout.session.infrastructure.repository.WorkoutRepository;
import com.workout.session.infrastructure.repository.entity.ExerciseEntity;
import com.workout.session.infrastructure.repository.entity.WorkoutEntity;
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
    WorkoutEntityMapper workoutMapper;
    @Autowired
    ExerciseEntityMapper exerciseMapper;
    @Autowired
    AccountService accountService;


    @Transactional
    public List<Workout> getWorkouts(String username) {
        log.info("Getting workout entities...begin");
        List<WorkoutEntity> entities = workoutRepository.findWorkoutEntitiesByUserUsername(username);
        log.info("Getting workout entities...complete");
        return entities.stream().map(ent -> workoutMapper.entityToModel(ent)).collect(Collectors.toList());
    }


    @Transactional
    public void saveWorkout(Workout workout, String username) {
        log.info("Transforming workout model to entity...begin");
        WorkoutEntity entity = workoutMapper.modelToEntity(workout);
        AccountEntity user = accountService.loadUserEntityByUsername(username);
        entity.setUser(user);
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
