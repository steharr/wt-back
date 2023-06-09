package com.workout.config.dev;

import com.workout.security.domain.dto.AccountDetailsDTO;
import com.workout.security.domain.model.AccountService;
import com.workout.session.application.WorkoutService;
import com.workout.session.domain.model.Exercise;
import com.workout.session.domain.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@Profile("DEV")
public class DataLoader implements ApplicationRunner {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.saveDummyWorkouts();
        this.createDummyUser();
    }

    @Transactional
    void saveDummyWorkouts() {

        Exercise SQUAT = new Exercise("Bench Press", 5L, 5L, BigDecimal.valueOf(100));
        Exercise BENCH_PRESS = new Exercise("Bench Press", 5L, 5L, BigDecimal.valueOf(70));
        Exercise CHIN_UPS = new Exercise("Chin Up", 5L, 5L, BigDecimal.valueOf(100));
        var wk1 = new Workout();
        wk1.setDate(new Date());
        wk1.setExercise(List.of(SQUAT, BENCH_PRESS));
        wk1.setRating(3);
        wk1.setWorkoutId(1L);
        workoutService.saveWorkout(wk1, "dev");

        var wk2 = new Workout();
        wk2.setDate(new Date());
        wk2.setExercise(List.of(SQUAT, CHIN_UPS));
        wk2.setRating(3);
        wk2.setWorkoutId(2L);
        workoutService.saveWorkout(wk2, "dev");

        var wk3 = new Workout();
        wk3.setDate(new Date());
        wk3.setExercise(List.of(CHIN_UPS, SQUAT, BENCH_PRESS));
        wk3.setRating(3);
        wk3.setWorkoutId(3L);
        workoutService.saveWorkout(wk3, "dev");

    }

    private void createDummyUser() {
        accountService.save(new AccountDetailsDTO(
                "mr",
                "developer",
                45,
                "MALE",
                "dev",
                "dev@gmail.com",
                "devpass123"
        ));
    }


}