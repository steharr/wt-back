package com.workout.infrastructure.repository.config;

import com.workout.infrastructure.repository.ExerciseRepository;
import com.workout.infrastructure.repository.WorkoutRepository;
import com.workout.infrastructure.repository.entity.ExerciseEntity;
import com.workout.infrastructure.repository.entity.WorkoutEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner{

    @Autowired
    private ExerciseRepository excerciseRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    var ex1 = new ExerciseEntity();

    ex1.setExerciseId(1L);
    ex1.setExerciseName("test");

    excerciseRepository.save(ex1);

    var wk1 = new WorkoutEntity();
//    wk1.setDate(Date.valueOf("12-01-2023"));
    wk1.setWorkoutId(1L);
    wk1.setExercises(List.of(ex1));

    workoutRepository.save(wk1);

this.test();
    }

    @Transactional
    private void test(){
    var wk2 = workoutRepository.findAll();

    }
}