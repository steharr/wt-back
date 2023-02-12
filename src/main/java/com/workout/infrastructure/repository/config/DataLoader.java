package com.workout.infrastructure.repository.config;

import com.workout.application.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    WorkoutService workoutService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    @Transactional
    void save() {

    }


}