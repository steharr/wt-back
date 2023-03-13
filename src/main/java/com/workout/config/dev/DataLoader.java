package com.workout.config.dev;

import com.workout.session.application.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("DEV")
public class DataLoader implements ApplicationRunner {

    @Autowired
    WorkoutService workoutService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Transactional
    void setupDummyData() {
    }
    
}