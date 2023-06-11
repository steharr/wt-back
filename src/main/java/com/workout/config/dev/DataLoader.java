package com.workout.config.dev;

import com.workout.security.application.AccountService;
import com.workout.security.domain.dto.AccountDetailsDTO;
import com.workout.session.application.WorkoutService;
import com.workout.session.domain.model.Exercise;
import com.workout.session.domain.model.Workout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile("DEV")
@Slf4j
public class DataLoader implements ApplicationRunner {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    AccountService accountService;

    private static final String TEST_USERNAME = "dev";
    private static final String TEST_PASSWORD = "devpass123";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Detected DEV profile: Starting ApplicationRunner to load up test data...");
        this.createDummyUser();
        this.saveDummyWorkouts();
        log.info("Detected DEV profile: Completed ApplicationRunner to load up test data...");
    }

    void saveDummyWorkouts() {

        Exercise SQUAT = new Exercise("Bench Press", 5L, 5L, BigDecimal.valueOf(100));
        Exercise BENCH_PRESS = new Exercise("Bench Press", 5L, 5L, BigDecimal.valueOf(70));
        Exercise CHIN_UPS = new Exercise("Chin Up", 5L, 5L, BigDecimal.valueOf(100));
        var wk1 = new Workout();
        wk1.setDate(LocalDateTime.now());
        wk1.setExercise(List.of(SQUAT, BENCH_PRESS));
        wk1.setRating(3);
        wk1.setWorkoutId(1L);
        workoutService.saveWorkout(wk1, TEST_USERNAME);

        var wk2 = new Workout();
        wk2.setDate(LocalDateTime.now());
        wk2.setExercise(List.of(SQUAT, CHIN_UPS));
        wk2.setRating(3);
        wk2.setWorkoutId(2L);
        workoutService.saveWorkout(wk2, TEST_USERNAME);

        var wk3 = new Workout();
        wk3.setDate(LocalDateTime.now());
        wk3.setExercise(List.of(CHIN_UPS, SQUAT, BENCH_PRESS));
        wk3.setRating(3);
        wk3.setWorkoutId(3L);
        workoutService.saveWorkout(wk3, TEST_USERNAME);

    }

    private void createDummyUser() {
        accountService.save(new AccountDetailsDTO(
                "mr",
                "developer",
                45,
                "MALE",
                TEST_USERNAME,
                "dev@gmail.com",
                TEST_PASSWORD
        ));
    }


}