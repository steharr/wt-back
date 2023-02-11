package com.workout.infrastructure.repository.config;

import com.workout.application.WorkoutService;
import com.workout.domain.model.Exercise;
import com.workout.domain.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    WorkoutService workoutService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        save();
    }

    @Transactional
    void save() {
        var wk1 = new Workout(1L, List.of(new Exercise("Bench Press", 3L, 10L)), Date.from(Instant.now()), 1);
        workoutService.saveWorkout(wk1);
    }


}