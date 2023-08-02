package com.workout.config.infrastructure.db.dev;

import com.workout.config.infrastructure.db.dev.data.DevTestDataGenerator;
import com.workout.security.application.AccountService;
import com.workout.session.application.WorkoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("DEV")
@Slf4j
public class DataLoader implements ApplicationRunner {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Detected DEV profile: Starting ApplicationRunner to load up test data...");
        DevTestDataGenerator.baseSetup(workoutService, accountService);
        log.info("Detected DEV profile: Completed ApplicationRunner to load up test data...");
    }

}