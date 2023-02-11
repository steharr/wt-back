package com.workout;

import com.workout.application.WorkoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WorkoutApplicationTests {

    @Autowired
    WorkoutService workoutService;

    @Test
    void contextLoads() {
    }

    @Test
    void testWorkoutServiceGet() {
        var tests = workoutService.getWorkout();
    }

    @Test
    void testWorkoutServiceSave() {
//        var wk1 = new Workout(2L, List.of(new Exercise("Bench Press", 3L, 10L)), Date.from(Instant.now()), 1);
//        workoutService.saveWorkout(wk1);

        var tests = workoutService.getWorkout();

        assertNotNull(tests);
    }


}
