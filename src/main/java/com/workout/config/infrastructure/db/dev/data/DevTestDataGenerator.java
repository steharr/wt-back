package com.workout.config.infrastructure.db.dev.data;

import com.workout.security.application.AccountService;
import com.workout.security.domain.dto.AccountDetailsDTO;
import com.workout.security.domain.model.AvatarEyesType;
import com.workout.security.domain.model.AvatarHairType;
import com.workout.session.application.WorkoutService;
import com.workout.session.domain.model.Exercise;
import com.workout.session.domain.model.Workout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class DevTestDataGenerator {

    private static final String TEST_USERNAME = "dev";
    private static final String TEST_PASSWORD = "devpass123";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DevTestDataGenerator() {
    }


    public void baseSetup(WorkoutService workoutService, AccountService accountService) {
        log.debug("DEV Test data base setup triggerred");
        createDummyUser(accountService);
        saveWorkoutTypesFromCSV();
        saveProgressionWorkouts(workoutService);
        log.debug("DEV Test data base setup completed");
    }


    private void createDummyUser(AccountService accountService) {
        accountService.save(new AccountDetailsDTO(
                "mr",
                "developer",
                45,
                "MALE",
                TEST_USERNAME,
                "dev@gmail.com",
                AvatarEyesType.NORMAL,
                AvatarHairType.STYLISH,
                TEST_PASSWORD
        ));
    }

    private void saveWorkoutTypesFromCSV() {
        try {
            jdbcTemplate.execute("INSERT INTO EXERCISE_TYPES (name, description, image)\n" +
                    "SELECT\n" +
                    "    EXERCISE_NAME,\n" +
                    "    DESCRIPTION_URL,\n" +
                    "    EXERCISE_IMAGE\n" +
                    "FROM CSVREAD('classpath:exercises.csv');" +
                    "");
        } catch (Exception e) {
            log.error("ERROR setting up exercises types from csv file: {}", e.getMessage());
        }
    }

    private void saveProgressionWorkouts(WorkoutService workoutService) {
//        Improving examples
        Exercise SQUATS_1 = new Exercise("Squats", 5L, 5L, BigDecimal.valueOf(80));
        Exercise SQUATS_2 = new Exercise("Squats", 5L, 5L, BigDecimal.valueOf(85));
        Exercise SQUATS_3 = new Exercise("Squats", 5L, 5L, BigDecimal.valueOf(90));
//        Declining examples
        Exercise BENCH_1 = new Exercise("Bench Press", 5L, 5L, BigDecimal.valueOf(80));
        Exercise BENCH_2 = new Exercise("Bench Press", 5L, 5L, BigDecimal.valueOf(75));
        Exercise BENCH_3 = new Exercise("Bench Press", 5L, 5L, BigDecimal.valueOf(70));
//        No change examples
        Exercise DEAD_1 = new Exercise("Deadlifts", 5L, 5L, BigDecimal.valueOf(80));
        Exercise DEAD_2 = new Exercise("Deadlifts", 5L, 5L, BigDecimal.valueOf(80));
        Exercise DEAD_3 = new Exercise("Deadlifts", 5L, 5L, BigDecimal.valueOf(80));

        saveWorkout(LocalDateTime.now().minusDays(3), List.of(SQUATS_1), 4, 1L, TEST_USERNAME, workoutService);
        saveWorkout(LocalDateTime.now().minusDays(2), List.of(SQUATS_2), 3, 2L, TEST_USERNAME, workoutService);
        saveWorkout(LocalDateTime.now().minusDays(1), List.of(SQUATS_3), 3, 3L, TEST_USERNAME, workoutService);

        saveWorkout(LocalDateTime.now().minusDays(3), List.of(BENCH_1), 4, 1L, TEST_USERNAME, workoutService);
        saveWorkout(LocalDateTime.now().minusDays(2), List.of(BENCH_2), 3, 2L, TEST_USERNAME, workoutService);
        saveWorkout(LocalDateTime.now().minusDays(1), List.of(BENCH_3), 3, 3L, TEST_USERNAME, workoutService);

        saveWorkout(LocalDateTime.now().minusDays(3), List.of(DEAD_1), 4, 1L, TEST_USERNAME, workoutService);
        saveWorkout(LocalDateTime.now().minusDays(2), List.of(DEAD_2), 3, 2L, TEST_USERNAME, workoutService);
        saveWorkout(LocalDateTime.now().minusDays(1), List.of(DEAD_3), 3, 3L, TEST_USERNAME, workoutService);
    }

    private void saveDummyWorkouts(WorkoutService workoutService) {
        Exercise SQUAT = new Exercise("Bench Press", 5L, 5L, BigDecimal.valueOf(100));
        Exercise BENCH_PRESS = new Exercise("Bench Press", 5L, 5L, BigDecimal.valueOf(70));
        Exercise CHIN_UPS = new Exercise("Chin Up", 5L, 5L, BigDecimal.valueOf(100));
        saveWorkout(LocalDateTime.now(), List.of(CHIN_UPS, SQUAT, BENCH_PRESS), 3, 1L, TEST_USERNAME, workoutService);
        saveWorkout(LocalDateTime.now(), List.of(SQUAT, CHIN_UPS), 3, 2L, TEST_USERNAME, workoutService);
        saveWorkout(LocalDateTime.now(), List.of(SQUAT, BENCH_PRESS), 3, 3L, TEST_USERNAME, workoutService);
    }

    private void saveWorkout(LocalDateTime date, List<Exercise> exercises, Integer rating, Long id, String username, WorkoutService workoutService) {
        var w = new Workout();
        w.setDate(date);
        w.setExercises(exercises);
        w.setWorkoutId(id);
        w.setRating(rating);
        workoutService.saveWorkout(w, username);
    }
}
