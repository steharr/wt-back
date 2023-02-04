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

@Component
public class DataLoader implements ApplicationRunner{

    @Autowired
    private ExerciseRepository excerciseRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        save();
    }

    @Transactional
    void save(){
    var wk1 = new WorkoutEntity();
    wk1.setWorkoutId(1L);
    workoutRepository.save(wk1);

    var ex1 = new ExerciseEntity();
    ex1.setExerciseId(1L);
    ex1.setExerciseName("test");
    ex1.setWorkoutEntity(wk1);
    excerciseRepository.save(ex1);


    }


}