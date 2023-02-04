package com.workout.application;

import com.workout.domain.model.Exercise;
import com.workout.domain.model.Workout;
import com.workout.infrastructure.repository.WorkoutRepository;
import com.workout.infrastructure.repository.entity.ExerciseEntity;
import com.workout.infrastructure.repository.entity.WorkoutEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {


    @Autowired
    WorkoutRepository workoutRepository;

    @Transactional
    public Workout getWorkout(){
        List<WorkoutEntity> workoutEntities = workoutRepository.findAll();
        return this.transformWorkoutEntity(workoutEntities.get(0));
    }

    public void saveWorkout(Workout workoutDTO){

    }

    private Workout transformWorkoutEntity(WorkoutEntity ent){
        return new Workout(ent.getWorkoutId(),
                ent.getExercises().stream().map(this::transformExceriseEntity).collect(Collectors.toList()),
                null,1);
    }

    private Exercise transformExceriseEntity(ExerciseEntity ent){

        return new Exercise(ent.getExerciseName(), ent.getSets(), ent.getReps());
    }
}
